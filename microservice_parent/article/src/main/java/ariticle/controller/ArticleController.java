package ariticle.controller;

import ariticle.exception.InvaildRequestException;
import ariticle.exception.NotFoundException;
import ariticle.newEntity.NewArticle;
import ariticle.pojo.Article;
import ariticle.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    @GetMapping("/articles")
    public ResponseEntity<?> findAll() {

        List<Article> articles = articleService.findAllA();
        if(articles.isEmpty()){
            throw new NotFoundException("List does not exist");
        }

        return new ResponseEntity<List<Article>>(articles, HttpStatus.OK);
    }

    @GetMapping("/articles/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        Article article = articleService.findById(id);
        if (article == null) {
            throw new NotFoundException(String.format("Article id: %s not found", id));
        }
        return new ResponseEntity<>(article, HttpStatus.OK);
    }

    @PostMapping("/articles")
    public ResponseEntity<?> addArticle(@Valid @RequestBody NewArticle newArticle, BindingResult bindingResult){
        if(bindingResult.hasErrors()) {
            throw new InvaildRequestException("Invaild parameter", bindingResult);
        }

        Article article = articleService.add(newArticle.convertToArticle());

        return new ResponseEntity<>(article, HttpStatus.CREATED);

    }

    @PutMapping("/articles/{id}")
    public ResponseEntity<?> updateArticle(@PathVariable Long id, @Valid @RequestBody NewArticle newArticle, BindingResult bindingResult){
        if(bindingResult.hasErrors()) {
            throw new InvaildRequestException("Invalid parameter", bindingResult);
        }

        Article article = articleService.findById(id);

        if (article == null) {
            throw new NotFoundException(String.format("CurrentBook id: %s not found", id));
        }

        newArticle.convertTo(article);

        Article updatedArticle = articleService.update(article);

        return new ResponseEntity<>(updatedArticle, HttpStatus.OK);
    }

    @DeleteMapping("/articles/{id}")
    public ResponseEntity<?> deleteBook(@PathVariable long id) {
        articleService.deleteById(id);
        return  new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }



}
