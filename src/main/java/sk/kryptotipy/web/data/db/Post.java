package sk.kryptotipy.web.data.db;

import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.*;

import static org.springframework.format.annotation.DateTimeFormat.ISO.DATE;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@EqualsAndHashCode(of = {"id"})
@ToString(exclude = {"content"})
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

    @Column(name = "kryptotipy_enabled")
    private Boolean kryptotipy_enabled;

    @Setter
    private Boolean external;

    @Column(name = "clicks")
    private Integer views;

    private Integer likes;

    @Column(name = "img_src")
    private String imgSrc;

    @Column(name = "source_render")
    private String sourceRender;

    //     @Column(name = "post_source_approved")
    //    private String approved;

    //    @ManyToMany
//    @JoinTable(
//            joinColumns = @JoinColumn(name = "post_id"),
//            inverseJoinColumns = @JoinColumn(name = "label_id"))
    private List<Label> labels;
}