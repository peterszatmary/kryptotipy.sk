package sk.kryptotipy.web.repository;

import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import sk.kryptotipy.web.data.Source;
import sk.kryptotipy.web.data.jdbc.QueryFactory;
import sk.kryptotipy.web.data.jdbc.SourcesMapper;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Repository
public class SourceRepository {

    private JdbcTemplate jdbcTemplate;

    public List<Source> getSources() {
        List<Source> sources = new ArrayList<>();
        try {
            sources  = jdbcTemplate.query(QueryFactory.sources(), new SourcesMapper());
        } catch(Exception e){
            e.printStackTrace();
        }

        return sources;
    }
}
