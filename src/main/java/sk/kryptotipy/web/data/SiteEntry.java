package sk.kryptotipy.web.data;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@AllArgsConstructor
@Data
public class SiteEntry {
    private final String title;
    private final Double priority;
    private final LocalDate date;
}
