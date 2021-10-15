package ga.healtCare.src.messageBoard.model;


import ga.healtCare.src.user.models.UserInfo;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.text.SimpleDateFormat;


@NoArgsConstructor
@Getter
public class GetMessageBoardRes {
    private Long messageBoardIdx;
    private String userNickName;
    private String message;
    private String title;
    private Long userIdx;
    private String createdDate;



    public GetMessageBoardRes(MessageBoardInfo messageBoardInfo){
        this.messageBoardIdx=messageBoardInfo.getId();
        this.userNickName=messageBoardInfo.getUserInfo().getUserNickName();
        this.message=messageBoardInfo.getMessage();
        this.title=messageBoardInfo.getTitle();
        this.userIdx=messageBoardInfo.getUserInfo().getId();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        this.createdDate= simpleDateFormat.format(messageBoardInfo.getCreatedAt());

    }



}
