package ga.healtCare.src.toiletInfo.model;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@Getter
public class PostToiletCheckListReq {

    private List<PostToiletCheckReq> postToiletCheckReqList;
}
