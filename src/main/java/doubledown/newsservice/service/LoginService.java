package doubledown.newsservice.service;

import doubledown.newsservice.dao.MemberDAO;
import doubledown.newsservice.dto.MemberDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoginService {

    private final MemberDAO memberDAO;

    /**
     * 로그인 실행 메서드로 로그인 id 기준으로 검색 후 그 검색 결과의 패스워드가 같으면 로그인 성공
     * */
    public MemberDTO login(String loginId, String password) {
        return memberDAO.findByLoginId(loginId)
                .filter(m -> m.getPassword().equals(password))
                .orElse(null);
    }
}
