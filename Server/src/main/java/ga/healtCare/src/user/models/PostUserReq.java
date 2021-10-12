package ga.healtCare.src.user.models;

import lombok.*;

@NoArgsConstructor(access = AccessLevel.PUBLIC) // Unit Test 를 위해 PUBLIC
@Getter

public class PostUserReq {
    private String loginId;
    private String userName;
    private String password;
    private String birth;
    private String msg;

}
