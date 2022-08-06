package doubledown.newsservice.controller;

import doubledown.newsservice.service.NewsCollectorService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@AllArgsConstructor
@RequestMapping("/news")
public class NewsController {

    private final NewsCollectorService newsCollectorService;

    @GetMapping("/newsSettingForm")
    public String newsCollectorSettingForm() {
        return "news/newsSettingForm";
    }

    @GetMapping("/newsCollectResult")
    public String newsCollectResult() {
        return "news/newsCollectResult";
    }

    /** 
     * 키워드 등록 시 1회 실행
     * */
    @PostMapping("/setting/keyword")
    public String insertKeyword(String keyword) throws Exception {
        if(newsCollectorService.checkKeyword(keyword) != 0) {
            throw new Exception("이미 등록된 키워드입니다.");
        }

        newsCollectorService.saveKeyword(keyword);

        newsCollectorService.saveNews(keyword);

        return "news/newsCollectResult";
    }

    @GetMapping("/setting/deleteKeyword")
    public String deleteKeyword(String keyword) {
        newsCollectorService.deleteKeyword(keyword);
        return "news/newsCollectResult";
    }
}
