package doubledown.newsservice.dao;

import doubledown.newsservice.dto.KeywordDTO;
import doubledown.newsservice.dto.NewsDTO;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NewsDAO {

    public void saveNews(List<NewsDTO> list);

    public List<KeywordDTO> getKeywords();

    public void saveKeyword(String keyword);

    public int checkKeyword(String keyword);

    public void deleteKeyword(String keyword);
}
