package sk.kryptotipy.web.data;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class ExternalPriceId {
    private String siteName;
    private String id;
}
