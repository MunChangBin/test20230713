package com.example.sihum;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class ArticleController {

    private final ArticleService articleService;
    /* 리스트 만들기*/
   @GetMapping("/article/list")
    public String list(Model model){
       List<Article>articleList =this.articleService.getList();
       model.addAttribute("articleList",articleList);
        return "article_list";
    }
    /*리스트 페이지에서는 등록 버튼이 있고 버튼을 누르면 http://주소:포트/article/create 경로로 이동하고 등록 폼이 나온다. */
    @GetMapping("/article/create")
    public String create(){
        return "article_form";
    }
    /*게시글 등록을 하면 http://주소:포트/article/create로 POST 요청을 보내어 DB에 해당 내용을 저장한다.*/
    @PostMapping("/article/create")
    public String create(@RequestParam String title, @RequestParam String content){
        this.articleService.create(title,content);
        return "redirect:/article/list";
    }
    /*리스트 페이지에서 해당 게시글을 클릭하면 상세페이지로 이동한다. 해당 경로는 http://주소:포트/article/detail/{id} 가 된다.*/
    @GetMapping("/article/detail/{id}")
    public String detail(Model model,@PathVariable("id") Integer id){
        Article article= this.articleService.getArticle(id);
        model.addAttribute("article",article);
        return "article_detail";
    }
   /* 게시글 상세페이지(http://주소:포트/article/detail/{id})에 수정 버튼이 있다. 수정 버튼을 누르면 게시글을 수정 할 수 있는 폼이나 오고 수정이 가능하다.*/
    @GetMapping("/article/modify/{id}")
    public String modify(Model model,Article article,@PathVariable("id")Integer id){
        this.articleService.getArticle(id);
        model.addAttribute("article",article);
        return "article_modify";
    }
    /*게시글 상세페이지(http://주소:포트/article/detail/{id})에 수정 버튼이 있다. 수정 버튼을 누르면 게시글을 수정 할 수 있는 폼이나 오고 수정이 가능하다.*/
    @PostMapping("/article/modify/{id}")
    public String modify(Article article,@PathVariable("id") Integer id,@RequestParam String title, @RequestParam String content){
        this.articleService.modify(article,title,content);
        return "redirect:/article/detail/{id}";
    }
    /*게시글 상세페이지에 삭제 버튼이 있다. 삭제 버튼을 누르면 게시글이 삭제가 된다. 삭제 후 리스트 페이지로 리다이렉트 된다.*/
    @GetMapping("/article/delete/{id}")
    public String delete(@PathVariable("id") Integer id){
        Article article = this.articleService.getArticle(id);
        this.articleService.delete(article);
        return "redirect:/article/list";
    }
}
