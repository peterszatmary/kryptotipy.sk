package sk.kryptotipy.web.data.db.mappers;

import org.springframework.jdbc.core.RowMapper;
import sk.kryptotipy.web.data.db.Label;

import java.sql.ResultSet;
import java.sql.SQLException;

public class LabelMapper implements RowMapper<Label> {

    @Override
    public Label mapRow(ResultSet rs, int rowNum) throws SQLException {
        var label = new Label();
        label.setName(rs.getString("l.name"));
        return label;
    }
}
