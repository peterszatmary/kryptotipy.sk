package sk.kryptotipy.web.data;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
public class Article {

    private String name;
    private String url;
    private String imgSrc;
    private String description;
    private String category;
    private Date pubDate;
}
