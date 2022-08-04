package doubledown.newsservice.controller;

import doubledown.newsservice.dto.MemberDTO;
import doubledown.newsservice.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
@RequestMapping("/members")
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/add")
    public String addForm(@ModelAttribute MemberDTO member) {
        return "members/addMemberForm";
    }

    @PostMapping("/add")
    public String save(@Valid @ModelAttribute MemberDTO member, BindingResult bindingResult) {
        if(bindingResult.hasFieldErrors()) {
            return "members/addMemberForm";
        }
        memberService.save(member);
        return "redirect:/";
    }
}
