package com.example.sihum.Article;

import com.example.sihum.User.SiteUser;
import com.example.sihum.User.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ArticleService {
    private final ArticleRepository articleRepository;


    private final UserRepository userRepository;

    public void create(String title, String content, SiteUser user) {
        Article article = new Article();
        article.setTitle(title);
        article.setContent(content);
        article.setAuthor(user);
        this.articleRepository.save(article);

    }

    public List<Article> getList() {

        return this.articleRepository.findAll();
    }

    public Article getArticle(Integer id) {
        Optional<Article> article = this.articleRepository.findById(id);
        if (article.isPresent()) {
            return article.get();
        } else {
            return null;
        }

    }

    public void modify(Article article, String title, String content) {
        article.setTitle(title);
        article.setContent(content);
        this.articleRepository.save(article);

    }

    public void delete(Article article) {
        this.articleRepository.delete(article);
    }
}
