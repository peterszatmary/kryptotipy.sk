package sk.kryptotipy.web.data.db.mappers;

import lombok.extern.slf4j.Slf4j;

import javax.management.DescriptorKey;
import java.util.stream.IntStream;

@Slf4j
public class QueryFactory {

    //@Deprecated(since = "used before BIG OPTIMALIZATION")
    public static String newestPosts(Integer langsCount, Integer limit) {
//        var query = new StringBuilder()
//                .append("SELECT p.id, p.url, p.title, p.source, p.birthday, p.img_src, p.source_render," +
//                        " p.i18n, p.clicks, p.likes, ")
//                .append("FROM post p ")
//                .append("WHERE p.disabled = 0 ")
//                .append("AND p.i18n IN (")
//                .append(_x(langsCount))
//                .append(") ")
//                .append("AND p.img_src is not null ")
//                .append("ORDER BY p.birthday ")
//                .append("DESC ")
//                .append("LIMIT ")
//                .append(limit)
//                .toString();


                var query = new StringBuilder()
                .append("SELECT p.id, p.post_id, p.post_url, p.post_title, p.post_title_render, p.post_source, p.post_birthday, p.post_source_image, " +
                        "p.post_source_render," +
                        " p.post_lang, p.post_clicks, p.post_likes, post_original_image" +
                        ",p.label_name_1, p.label_relevance_1,p.label_name_2, p.label_relevance_2" +
                        ",p.label_name_3, p.label_relevance_3,p.label_name_4, p.label_relevance_4 ")
                .append("FROM post_with_label p ")
                        //TODO disabled nezabudni
                .append("WHERE p.post_disabled = 0 ")
                //.append("AND p.post_lang IN (")
                        .append("AND p.post_lang IN (")
                .append(_x(langsCount))
                .append(") ")
                .append("AND p.post_source_image is not null ")
                .append("ORDER BY p.post_birthday DESC ")
                .append("LIMIT ")
                .append(limit)
                .toString();

        return query;
    }



    //@Deprecated(since = "used before BIG OPTIMALIZATION")
    public static String getNews(Integer labelsCount, Integer langsCount) {
        var query = new StringBuilder();
//                .append("SELECT p.id, p.url, p.title, p.source, p.birthday, p.img_src, p.source_render," +
//                        "p.i18n, p.clicks, p.likes FROM post p ")
//                .append("JOIN post_index pi ON pi.post_id = p.id JOIN label l ON l.id = pi.label_id ")
//                .append("WHERE p.i18n in ( ")
//                .append(_x(langsCount))
//                .append(" ) and l.name in ( ")
//                .append(_x(labelsCount))
//                .append(" ) ")
//
//                .append("and pi.count=(select max(u.count) from post_index as u where u.post_id = p.id) ")
//

        query
                .append("SELECT p.id, p.post_id, p.post_url, p.post_title, p.post_title_render, p.post_source, p.post_birthday, p.post_source_image, " +
                        "p.post_source_render,post_original_image," +
                        " p.post_lang, p.post_clicks, p.post_likes " +
                        ",p.label_name_1, p.label_relevance_1,p.label_name_2, p.label_relevance_2" +
                        ",p.label_name_3, p.label_relevance_3,p.label_name_4, p.label_relevance_4 ")
                .append("FROM post_with_label p ")
                //TODO disabled nezabudni
                .append("WHERE p.post_disabled = 0 ")
                //.append("AND p.post_lang IN (")
                .append("AND p.post_lang IN (")
                .append(_x(langsCount))
                .append(" ) and p.label_name_1 in ( ")
                .append(_x(labelsCount))
                .append(" ) ")

                .append("AND p.post_source_image is not null ")
                .append("ORDER BY p.post_birthday DESC ")
                .append("LIMIT ?,?");

        //TODO
        return query.toString();
    }


    //TODO v novej verzii uz nebude vobec treba
//    @Deprecated(since = "used before BIG OPTIMALIZATION")
//    public static String getLabelsByPostId() {
//        return new StringBuilder()
//                .append("SELECT l.id, l.name ")
//                .append("FROM label l JOIN post_index pl ON pl.label_id = l.id ")
//                .append("WHERE pl.post_id = ? ORDER BY pl.count DESC LIMIT 4") // LIMIT because i dont want to spend so much time to fix LABEL ONE LINER problem. line can hold max 5 labels. TODO do the fix later
//                .toString();
//    }

    public static String getAllLabels() {
        return new StringBuilder()
                .append("SELECT DISTINCT l.name ")
                .append("FROM label l ")
                .toString();
    }

    private static String _x(Integer count) {
        var str = new StringBuilder();
        IntStream.range(0, count).forEach(x -> str.append("?,"));
        str.delete(str.length() - 1, str.length());
        return str.toString();
    }

    //TODO do not tuch you need it in new version
    public static String postById() {
        var query = new StringBuilder()
                .append("SELECT p.id, p.url, p.title, p.source, p.birthday, p.img_src, p.source_render," +
                        " p.i18n, p.clicks, p.likes ")
                .append("FROM post p ")
                .append("WHERE p.id = ? ")
                .toString();
        //log.info(query);
        return query;
    }
}