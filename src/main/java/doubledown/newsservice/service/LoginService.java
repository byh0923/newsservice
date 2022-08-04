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
     *
     * */
    public MemberDTO login(String loginId, String password) {
        return memberDAO.findByLoginId(loginId)
                .filter(m -> m.getPassword().equals(password))
                .orElse(null);
    }
}
