package doubledown.newsservice.controller;

import doubledown.newsservice.dto.KeywordDTO;
import doubledown.newsservice.service.NewsCollectorService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
public class NewsCollectorController {

    private final NewsCollectorService newsCollectorService;

    /** 
     * 매시 정각마다 실행
     *
     * 0 0 * * * *
     * */
    //*/10 * * * * *
    @Scheduled(cron = "0 0 * * * *")
    public void collectNews() {

        List<KeywordDTO> list = newsCollectorService.getKeywords();

        list.stream().forEach(m -> newsCollectorService.saveNews(String.valueOf(m.getKeywordName())));

    }
}
