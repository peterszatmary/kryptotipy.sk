import fallbackRss from '../data/rss_fallback.json';
import type { RssArticle } from './types';

/**
 * Strips HTML tags and decodes basic entities.
 */
function cleanText(text: string): string {
  return text
    .replace(/<!\[CDATA\[(.*?)\]\]>/gs, '$1')
    .replace(/<[^>]+>/g, '')
    .replace(/&amp;/g, '&')
    .replace(/&lt;/g, '<')
    .replace(/&gt;/g, '>')
    .replace(/&quot;/g, '"')
    .replace(/&#8211;/g, '–')
    .replace(/&#8217;/g, "'")
    .replace(/&#8220;/g, '"')
    .replace(/&#8221;/g, '"')
    .replace(/\s+/g, ' ')
    .trim();
}

/**
 * Formats pubDate string into clean human-readable Slovak representation.
 */
function formatPubDate(rawDate: string): string {
  try {
    const d = new Date(rawDate);
    if (isNaN(d.getTime())) return rawDate;

    const now = new Date();
    const diffMs = now.getTime() - d.getTime();
    const diffHours = Math.floor(diffMs / (1000 * 60 * 60));

    if (diffHours < 24) {
      return `Dnes, ${d.toLocaleTimeString('sk-SK', { hour: '2-digit', minute: '2-digit' })}`;
    } else if (diffHours < 48) {
      return `Včera, ${d.toLocaleTimeString('sk-SK', { hour: '2-digit', minute: '2-digit' })}`;
    } else {
      return d.toLocaleDateString('sk-SK', { day: 'numeric', month: 'short' });
    }
  } catch {
    return rawDate;
  }
}

/**
 * Lightweight XML item parser for RSS 2.0 feeds.
 */
function parseRssXml(xml: string, sourceName: string): RssArticle[] {
  const items: RssArticle[] = [];
  const itemRegex = /<item[\s>]([\s\S]*?)<\/item>/gi;
  let match: RegExpExecArray | null;

  while ((match = itemRegex.exec(xml)) !== null && items.length < 10) {
    const content = match[1];

    const titleMatch = /<title[\s>]([\s\S]*?)<\/title>/i.exec(content);
    const linkMatch = /<link[\s>]([\s\S]*?)<\/link>/i.exec(content);
    const pubDateMatch = /<pubDate[\s>]([\s\S]*?)<\/pubDate>/i.exec(content);
    const categoryMatch = /<category[\s>]([\s\S]*?)<\/category>/i.exec(content);

    const title = titleMatch ? cleanText(titleMatch[1]) : '';
    const link = linkMatch ? cleanText(linkMatch[1]) : '';
    const rawDate = pubDateMatch ? cleanText(pubDateMatch[1]) : '';
    const category = categoryMatch ? cleanText(categoryMatch[1]) : 'Krypto';

    if (title && link) {
      items.push({
        title,
        link,
        pubDate: formatPubDate(rawDate),
        source: sourceName,
        category,
      });
    }
  }

  return items;
}

/**
 * Fetches and aggregates latest SK/CZ crypto news headlines at build-time.
 */
export async function getLocalRssHeadlines(): Promise<RssArticle[]> {
  const feeds = [
    { url: 'https://kryptomagazin.sk/feed/', source: 'Kryptomagazin.sk' },
    { url: 'https://bitcoinvkapse.cz/feed/', source: 'Bitcoin v Kapse' },
  ];

  const articles: RssArticle[] = [];

  for (const feed of feeds) {
    try {
      const controller = new AbortController();
      const timeoutId = setTimeout(() => controller.abort(), 3500);

      const res = await fetch(feed.url, {
        headers: { 'User-Agent': 'Mozilla/5.0 (compatible; KryptotipyBot/1.0)' },
        signal: controller.signal,
      });
      clearTimeout(timeoutId);

      if (res.ok) {
        const text = await res.text();
        const parsed = parseRssXml(text, feed.source);
        articles.push(...parsed);
      }
    } catch {
      // Continue to next feed or fallback
    }
  }

  if (articles.length >= 5) {
    return articles.slice(0, 10);
  }

  return fallbackRss as RssArticle[];
}
