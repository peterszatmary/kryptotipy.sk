package sk.kryptotipy.web.data;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@AllArgsConstructor
@Data
public class SiteEntry {
    private final String loc;
    private final Double priority;
    private final LocalDate lastmod;
}
