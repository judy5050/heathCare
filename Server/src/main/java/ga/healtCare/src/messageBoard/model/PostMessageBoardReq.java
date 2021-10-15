package ga.healtCare.src.messageBoard.model;


import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class PostMessageBoardReq {
    private String title;
    private String message;
}
