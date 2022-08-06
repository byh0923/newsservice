package doubledown.newsservice.service;

import doubledown.newsservice.dao.NewsDAO;
import doubledown.newsservice.dto.KeywordDTO;
import doubledown.newsservice.dto.NewsDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class NewsCollectorService {

    private final NewsApiCollector newsApiCollector;

    private final NewsDAO newsDAO;

    public void saveNews(String keyword) {
        List<NewsDTO> list = newsApiCollector.requestNews(keyword);

        newsDAO.saveNews(list);
    }

    public List<KeywordDTO> getKeywords() {
        return newsDAO.getKeywords();
    }

    public void saveKeyword(String keyword) {
        newsDAO.saveKeyword(keyword);
    }

    public int checkKeyword(String keyword) {
        return newsDAO.checkKeyword(keyword);
    }

    public void deleteKeyword(String keyword) {
        newsDAO.deleteKeyword(keyword);
    }

}
