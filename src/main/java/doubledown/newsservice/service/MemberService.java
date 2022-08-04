package doubledown.newsservice.service;

import doubledown.newsservice.dao.MemberDAO;
import doubledown.newsservice.dto.MemberDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberDAO memberDAO;


    public void save(MemberDTO member) {
        memberDAO.save(member);
    }

    public MemberDAO findById(Long id) {
        return null;
    }

    public Optional<MemberDTO> findByLoginId(String loginId) {
        // memberdao에서 전체 리스트 찾기
        List<MemberDTO> all = null;

        return all.stream().filter(member -> member.getLoginId().equals(loginId)).findFirst();
    }
}
