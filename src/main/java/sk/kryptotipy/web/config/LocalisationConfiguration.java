package sk.kryptotipy.web.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;

import java.util.Locale;

@Configuration
public class LocalisationConfiguration {

    @Bean
    public LocaleResolver localeResolver() {
        SubdomainLocaleResolver slr = new SubdomainLocaleResolver();
        slr.setDefaultLocale(new Locale("sk"));
        return slr;
    }
}
