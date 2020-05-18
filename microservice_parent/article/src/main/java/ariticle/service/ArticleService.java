package ariticle.service;

import ariticle.pojo.Article;

import java.util.List;

public interface ArticleService {

    public List<Article> findAllA();

    public Article findById(Long id);

    public Article add(Article article);

    public Article update(Article article);

    public void deleteById(Long id);
}
