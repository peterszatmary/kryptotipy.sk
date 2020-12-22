package sk.kryptotipy.web.data;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@EqualsAndHashCode(of = {"name"})
@NoArgsConstructor
//@Data
@Getter
//@Entity
@ToString(exclude = {"id", "posts"})
//@Table(name = "label")
public class Label {

    //@Id
    //@GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Setter
    //@Column(unique = true)
    private String name;


    public Label(String name) {
        this.name = name;
    }

    //@ManyToMany(mappedBy = "labels")
    @Setter
    private List<Post> posts;
}
