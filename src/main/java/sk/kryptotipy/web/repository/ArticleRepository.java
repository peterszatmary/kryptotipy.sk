package sk.kryptotipy.web.repository;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import sk.kryptotipy.web.data.Article;

import java.sql.Date;
import java.time.Instant;
import java.time.LocalDate;
import java.time.Month;
import java.util.*;
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
                .map(x -> {
                    x.setTitle(rb.getString(x.getRbTitleKey()));
                    x.setDescription(x.getCategory() + " - " + rb.getString(x.getRbCategoryKey()));
                    return x;
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
