package sk.kryptotipy.web.repository;

import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import sk.kryptotipy.web.data.Label;
import sk.kryptotipy.web.data.Post;
import sk.kryptotipy.web.data.jdbc.LabelMapper;
import sk.kryptotipy.web.data.jdbc.QueryFactory;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Repository
public class LabelRepository {
    private final JdbcTemplate jdbcTemplate;

    public List<Label> getAllLabels() {
        List<Label> labels = new ArrayList<>();

        try {
            labels = jdbcTemplate.query(QueryFactory.getAllLabels(), new Object[] {}, new LabelMapper());

        } catch(Exception e){
            e.printStackTrace();
        }
        return labels;
    }
}
