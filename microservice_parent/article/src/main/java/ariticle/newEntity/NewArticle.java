package ariticle.newEntity;

import org.hibernate.validator.constraints.Length;
import ariticle.pojo.Article;
import ariticle.utils.CustomBeanUtils;
import org.springframework.beans.BeanUtils;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.util.Date;


public class NewArticle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Length(min = 3)
    private String title;

    @NotNull
    private String content;

    private String author;

    private Date addTime;

    public NewArticle() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Date getAddTime() {
        return addTime;
    }

    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }
    public void convertTo(Article article){ new ArticleConvert().convert(this, article); };
    public Article convertToArticle(){ return new ArticleConvert().convert(this); };

    private class ArticleConvert implements Convert<NewArticle, Article> {
        @Override
        public Article convert(NewArticle newArticle, Article currentArticle) {

            String[] nullPropertyFields = CustomBeanUtils.getNullPropertyFields(newArticle);
            BeanUtils.copyProperties(newArticle, currentArticle, nullPropertyFields);

            return currentArticle;
        }

        @Override
        public Article convert(NewArticle newArticle) {

            Article article = new Article();
            String[] nullPropertyFields = CustomBeanUtils.getNullPropertyFields(newArticle);

            BeanUtils.copyProperties(newArticle, article, nullPropertyFields);

            return article;
        }
    }

}
