package doubledown.newsservice.controller;

import doubledown.newsservice.dao.MemberDAO;
import doubledown.newsservice.dto.MemberDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

@Slf4j
@Controller
@RequiredArgsConstructor
public class HomeController {

    private final MemberDAO memberDAO;

    @GetMapping("/")
    public String homeLogin(@SessionAttribute(name = "memberId", required = false) MemberDTO loginMember, Model model) {

        if(loginMember == null) {
            return "home";
        }

        model.addAttribute("member", loginMember);
        return "news/resultHome";
    }
}
