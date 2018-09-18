package com.poi.jpa;

import com.poi.entity.Comments;
import com.poi.entity.Contents;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CommentJpa extends JpaRepository<Comments,Integer> {

    @Query(value = "select * from t_comments where blog_id = :blogId",nativeQuery = true)
    List<Comments> findListByBlogId(@Param("blogId") int blogId);

    @Query(value = "select * from t_comments where t_father_id = :blogId",nativeQuery = true)
    List<Comments> findListByFatherIdId(@Param("blogId") int blogId);

    @Query(value = "select * from t_comments " +
            "where t_father_id = :fatherId order by :orderByP :Dic limit :pageSize offset :page",nativeQuery = true)
    List<Comments> findListByFatherIdOnPage(@Param("fatherId")int fatherId, @Param("orderByP") String orderByP,
                                                   @Param("Dic") String Dic,@Param("page") int page,@Param("pageSize") int pageSize);

    @Query(value = "select * from t_comments " +
            "where blog_id = :blogId order by :orderByP :Dic limit :pageSize offset :page",nativeQuery = true)
    List<Comments> findListByBlogIdOnPage(@Param("blogId")int blogId, @Param("orderByP") String orderByP,
                                            @Param("Dic") String Dic,@Param("page") int page,@Param("pageSize") int pageSize);

    @Query(value = "delete from t_comments where blog_id = :blogId",nativeQuery = true)
    boolean removeByBlogId(@Param("blogId")int blogId);
}
