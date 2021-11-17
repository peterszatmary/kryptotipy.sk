package sk.kryptotipy.web.repository;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import sk.kryptotipy.web.data.Article;
import java.util.List;


//TODO not in use!!!!!!
@AllArgsConstructor
@Repository
public class RssRepository {

    private final List<Article> articles;

    public List<Article> getKryptotipyContentForRss() {
        return articles;
    }
}
