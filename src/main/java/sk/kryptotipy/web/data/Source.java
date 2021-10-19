package sk.kryptotipy.web.data;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Source {
    private String i18n;
    private Integer numberOfPosts;
    private String url;
    private String urlRendered;
    private Integer order;
    private String birthday;
}
