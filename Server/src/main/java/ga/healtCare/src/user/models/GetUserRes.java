package ga.healtCare.src.user.models;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class GetUserRes {
    private  Long userIdx;
    private  String userName;
    private  String userNickName;
    private  String birth;
}