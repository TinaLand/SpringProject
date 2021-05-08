package com.example.tinnews.model;

import java.util.Objects;

public class Article {
    public String author;
    public String content;
    public String description;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Article)) return false;
        Article article = (Article) o;
        return author.equals(article.author) &&
                content.equals(article.content) &&
                description.equals(article.description) &&
                publishedAt.equals(article.publishedAt) &&
                title.equals(article.title) &&
                url.equals(article.url) &&
                urlToImage.equals(article.urlToImage);
    }

    @Override
    public int hashCode() {
        return Objects.hash(author, content, description, publishedAt, title, url, urlToImage);
    }

    public String publishedAt;
    public String title;
    public String url;
    public String urlToImage;

    @Override
    public String toString() {
        return "Article{" +
                "author='" + author + '\'' +
                ", content='" + content + '\'' +
                ", description='" + description + '\'' +
                ", publishedAt='" + publishedAt + '\'' +
                ", title='" + title + '\'' +
                ", url='" + url + '\'' +
                ", urlToImage='" + urlToImage + '\'' +
                '}';
    }

}
