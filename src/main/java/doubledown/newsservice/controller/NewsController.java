package doubledown.newsservice.controller;

import com.github.pagehelper.PageInfo;
import doubledown.newsservice.dto.NewsDTO;
import doubledown.newsservice.service.NewsCollectorService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Controller
@AllArgsConstructor
@RequestMapping("/news")
public class NewsController {

    private final NewsCollectorService newsCollectorService;

    /** 
     * 수집 키워드 등록 폼으로 이동
     * */
    @GetMapping("/newsSettingForm")
    public String newsCollectorSettingForm() {
        return "news/newsSettingForm";
    }

    /** 
     * 수집된 데이터 결과 확인하는 메서드
     * */
    @GetMapping("/newsCollectResult")
    public String newsCollectResult(String keyword,
            @RequestParam(required = false, defaultValue = "1") int pageNum, Model model) {
        PageInfo<NewsDTO> list = new PageInfo<>(newsCollectorService.getCollectNews(pageNum, keyword), 10);

        model.addAttribute("list", list);
        model.addAttribute("keyword", keyword);

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

    /** 
     * 등록된 키워드를 삭제하는 메서드
     * */
    @GetMapping("/setting/deleteKeyword")
    public String deleteKeyword(String keyword) {
        newsCollectorService.deleteKeyword(keyword);
        return "news/newsCollectResult";
    }

}
