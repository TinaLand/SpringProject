package com.example.tinnews.model;

import java.util.List;
import java.util.Objects;

public class NewsResponse {
    public Integer totalResults;
    public List<Article> articles;
    public String code;
    public String message;
    public String status;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof NewsResponse)) return false;
        NewsResponse that = (NewsResponse) o;
        return totalResults.equals(that.totalResults) &&
                articles.equals(that.articles) &&
                code.equals(that.code) &&
                message.equals(that.message) &&
                status.equals(that.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(totalResults, articles, code, message, status);
    }

    @Override
    public String toString() {
        return "NewsResponse{" +
                "totalResults=" + totalResults +
                ", articles=" + articles +
                ", code='" + code + '\'' +
                ", message='" + message + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
