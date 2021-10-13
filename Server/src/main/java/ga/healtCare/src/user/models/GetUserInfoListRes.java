package ga.healtCare.src.user.models;


import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class GetUserInfoListRes {

    private List<GetUserRes> userInfoList;
    private Long count;


}
