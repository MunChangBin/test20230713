package com.example.sihum.Article;

import com.example.sihum.User.SiteUser;
import com.example.sihum.User.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.server.ResponseStatusException;

import java.security.Principal;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class ArticleController {
    private final ArticleService articleService;
    private final UserService userService;


    @GetMapping("/article/list")
    public String list(Model model) {
        List<Article> articleList = this.articleService.getList();
        model.addAttribute("articleList", articleList);
        return "article_list";
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/article/create")
    public String create(ArticleForm articleForm) {

        return "article_form";
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/article/create")
    public String create(@Valid ArticleForm articleForm, BindingResult bindingResult, Principal principal) {
        if (bindingResult.hasErrors()) {
            return "article_form";
        }
        SiteUser siteUser =this.userService.getUser(principal.getName());
        this.articleService.create(articleForm.getTitle(), articleForm.getContent(),siteUser);
        return "redirect:/article/list";
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/article/detail/{id}")
    public String detail(Model model, @PathVariable("id") Integer id) {
        Article article = this.articleService.getArticle(id);
        model.addAttribute("article", article);
        return "article_detail";
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/article/modify/{id}")
    public String modify(Model model, ArticleForm articleForm, Principal principal, @PathVariable("id") Integer id) {
        Article article = this.articleService.getArticle(id);
        if(!article.getAuthor().getUsername().equals(principal.getName())){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "수정권한이없습니다");
        }
        model.addAttribute("article",article);
        articleForm.setTitle(article.getTitle());
        articleForm.setContent(article.getContent());
        return "article_modify";
    }



    @PreAuthorize("isAuthenticated()")
    @PostMapping("/article/modify/{id}")
    public String questionModify(@Valid ArticleForm articleForm, BindingResult bindingResult,
                                 Principal principal, @PathVariable("id") Integer id) {
        if(bindingResult.hasErrors()){
            return "article_modify";
        }
        Article article =this.articleService.getArticle(id);
        if(!article.getAuthor().getUsername().equals(principal.getName())){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST , "수정권한이 없습니다.");

        }
        this.articleService.modify(article,articleForm.getTitle(), articleForm.getContent());
        return String.format("redirect:/article/detail/%s", id);
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/article/delete/{id}")
    public String delete(Principal principal,@PathVariable("id") Integer id){
        Article article = this.articleService.getArticle(id);
        if(! article.getAuthor().getUsername().equals(principal.getName())){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST , "삭제 권한이 없습니다");
        }
        this.articleService.delete(article);
        return "redirect:/article/list";
    }
}
