package sk.kryptotipy.web.data;


import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;

@Builder
@Data
public class Article {

    private String url;
    private String imgSrc;
    private String description;
    private String category;
    private String title;

    private Date pubDate;
    private PostMeta postMeta;

    private String rbTitleKey;
    private String rbCategoryKey;

    @Data
    @Builder
    public static class PostMeta {
        private String metaPostKey;
        private String metaPostTitle;
        private String metaPostDescription;
        private String metaPostImageExtension;
        private String metaImgWidth;
        private String metaImgHeight;
        private LocalDateTime metaPostDate;
    }
}
