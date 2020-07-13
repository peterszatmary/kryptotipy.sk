package sk.kryptotipy.web.data.jdbc;

public class QueryFactory {

    public static String newestPosts() {
        return new StringBuilder()
                .append("SELECT p.url, p.title, p.source, p.birthday, p.img_src, p.source_render, l.name ")
                .append("FROM post p JOIN post_labels pl ON pl.post_id = p.id ")
                .append("JOIN label l ON l.id = pl.label_id ")
                .append("ORDER BY p.birthday ")
                .append("DESC ")
                .append("LIMIT 200") // 20 x N , where N is number of labels.
                .toString();
    }

    //TODO pagination
    public static String postsByLabel() {
        return new StringBuilder()
                .append("SELECT p.url, p.title, p.source, p.birthday, p.img_src, p.source_render, l.name ")
                .append("FROM post p JOIN post_labels pl ON pl.post_id = p.id ")
                .append("JOIN label l ON l.id = pl.label_id ")
                .append("WHERE l.name = ? ")
                .append("ORDER BY p.birthday ")
                .append("DESC ")
                .append("LIMIT 200") // 20 x N , where N is number of labels.
                .toString();
    }
}
