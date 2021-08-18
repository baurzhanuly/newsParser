package com.example.newsParser.service;

import com.example.newsParser.model.News;

import java.util.List;

public interface NewsService {
    void save(News news);
    boolean isExist(String newsTitle);
    List<News> getAllNews();
}
