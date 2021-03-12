package sk.kryptotipy.web;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;

@Data
@Component
public class AppProperties {

    @Value("${spring.datasource.url}")
    private String dbUrl;

    @Value("${spring.datasource.username}")
    private String dbUsername;

    @Value("${spring.datasource.password}")
    private String dbPassword;

    @Value("${spring.datasource.driver-class-name}")
    private String dbDriver;

    @Value("${page.size}")
    private Long pageSize;

    @Value("${page.description.short}")
    private String pageDescriptionShort;

    @Value("${page.port}")
    private String pagePort;

    @Value("${page.title}")
    private String pageTitle;

    @Value("${page.description}")
    private String pageDescription;

    @Value("${page.author}")
    private String pageAuthor;

    @Value("${base.url}")
    private String baseUrl;

    @Value("${base.url.without.port}")
    private String baseUrlWithoutPort;

    @Value("${base.url.without.port.en}")
    private String baseUrlWithoutPortEn;

    @Value("${base.url.without.port.de}")
    private String baseUrlWithoutPortDe;

    @Value("${base.url.without.port.sk}")
    private String baseUrlWithoutPortSk;

    @Value("${base.url.without.port.hu}")
    private String baseUrlWithoutPortHu;

    @Value("${project.version}")
    private String projectVersion;

    @Value("${project.post.count}")
    private String postsCount;

    @Value("${page.image}")
    private String pageImagePath;

    @Value("#{'${supported.langs}'.split(',')}")
    private List<String> supportedLangs;
}
