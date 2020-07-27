package sk.kryptotipy.web.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.tuckey.web.filters.urlrewrite.Conf;
import org.tuckey.web.filters.urlrewrite.UrlRewriteFilter;
import org.tuckey.web.filters.urlrewrite.UrlRewriter;

import javax.servlet.*;
import java.io.InputStream;

@Configuration
public class UrlRewriteConfig extends UrlRewriteFilter {

    private UrlRewriter urlRewriter;

    @Bean
    public FilterRegistrationBean tuckeyRegistrationBean() {
        final FilterRegistrationBean registrationBean = new FilterRegistrationBean();
        registrationBean.setFilter(new UrlRewriteConfig());
        return registrationBean;
    }

    @Override
    public void loadUrlRewriter(FilterConfig filterConfig) throws ServletException {
        try {
            String urlRewriteConf = "url-rewrite.xml";
            ClassPathResource classPathResource = new ClassPathResource(urlRewriteConf);
            InputStream inputStream = classPathResource.getInputStream();
            Conf conf1 = new Conf(filterConfig.getServletContext(), inputStream, urlRewriteConf, "");
            urlRewriter = new UrlRewriter(conf1);
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }

    @Override
    public UrlRewriter getUrlRewriter(ServletRequest request, ServletResponse response, FilterChain chain) {
        return urlRewriter;
    }

    @Override
    public void destroyUrlRewriter() {
        if (urlRewriter != null)
            urlRewriter.destroy();
    }
}