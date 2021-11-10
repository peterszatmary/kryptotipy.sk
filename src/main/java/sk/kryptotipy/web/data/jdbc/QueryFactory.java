package sk.kryptotipy.web.data.jdbc;

import lombok.extern.slf4j.Slf4j;

import java.util.stream.IntStream;

@Slf4j
public class QueryFactory {

    public static String newestPosts(Integer langsCount) {
        String query = new StringBuilder()
                .append("SELECT p.id, p.url, p.title, p.source, p.birthday, p.img_src, p.source_render," +
                        " p.i18n, p.clicks, p.likes ")
                .append("FROM post p ")
                .append("WHERE p.disabled = 0 ")
                .append("AND p.i18n IN (")
                .append(_x(langsCount))
                .append(") ")
                .append("AND p.img_src is not null ")
                .append("ORDER BY p.birthday ")
                .append("DESC ")
                .append("LIMIT 8")
                .toString();
        return query;
    }



    public static String getNews(Integer labelsCount, Integer langsCount) {
        String query = new StringBuilder()
                .append("SELECT p.id, p.url, p.title, p.source, p.birthday, p.img_src, p.source_render," +
                        "p.i18n, p.clicks, p.likes FROM post p ")
                .append("JOIN post_index pi ON pi.post_id = p.id JOIN label l ON l.id = pi.label_id ")
                .append("WHERE p.i18n in ( ")
                .append(_x(langsCount))
                .append(" ) and l.name in ( ")
                .append(_x(labelsCount))
                .append(" ) ")
                .append("AND p.img_src is not null ")
                .append("and pi.count=(select max(u.count) from post_index as u where u.post_id = p.id) ")
                .append("and p.disabled = 0 ")
                .append("order by p.birthday DESC LIMIT ?,?")
                .toString();
        return query;
    }


    public static String getLabelsByPostId() {
        return new StringBuilder()
                .append("SELECT l.id, l.name ")
                .append("FROM label l JOIN post_index pl ON pl.label_id = l.id ")
                .append("WHERE pl.post_id = ? ORDER BY pl.count DESC LIMIT 4") // LIMIT because i dont want to spend so much time to fix LABEL ONE LINER problem. line can hold max 5 labels. TODO do the fix later
                .toString();
    }

    public static String getAllLabels() {
        return new StringBuilder()
                .append("SELECT DISTINCT l.name ")
                .append("FROM label l ")
                .toString();
    }

    private static String _x(Integer count) {
        StringBuilder str = new StringBuilder();
        IntStream.range(0, count).forEach(x -> str.append("?,"));
        str.delete(str.length() - 1, str.length());
        return str.toString();
    }

    public static String postById() {
        String query = new StringBuilder()
                .append("SELECT p.id, p.url, p.title, p.source, p.birthday, p.img_src, p.source_render," +
                        " p.i18n, p.clicks, p.likes ")
                .append("FROM post p ")
                .append("WHERE p.id = ? ")
                .toString();
        //log.info(query);
        return query;
    }
}