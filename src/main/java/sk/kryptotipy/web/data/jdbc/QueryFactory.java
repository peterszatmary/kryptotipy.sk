package sk.kryptotipy.web.data.jdbc;

public class QueryFactory {

    public static String newestPosts() {
        return new StringBuilder()
                .append("SELECT p.id, p.url, p.title, p.source, p.birthday, p.img_src, p.source_render ")
                .append("FROM post p ")
                .append("ORDER BY p.birthday ")
                .append("DESC ")
                .append("LIMIT 12")
                .toString();
    }

    public static String postsByLabelAll() {
        return new StringBuilder()
                .append("SELECT p.id, p.url, p.title, p.source, p.birthday, p.img_src, p.source_render ")
                .append("FROM post p ")
                .append("ORDER BY p.birthday ")
                .append("DESC ")
                .append("LIMIT ?,?")
                .toString();
    }

    //TODO pagination
    public static String postsByLabel() {
        return new StringBuilder()
                .append("SELECT p.id, p.url, p.title, p.source, p.birthday, p.img_src, p.source_render, l.name ")
                .append("FROM post p JOIN post_labels pl ON pl.post_id = p.id ")
                .append("JOIN label l ON l.id = pl.label_id ")
                .append("WHERE l.name = ? ")
                .append("ORDER BY p.birthday ")
                .append("DESC ")
                .append("LIMIT ?,?")
                .toString();
    }

    public static String getLabelsByPostId() {
        return new StringBuilder()
                .append("SELECT l.name ")
                .append("FROM label l JOIN post_labels pl ON pl.label_id = l.id ")
                .append("WHERE pl.post_id = ? LIMIT 5") // LIMIT because i dont wnat to spend so much time to fix LABEL ONE LINER problem. line can hold max 5 labels. TODO do the fix later
                .toString();
    }
}
