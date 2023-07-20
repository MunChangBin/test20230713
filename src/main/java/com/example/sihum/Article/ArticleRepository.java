package com.example.sihum.Article;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ArticleRepository extends JpaRepository<Article,Integer> {

    @Query("select "
            + "distinct a "
            + "from Article a "
            + "left outer join SiteUser u1 on a.author=u1 "
            + "where "
            + "   a.title like %:kw% "
            + "   or a.content like %:kw% "
            + "   or u1.username like %:kw% ")
    List<Article> findAllByKeyword(@Param("kw") String kw);
}
