package sk.kryptotipy.web.data;

import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.util.CollectionUtils;

import javax.persistence.*;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Base64;
import java.util.List;
import java.util.Set;

import static org.springframework.format.annotation.DateTimeFormat.ISO.DATE;

@NoArgsConstructor
@AllArgsConstructor
//@Data
@Getter
//@Entity
@EqualsAndHashCode(of = {"id"})
@ToString(exclude = {"content"})
//@Table(name = "post")
@Slf4j
@Setter
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(unique = true)
    private String url;

    private String source;

    @Setter
    private String title;

    @Transient
    private String titleRender;

    @Lob
    private String content;

    @DateTimeFormat(iso = DATE)
    private LocalDate birthday;

    private String i18n;

    private Boolean disabled;

    @Setter
    private Boolean external;

    @Column(name = "clicks")
    private Integer views;

    private Integer likes;

    @Column(name = "img_src")
    private String imgSrc;

    @Column(name = "source_render")
    private String sourceRender;

    //    @ManyToMany
//    @JoinTable(
//            joinColumns = @JoinColumn(name = "post_id"),
//            inverseJoinColumns = @JoinColumn(name = "label_id"))
    private List<Label> labels;

    public String getPostViewUrl() {
        String hashed = "p" + (this.id + 217) + "sz";
        return "/one-post-frame/" + hashed;
    }

    public String getI18nFlagRender() {
        switch (this.i18n) {
            case "SK" : { return "crypto.news.title.sk"; }
            case "CZ" : { return "crypto.news.title.cz"; }
            case "DE" : { return "crypto.news.title.de"; }
            case "CH" : { return "crypto.news.title.ch"; }
            case "PL" : { return "crypto.news.title.pl"; }
            case "HU" : { return "crypto.news.title.hu"; }
            case "CA" : { return "crypto.news.title.ca"; }
            case "US" : { return "crypto.news.title.us"; }
            case "GB" : { return "crypto.news.title.gb"; }
            default : return "navbar.news";
        }
    }

    /**
     * some sources have to have direct redirect to their site and
     * their content without kryptotipy iframe.
     * @return
     */
    public boolean useRedirectToSourceSite() {
        List<String> redirect = Arrays.asList(
                "krypto.sk", // si tak praje
                "kryptonovinky.sk", //TODO problem s redirectom,
                 "cryptobriefing.com", //TODO problem s redirectom,
                 "trustnodes.com" //TODO problem s redirectom,

        );
        return redirect.contains(sourceRender);
    }
}