package ga.healtCare.src.messageBoard.model;


import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class GetMessageBoardListRes {

    private List<GetMessageBoardRes> messageBoardList;
}
