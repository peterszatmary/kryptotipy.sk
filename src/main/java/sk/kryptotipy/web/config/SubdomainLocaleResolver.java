package sk.kryptotipy.web.config;

import org.springframework.util.StringUtils;
import org.springframework.web.servlet.i18n.AbstractLocaleResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;

public class SubdomainLocaleResolver extends AbstractLocaleResolver {

    @Override
    public Locale resolveLocale(HttpServletRequest request) {
        String domain = request.getServerName();

        String[] domainParts = domain.split("\\.");

//        if (!(domainParts.length >= 3)) {
//            return new Locale("sk");
//        } else if ("https://www".equals(domainParts[0])) {
//            return new Locale("sk");
//        }

        int i = domain.indexOf(".");
        if (i == -1) {
            return new Locale("sk");
        }

        String language = domain.substring(0, i);

        if ("www".equals(language)) {
            return new Locale("sk");
        }

        Locale locale = StringUtils.parseLocaleString(language);
        if (locale == null) {
            locale = determineDefaultLocale(request);
        }
        return locale != null ? locale : determineDefaultLocale(request);
    }

    protected Locale determineDefaultLocale(HttpServletRequest request) {
        Locale defaultLocale = getDefaultLocale();
        if (defaultLocale == null) {
            defaultLocale = request.getLocale();
        }
        return defaultLocale;
    }

    @Override
    public void setLocale(HttpServletRequest request, HttpServletResponse response, Locale locale) {
        throw new UnsupportedOperationException("Cannot change sub-domain locale - use a different locale resolution strategy");

    }
}