package sk.kryptotipy.web.config;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import sk.kryptotipy.web.AppProperties;

import java.util.Locale;
@AllArgsConstructor
@Configuration
public class LocalisationConfiguration {

    private final AppProperties appProperties;

    @Bean
    public LocaleResolver localeResolver() {
        MixedLocaleResolver slr = new MixedLocaleResolver(appProperties);
        slr.setDefaultLocale(new Locale("", "SK"));
        return slr;
    }

}
