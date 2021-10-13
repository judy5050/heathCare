package ga.healtCare.src.messageBoard;


import ga.healtCare.config.BaseException;
import ga.healtCare.config.BaseResponse;
import ga.healtCare.config.BaseResponseStatus;
import ga.healtCare.src.group.models.GroupInfo;
import ga.healtCare.src.messageBoard.model.GetMessageBoardListRes;
import ga.healtCare.src.messageBoard.model.PathMessageBoardReq;
import ga.healtCare.src.messageBoard.model.PostMessageBoardReq;
import ga.healtCare.src.messageBoard.model.PostMessageBoardRes;
import ga.healtCare.src.user.UserInfoRepository;
import ga.healtCare.src.user.UserInfoService;
import ga.healtCare.src.user.models.UserInfo;
import ga.healtCare.utils.JwtService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class MessageBoardController {

    private final JwtService jwtService;
    private final UserInfoService userInfoService;
    private final UserInfoRepository userInfoRepository;
    private final MessageBoardService messageBoardService;

    @PostMapping("/messageBoard/{userIdx}")
    public BaseResponse<PostMessageBoardRes> postMessageBoard(@PathVariable Long userIdx, @RequestBody PostMessageBoardReq postMessageBoardReq){

        if(postMessageBoardReq.getMessage()==null||postMessageBoardReq.getMessage().length()==0){
              return new BaseResponse(BaseResponseStatus.EMPTY_MESSAGE);
        }


        Long messageBoardIdx;

        try {
            //1. jwt 토큰 유무 확인
            jwtService.getUserId();
            //2. 게시글 등록
            messageBoardIdx = messageBoardService.createMessageBoard(userIdx, postMessageBoardReq);

        } catch (BaseException exception) {
            return new BaseResponse<>(exception.getStatus());
        }

        return new BaseResponse<>(BaseResponseStatus.SUCCESS_POST_MESSAGE,new PostMessageBoardRes(messageBoardIdx));


    }

    /**
     * 게시글 수정
     * @param messageBoardIdx
     * @param userIdx
     * @return
     */
    @PatchMapping("/messageBoard/{messageBoardIdx}/{userIdx}")
    public BaseResponse patchMessage(@PathVariable Long messageBoardIdx, @PathVariable Long userIdx, @RequestBody PathMessageBoardReq patchMessageReq){

        if(patchMessageReq.getMessage()==null||patchMessageReq.getMessage().length()==0){
            return new BaseResponse(BaseResponseStatus.EMPTY_MESSAGE);
        }
        try {
            //1. jwt 토큰 유무 확인
            jwtService.getUserId();
            //2. 게시글 수정
            messageBoardService.updateMessageBoard(messageBoardIdx,userIdx, patchMessageReq);

        } catch (BaseException exception) {
            return new BaseResponse<>(exception.getStatus());
        }

        return new BaseResponse<>(BaseResponseStatus.SUCCESS_PATCH_MESSAGE);



    }

    /**
     * 게시글 삭제 API
     * @param messageBoardIdx
     * @param userIdx
     * @return
     */
    @DeleteMapping("/messageBoards/{messageBoardIdx}/{userIdx}")
    public BaseResponse deleteMessageBoard(@PathVariable Long messageBoardIdx, @PathVariable Long userIdx){


        try {
            //1. jwt 토큰 유무 확인
            jwtService.getUserId();
            //2. 게시글 수정
            messageBoardService.deleteMessageBoard(messageBoardIdx,userIdx);

        } catch (BaseException exception) {
            return new BaseResponse<>(exception.getStatus());
        }

        return new BaseResponse<>(BaseResponseStatus.SUCCESS_DELETE_MESSAGE);



    }

    /**
     * 게시판 글 조회
     */

    @GetMapping("/messageBoards")
    public BaseResponse<GetMessageBoardListRes> getMessageList(@RequestParam("page") int page){

        GetMessageBoardListRes getMessageBoardListRes;
        try {
            //1. jwt 토큰 유무 확인
            jwtService.getUserId();
            //2. 게시글 조회

             getMessageBoardListRes = messageBoardService.readMessageBoardList(page);
        } catch (BaseException exception) {
            return new BaseResponse<>(exception.getStatus());
        }

        return new BaseResponse<>(BaseResponseStatus.SUCCESS_READ_MESSAGES,getMessageBoardListRes);





    }

}

