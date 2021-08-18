package com.example.newsParser.service.impl;

import com.example.newsParser.model.News;
import com.example.newsParser.repository.NewsRepository;
import com.example.newsParser.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NewsServiceImpl implements NewsService {

    private final NewsRepository newsRepository;

    @Autowired
    public NewsServiceImpl(NewsRepository newsRepository) {
        this.newsRepository = newsRepository;
    }

    @Override
    public void save(News news) {
        newsRepository.save(news);
    }

    @Override
    public boolean isExist(String newsTitle) {
        return newsRepository.findByTitle(newsTitle).isPresent();
    }

    @Override
    public List<News> getAllNews() {
        return newsRepository.findAll();
    }
}
