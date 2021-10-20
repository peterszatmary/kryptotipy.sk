package sk.kryptotipy.web.data.jdbc;

import org.springframework.jdbc.core.RowMapper;
import sk.kryptotipy.web.data.Source;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SourcesMapper implements RowMapper<Source> {

    @Override
    public Source mapRow(ResultSet rs, int i) throws SQLException {
        Source s = new Source();
        s.setOrder(i + 1);
        s.setNumberOfPosts(rs.getInt(1));
        s.setUrl(rs.getString(2));
        s.setUrlRendered(rs.getString(3));
        s.setI18n(rs.getString(4));
        s.setBirthday(rs.getString(5));
        return s;
    }
}
