package sk.kryptotipy.web.data;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import lombok.Data;
import lombok.NoArgsConstructor;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "urlset", namespace = "http://www.sitemaps.org/schemas/sitemap/0.9")
public class Sitemap {

    @XmlElement(name = "url")
    List<URL> urls = null;

    public List<URL> getUrls() {
        return urls;
    }

    public void setUrls(List<URL> urls) {
        this.urls = urls;
    }

    public void addURL(String url) {
        if (urls == null) {
            urls = new ArrayList<>();
        }

    }

    @NoArgsConstructor
    @Data
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlRootElement(name = "url")
    public static class URL {

        private String loc = null;
        private String lastMod = null;
        private Double priority = null;

        public URL(String loc, Double priority, Date created) {
            this.loc = loc;
            this.lastMod = dateToISOString(created);
            this.priority = priority;
        }

        private String dateToISOString(Date date) {
            SimpleDateFormat sdf;
            sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");
            sdf.setTimeZone(TimeZone.getTimeZone("CET"));
            return sdf.format(date);
        }
    }
}