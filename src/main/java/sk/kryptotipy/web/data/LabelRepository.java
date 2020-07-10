package sk.kryptotipy.web.data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

public interface LabelRepository extends JpaRepository<Label, Long> {
    Label findByName(@Param("name") String name);
}