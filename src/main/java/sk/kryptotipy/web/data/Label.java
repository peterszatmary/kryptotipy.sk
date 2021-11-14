package sk.kryptotipy.web.data;

import lombok.*;
import java.util.List;

@EqualsAndHashCode(of = {"name"})
@NoArgsConstructor
@Getter
@ToString(exclude = {"id", "posts"})
public class Label {

    private Long id;

    @Setter
    private String name;

    @Setter
    private List<Post> posts;
}
