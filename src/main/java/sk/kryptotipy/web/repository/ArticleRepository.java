package sk.kryptotipy.web.repository;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import sk.kryptotipy.web.data.Article;

import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

//TODO encoding problems are caused by external library rome.
// but coinly is able to read it as it is.
//
@AllArgsConstructor
@Repository
public class ArticleRepository {

    private final List<Article> articles;

    /**
     * all articles
     * @param rb
     * @return
     */
    public List<Article> getKryptotipyContent(ResourceBundle rb) {

        var articleList = articles.stream()
                .map((var article) -> {
                    article.setTitle(rb.getString(article.getRbTitleKey()));
                    article.setDescription(article.getCategory() + " - " + rb.getString(article.getRbCategoryKey()));
                    return article;
                })
                .collect(Collectors.toList());
        return articleList;
    }

    /**
     * first 8 articles
     * @param rb
     * @return
     */
    public List<Article> getKryptotipyContentView(ResourceBundle rb) {
        return getKryptotipyContent(rb).stream()
                .limit(8)
                .collect(Collectors.toList());
    }
}
