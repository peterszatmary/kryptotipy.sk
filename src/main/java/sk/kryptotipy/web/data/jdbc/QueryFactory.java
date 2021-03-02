package sk.kryptotipy.web.data.jdbc;

import lombok.extern.slf4j.Slf4j;

import java.util.stream.IntStream;

@Slf4j
public class QueryFactory {

    public static String newestPosts() {
        String query = new StringBuilder()
                .append("SELECT p.id, p.url, p.title, p.source, p.birthday, p.img_src, p.source_render, p.i18n, p.clicks, p.likes ")
                .append("FROM post p ")
                .append("WHERE p.disabled = 0 ")
                .append("AND p.i18n IN ('CZ','SK') ")
                .append("ORDER BY p.birthday ")
                .append("DESC ")
                .append("LIMIT 8")
                .toString();
        //log.info(query);
        return query;
    }

    public static String postsByI18n() {
        String query = new StringBuilder()
                .append("SELECT p.id, p.url, p.title, p.source, p.birthday, p.img_src, p.source_render, p.i18n, p.clicks, p.likes ")
                .append("FROM post p ")
                .append("WHERE p.disabled = 0 ")
                .append("AND p.i18n = ? ")
                .append("ORDER BY p.birthday ")
                .append("DESC ")
                .append("LIMIT ?,?")
                .toString();
        //log.info(query);
        return query;
    }

    public static String postsByLabelAll() {

        String query = new StringBuilder()
                .append("SELECT p.id, p.url, p.title, p.source, p.birthday, p.img_src, p.source_render, p.i18n, p.clicks, p.likes ")
                .append("FROM post p ")
                .append("WHERE p.disabled = 0 ")
                .append("ORDER BY p.birthday ")
                .append("DESC ")
                .append("LIMIT ?,?")
                .toString();
        //log.info(query);
        return query;
    }

    public static String postsByLabel() {
        String query = new StringBuilder()
                .append("SELECT p.id, p.url, p.title, p.source, p.birthday, p.img_src, p.source_render, p.i18n, p.clicks, p.likes, l.name ")
                .append("FROM post p JOIN post_labels pl ON pl.post_id = p.id ")
                .append("JOIN label l ON l.id = pl.label_id ")
                .append("WHERE l.name = ? ")
                .append("AND p.disabled = 0 ")
                .append("ORDER BY p.birthday ")
                .append("DESC ")
                .append("LIMIT ?,?")
                .toString();
        //log.info(query);
        return query;
    }

    public static String getLabelsByPostId() {
        return new StringBuilder()
                .append("SELECT l.name ")
                .append("FROM label l JOIN post_labels pl ON pl.label_id = l.id ")
                .append("WHERE pl.post_id = ? LIMIT 5") // LIMIT because i dont want to spend so much time to fix LABEL ONE LINER problem. line can hold max 5 labels. TODO do the fix later
                .toString();
    }

    public static String getAllLabels() {
        return new StringBuilder()
                .append("SELECT DISTINCT l.name ")
                .append("FROM label l ")
                .toString();
    }

    public static String postsByLabelsAndLangs(Integer labelsCount, Integer langsCount) {
        String query = new StringBuilder()
                .append("SELECT p.id, p.url, p.title, p.source, p.birthday, p.img_src, p.source_render, p.i18n, p.clicks, p.likes ")
                .append("FROM post p JOIN post_labels pl ON pl.post_id = p.id ")
                .append("JOIN label l ON l.id = pl.label_id ")
                .append("WHERE l.name in (")
                .append(_x(labelsCount))
                .append(") ")
                .append("AND p.i18n in (")
                .append(_x(langsCount))
                .append(") ")
                .append("AND p.disabled = 0 ")
                //.append("GROUP BY p.id ")
                //.append("HAVING count(p.id)=1 ")
                .append("ORDER BY p.birthday ")
                .append("DESC ")
                .append("LIMIT ?,?")
                .toString();
        log.info(query);
        return query;
    }

    public static String postByUrl() {
        String query = new StringBuilder()
                .append("SELECT p.id, p.url, p.title, p.source, p.birthday, p.img_src, p.source_render, p.i18n, p.clicks ")
                .append("FROM post p ")
                .append("WHERE p.url like ? ") // because ending / can be present in url and can not be. it is optional
                .toString();
        //log.info(query);
        return query;
    }

    private static String _x(Integer count) {
        StringBuilder str = new StringBuilder();
        IntStream.range(0, count).forEach(x -> str.append("?,"));
        str.delete(str.length() - 1, str.length());
        return str.toString();
    }
}