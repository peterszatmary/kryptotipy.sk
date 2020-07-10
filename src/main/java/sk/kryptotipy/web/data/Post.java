package sk.kryptotipy.web.data;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

import static org.springframework.format.annotation.DateTimeFormat.ISO.DATE;

@NoArgsConstructor
@AllArgsConstructor
//@Data
@Getter
@Entity
@EqualsAndHashCode
@ToString(exclude = {"labels", "content"})
@Table(name = "post")
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Setter
    @Column(unique = true)
    private String url;

    @Setter
    private String source;

    @Setter
    private String title;

    @Lob
    @Setter
    private String content;

    @DateTimeFormat(iso = DATE)
    @Setter
    private LocalDate birthday;

    @Setter
    private String i18n;

    @Setter
    private Boolean disabled;

    @Setter
    private Boolean external;

    @Setter
    @Column(name = "img_src")
    private String imgSrc;

    @Setter
    @Column(name = "source_render")
    private String sourceRender;

    @ManyToMany
    @JoinTable(
            joinColumns = @JoinColumn(name = "post_id"),
            inverseJoinColumns = @JoinColumn(name = "label_id"))
    @Setter
    private List<Label> labels;
}