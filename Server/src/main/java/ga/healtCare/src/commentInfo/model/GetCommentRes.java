package ga.healtCare.src.commentInfo.model;


import lombok.AllArgsConstructor;
import lombok.Getter;

import java.text.SimpleDateFormat;

@AllArgsConstructor
@Getter
public class GetCommentRes {
    private Long commentIdx;
    private Long userIdx;
    private String userNickName;
    private String commentMsg;
    private String createdDate;

    public GetCommentRes(CommentInfo commentInfo) {
        this.commentIdx=commentInfo.getId();
        this.userIdx=commentInfo.getUserInfo().getId();
        this.userNickName=commentInfo.getUserInfo().getUserNickName();
        this.commentMsg=commentInfo.getMsg();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        this.createdDate=simpleDateFormat.format(commentInfo.getCreatedAt());
    }
}
