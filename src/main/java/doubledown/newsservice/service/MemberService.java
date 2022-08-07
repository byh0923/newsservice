package doubledown.newsservice.service;

import doubledown.newsservice.dao.MemberDAO;
import doubledown.newsservice.dto.MemberDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberDAO memberDAO;

    public void save(MemberDTO member) {
        memberDAO.save(member);
    }

}
