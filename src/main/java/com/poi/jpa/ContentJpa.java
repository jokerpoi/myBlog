package com.poi.jpa;

import com.poi.entity.Contents;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ContentJpa extends JpaRepository<Contents,Integer> {

    @Query(value = "select * from t_contents where t_author_id = :uId",nativeQuery = true)
    List<Contents> findAllByUserId(@Param("uId") int authorId);

    @Query(value = "select * from t_contents where t_author_id = :authorId order by :orderByP :Dic limit :pageSize offset :page",nativeQuery = true)
    List<Contents> findAllByAuthorIdOnPage(@Param("authorId")int authorId, @Param("orderByP") String orderByP,
                                           @Param("Dic") String Dic,@Param("page") int page,@Param("pageSize") int pageSize);
}
