package sk.kryptotipy.web.data;


import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;

@Data
@Builder
public final class Article {
    private String url;
    private String imgSrc;
    private String description;
    private String category;
    private String title;
    private Date pubDate;
    private PostMeta postMeta;
    private String rbTitleKey;
    private String rbCategoryKey;

    @Builder
    public record PostMeta(
            String metaPostKey,
            String metaPostTitle,
            String metaPostDescription,
            String metaPostImageExtension,
            String metaImgWidth,
            String metaImgHeight,
            LocalDateTime metaPostDate
    ) { }
}
