package sk.kryptotipy.web.repository.db;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import sk.kryptotipy.web.data.db.PostWithLabel;

public interface PostWithLabelRepository extends JpaRepository<PostWithLabel, Long> {
    @Query(value = "select * from post_with_label where post_id=:postId", nativeQuery = true)
    PostWithLabel findByPostId(@Param("postId") Long postId);

}
