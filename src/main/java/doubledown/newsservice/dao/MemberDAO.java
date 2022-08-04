package doubledown.newsservice.dao;

import doubledown.newsservice.dto.MemberDTO;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MemberDAO {

    public void save(MemberDTO member);

    public MemberDTO findById(Long id);

    public Optional<MemberDTO> findByLoginId(String loginId);

}
