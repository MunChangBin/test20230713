package com.example.sihum.Article;

import com.example.sihum.User.SiteUser;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Article {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String title;

    private String content;

    @ManyToOne
    private SiteUser author;
}
