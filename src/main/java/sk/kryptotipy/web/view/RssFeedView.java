package sk.kryptotipy.web.view;

import com.rometools.rome.feed.rss.Channel;
import com.rometools.rome.feed.rss.Item;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.feed.AbstractRssFeedView;
import sk.kryptotipy.web.AppProperties;
import sk.kryptotipy.web.data.Article;
import sk.kryptotipy.web.repository.ArticleRepository;
import sk.kryptotipy.web.service.UtilityService;

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
        feed.setDescription(utilityService.getResourceBundleSk().getString("page.index.description.long"));
        feed.setLink(appProperties.getBaseUrlWithoutPort());
    }

//TODO CRYPTO-2: I was not able to solve encoding problem with resource bundle!
// try in the future solve it because you can improve by that the maintance
    @Override
    protected List<Item> buildFeedItems(Map<String, Object> model,
                                        HttpServletRequest request, HttpServletResponse response) {

        var articles = articleRepository.getKryptotipyContent(
                utilityService.getResourceBundleSk());
        return articles.stream()
                .map((var article) -> mapArticleToRssItem(article))
                .collect(Collectors.toList());
    }

    private Item mapArticleToRssItem(Article article) {
        var rssItem = new Item();
        rssItem.setTitle(article.getTitle());
        rssItem.setAuthor(appProperties.getPageTitle());
        rssItem.setLink(appProperties.getBaseUrlWithoutPort() + article.getUrl());
        rssItem.setPubDate(article.getPubDate());

        return rssItem;
    }
}