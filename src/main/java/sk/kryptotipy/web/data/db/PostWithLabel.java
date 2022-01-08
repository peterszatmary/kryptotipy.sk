package sk.kryptotipy.web.data.db;

import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import static org.springframework.format.annotation.DateTimeFormat.ISO.DATE;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@EqualsAndHashCode(of = {"id"})
@Slf4j
@Setter
@Entity
@Table(name = "post_with_label")
public class PostWithLabel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "post_id")
    private Long postId;

    @Column(name = "post_url")
    private String url;

    @Column(name = "post_source")
    private String source;

    @Setter
    @Column(name = "post_title")
    private String title;

    @Transient
    @Column(name = "post_title_render")
    private String titleRender;

    @DateTimeFormat(iso = DATE)
    @Column(name = "post_birthday")
    private LocalDate birthday;

    @Column(name = "post_lang")
    private String lang;

    @Column(name = "post_disabled")//TODO
    private Boolean disabled;

    @Setter
    @Column(name = "post_external")//TODO
    private Boolean external;

    @Column(name = "post_clicks")
    private Integer views;

    @Column(name = "post_likes")
    private Integer likes;

    @Column(name = "post_source_image")
    private String imgSrc;

    @Column(name = "post_original_image")
    private String imgSrcOrigin;

    @Column(name = "post_source_render")
    private String sourceRender;

    @Column(name = "post_type")
    private String type;

    @Column(name = "label_name_1")
    private String labelName1;

    @Column(name = "label_name_2")
    private String labelName2;

    @Column(name = "label_name_3")
    private String labelName3;

    @Column(name = "label_name_4")
    private String labelName4;

    @Column(name = "label_relevance_1")
    private Integer labelRelevance1;

    @Column(name = "label_relevance_2")
    private Integer labelRelevance2;

    @Column(name = "label_relevance_3")
    private Integer labelRelevance3;

    @Column(name = "label_relevance_4")
    private Integer labelRelevance4;

//delete , kryptotipy_url instead
    //TODO asi problem kvoli spatnej kompatibilite musim v tom urobit poriadok
    public String getPostViewUrl() {
        var hashed = "p" + (this.postId + 217) + "sz";
        return "/one-post-frame/" + hashed;
    }


    public String getPostRenderShort() {
        if (this.sourceRender == null || this.sourceRender.isEmpty()) {
            return "";
        }
        if (sourceRender.length() <= 20) {
            return this.sourceRender;
        }
        List<String> sourceParts = new ArrayList(Arrays.asList(this.sourceRender.split("\\.")));
        return sourceParts.stream().max(Comparator.comparingInt(String::length)).get();

    }

    public String getI18nFlagRender() {
        switch (this.lang) {
            case "SK" : { return "crypto.news.title.sk"; }
            case "CZ" : { return "crypto.news.title.cz"; }
            case "DE" : { return "crypto.news.title.de"; }
            case "CH" : { return "crypto.news.title.ch"; }
            case "PL" : { return "crypto.news.title.pl"; }
            case "HU" : { return "crypto.news.title.hu"; }
            case "CA" : { return "crypto.news.title.ca"; }
            case "US" : { return "crypto.news.title.us"; }
            case "GB" : { return "crypto.news.title.gb"; }
            case "NL" : { return "crypto.news.title.nl"; }
            default : return "navbar.news";
        }
    }
}
