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
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;
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
                .map(article -> mapArticleToRssItem(article))
                .collect(Collectors.toList());
    }

//    @Override
//    protected List<Item> buildFeedItems(Map<String, Object> model,
//                                        HttpServletRequest request, HttpServletResponse response) {
//
//        List<Article> articles = rssRepository.getKryptotipyContentForRss();
//        return articles.stream()
//                .map(article -> mapArticleToRssItem(article))
//                .collect(Collectors.toList());
//    }



    private Item mapArticleToRssItem(Article article) {
        var rssItem = new Item();
        rssItem.setTitle(article.getTitle());
        rssItem.setAuthor(appProperties.getPageTitle());
        rssItem.setLink(appProperties.getBaseUrlWithoutPort() + article.getUrl());
        rssItem.setPubDate(article.getPubDate());

//        Description description = new Description();
//        description.setValue(article.getDescription());
//        description.setType(article.getDescription());
//        rssItem.setDescription(description);
//
//        Category category = new Category();
//        category.setValue(article.getCategory());
//        rssItem.setCategories(Arrays.asList(category));

        return rssItem;
    }
}