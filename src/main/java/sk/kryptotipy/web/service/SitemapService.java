package sk.kryptotipy.web.service;

//import com.sun.xml.bind.marshaller.NamespacePrefixMapper;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.Marshaller;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import sk.kryptotipy.web.data.Sitemap;
import sk.kryptotipy.web.repository.SiteRepository;

import java.io.StringWriter;
import java.util.stream.Collectors;

@AllArgsConstructor
@Slf4j
@Service
public class SitemapService {

    private final SiteRepository siteRepository;

    public String create(String sitemapLang) throws Exception {

        var sitemap = new Sitemap();
        var sitesByLang = siteRepository.getSites().stream()
                .filter(site -> site.getLoc().contains(sitemapLang + ".kryptotipy.sk"))
                .collect(Collectors.toList());
        sitemap.setUrls(sitesByLang);

        var jaxbContext = JAXBContext.newInstance(Sitemap.class);
        var jaxbMarshaller = jaxbContext.createMarshaller();
        var sw = new StringWriter();

        jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        jaxbMarshaller.marshal(sitemap, sw);


//        var mapper = new NamespacePrefixMapper() {
//            public String getPreferredPrefix(String namespaceUri, String suggestion, boolean requirePrefix) {
//                if ("http://namespace".equals(namespaceUri) && !requirePrefix)
//                    return "";
//                return "ns";
//            }
//        };
//        jaxbMarshaller.setProperty("com.sun.xml.bind.namespacePrefixMapper", mapper);

        return sw.toString();
    }
}
