package ga.healtCare.src.commentInfo.model;


import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@AllArgsConstructor
@Getter
public class GetCommentListRes {
    private List<GetCommentRes>commentResList;
}
