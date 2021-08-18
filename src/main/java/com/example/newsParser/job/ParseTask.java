package com.example.newsParser.job;

import com.example.newsParser.model.News;
import com.example.newsParser.service.NewsService;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class ParseTask {

    private NewsService newsService;

    @Autowired
    public ParseTask(NewsService newsService) {
        this.newsService = newsService;
    }

    @Scheduled(fixedDelay = 10000)
    public void parseNewNews(){
        String url = "https://news.ycombinator.com/";
        try {
            Document document = Jsoup.connect(url)
                    .userAgent("Mozilla")
                    .timeout(5000)
                    .referrer("https://google.com")
                    .get();

            Elements news = document.getElementsByClass("storylink");

            for (Element element: news) {
                String title = element.ownText();
                if (!newsService.isExist(title)){
                    newsService.save(new News(title));
                }
            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
