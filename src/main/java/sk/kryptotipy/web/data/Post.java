package sk.kryptotipy.web.data;

import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

import static org.springframework.format.annotation.DateTimeFormat.ISO.DATE;

@NoArgsConstructor
@AllArgsConstructor
//@Data
@Getter
//@Entity
@EqualsAndHashCode
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
        return "/one-post-frame/" + this.getProtocol() + "/" + this.getWWW() + "/" + this.sourceRender + "/" + this.getUrlLastPart();
    }

    public String getI18nFlagRender() {
        switch (this.i18n) {
            case "SK" : { return "Slovenské novinky"; }
            case "CZ" : { return "České novinky"; }
            case "DE" : { return "Nemecké novinky"; }
            case "CH" : { return "Švajčiarské novinky"; }
            case "PL" : { return "Poľské novinky"; }
            case "HU" : { return "Maďarské novinky"; }
            default : return "Novinky";
        }
    }

    private String getUrlLastPart() {
        if (null != url) {
            String[] urlParts = url.split("/");
            return urlParts[urlParts.length -1 ];
        }
        log.info("UrlLastPath = {}", "NA");
        return "NA";
    }

    private String getProtocol() {
        if (null != url) {
            String[] urlParts = url.split("/");
            String s = urlParts[0].replace(":","");
            return "https".equals(s) ? "hs" : "h";
        }
        log.info("protocol = {}", "NA");
        return "NA";
    }

    private String getWWW() {
        if (null != url) {
            return url.contains("www") ? "www" : "non-www";
        }
        log.info("protocol = {}", "NA");
        return "NA";
    }

    /**
     * some sources have to have direct redirect to their site and
     * their content without kryptotipy iframe.
     * @return
     */
    public boolean useRedirectToSourceSite() {
        List<String> redirect = Arrays.asList(
                "krypto.sk", // si tak praje
                "kryptonovinky.sk" //TODO problem s redirectom
        );
        return this.url.contains(redirect.get(0)) || this.url.contains(redirect.get(1));
    }
}