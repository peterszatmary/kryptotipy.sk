package sk.kryptotipy.web.data;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Service {

    private String name;
    private String url;
    private String imgSrc;
    private String description;
}
