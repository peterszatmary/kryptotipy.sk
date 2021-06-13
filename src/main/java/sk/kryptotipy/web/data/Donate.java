package sk.kryptotipy.web.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Donate {
    private String name;
    private String img;
    private String address;
    private String qrImgUrl;
}
