package sk.kryptotipy.web.data;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "urlset", namespace = "")
public class Sitemap {

    @XmlElement(name = "url")
    List<URL> urls;

    public List<URL> getUrls() {
        return urls;
    }

    public void setUrls(List<URL> urls) {
        this.urls = urls;
    }

    @NoArgsConstructor
    @Data
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlRootElement(name = "url")
    public static class URL {

        private String loc;
        private String lastMod;
        private Double priority;

        public URL(String loc, Double priority, Date created) {
            this.loc = loc;
            this.lastMod = dateToISOString(created);
            this.priority = priority;
        }

        private String dateToISOString(Date date) {
            var sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");
            sdf.setTimeZone(TimeZone.getTimeZone("CET"));
            return sdf.format(date);
        }
    }
}