package sk.kryptotipy.web.view;

import com.rometools.rome.feed.rss.Channel;
import com.rometools.rome.feed.rss.Item;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.feed.AbstractRssFeedView;
import sk.kryptotipy.web.AppProperties;
import sk.kryptotipy.web.data.Article;
import sk.kryptotipy.web.repository.ArticleRepository;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@AllArgsConstructor
@Component
public class RssFeedView extends AbstractRssFeedView {

    private final ArticleRepository articleRepository;
    private final AppProperties appProperties;

    @Override
    protected void buildFeedMetadata(Map<String, Object> model,
                                     Channel feed, HttpServletRequest request) {
        feed.setTitle("Kryptotipy.sk RSS Feed");
        feed.setDescription(appProperties.getPageDescription());
        feed.setLink(appProperties.getBaseUrlWithoutPort());
    }

    @Override
    protected List<Item> buildFeedItems(Map<String, Object> model,
                                        HttpServletRequest request, HttpServletResponse response) {

        List<Article> articles = articleRepository.getKryptotipyContent();
        return articles.stream()
                .map(article -> mapArticleToRssItem(article))
                .collect(Collectors.toList());
    }

    private Item mapArticleToRssItem(Article article) {
        Item rssItem = new Item();
        rssItem.setTitle(article.getName());
        rssItem.setAuthor(appProperties.getPageTitle());
        rssItem.setLink(appProperties.getBaseUrlWithoutPort() + article.getUrl());
        rssItem.setPubDate(article.getPubDate());
        return rssItem;
    }
}