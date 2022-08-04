package doubledown.newsservice.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class MemberDTO {

    private long id;

    @NotEmpty
    private String loginId;
    @NotEmpty
    private String name;
    @NotEmpty
    private String password;


}
