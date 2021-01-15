package sk.kryptotipy.web.config;

import lombok.AllArgsConstructor;
import org.apache.catalina.valves.rewrite.RewriteValve;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import sk.kryptotipy.web.AppProperties;

//@AllArgsConstructor
//@Component
public class RedirectToWwwVersionConfig //implements WebServerFactoryCustomizer<TomcatServletWebServerFactory> {
{
    //private final AppProperties properties;

//    @Override
//    public void customize(TomcatServletWebServerFactory factory) {
//        factory.setPort(Integer.getInteger(properties.getPagePort()));
//        factory.addContextValves(new RewriteValve());
//    }
}
