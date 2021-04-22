package sk.kryptotipy.web.view;

import com.rometools.rome.feed.rss.Channel;
import com.rometools.rome.feed.rss.Item;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.feed.AbstractRssFeedView;
import sk.kryptotipy.web.AppProperties;
import sk.kryptotipy.web.data.Article;
import sk.kryptotipy.web.repository.ArticleRepository;
import sk.kryptotipy.web.service.UtilityService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@AllArgsConstructor
@Component
public class RssFeedView extends AbstractRssFeedView {

    private final ArticleRepository articleRepository;
    private final AppProperties appProperties;
    private final UtilityService utilityService;

    @Override
    protected void buildFeedMetadata(Map<String, Object> model,
                                     Channel feed, HttpServletRequest request) {
        feed.setTitle("Kryptotipy.sk RSS Feed");
        //feed.setEncoding("ISO-8859-1");
        feed.setDescription(appProperties.getPageDescription());
        feed.setLink(appProperties.getBaseUrlWithoutPort());
    }

    @Override
    protected List<Item> buildFeedItems(Map<String, Object> model,
                                        HttpServletRequest request, HttpServletResponse response) {

        List<Article> articles = articleRepository.getKryptotipyContent(
                utilityService.getResourceBundleSk(request));
        return articles.stream()
                .map(article -> mapArticleToRssItem(article))
                .collect(Collectors.toList());
    }

    private Item mapArticleToRssItem(Article article) {
        Item rssItem = new Item();
        String t = "";
        try {
            t = new String(article.getName().getBytes("ISO-8859-1"), "ISO-8859-1");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        rssItem.setTitle(t);
        rssItem.setAuthor(appProperties.getPageTitle());
        rssItem.setLink(appProperties.getBaseUrlWithoutPort() + article.getUrl());
        rssItem.setPubDate(article.getPubDate());
        return rssItem;
    }
}