package ga.healtCare.src.commentInfo;


import ga.healtCare.config.BaseException;
import ga.healtCare.config.BaseResponse;
import ga.healtCare.config.BaseResponseStatus;
import ga.healtCare.src.commentInfo.model.GetCommentListRes;
import ga.healtCare.src.commentInfo.model.PostCommentReq;
import ga.healtCare.src.commentInfo.model.PostCommentRes;
import ga.healtCare.utils.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class CommentInfoController {

    private final CommentInfoService commentInfoService;
    private final JwtService jwtService;
    /**
     * 댓글 등록
     * @param messageBoardIdx
     * @param userIdx
     * @param postCommentReq
     * @return
     * @throws BaseException
     */
    @PostMapping("/messageBoards/{messageBoardIdx}/comment/{userIdx}")
    public BaseResponse<PostCommentRes> createComment(@PathVariable Long messageBoardIdx, @PathVariable Long userIdx, @RequestBody PostCommentReq postCommentReq) throws BaseException {

        PostCommentRes postCommentRes;
        if(postCommentReq.getMsg()==null||postCommentReq.getMsg().length()==0){
            return new BaseResponse<>(BaseResponseStatus.EMPTY_COMMENT);
        }
        try {
                jwtService.getUserId();
             postCommentRes = commentInfoService.postComment(messageBoardIdx, userIdx, postCommentReq.getMsg());
        }catch (BaseException exception){
            return new BaseResponse<>(exception.getStatus());
        }

        return new BaseResponse<>(BaseResponseStatus.SUCCESS_POST_COMMENT,postCommentRes);



    }

    /**
     * 댓글 삭제
     */

    @DeleteMapping("/messageBoards/{messageBoardIdx}/comment/{commentIdx}/{userIdx}")
    public BaseResponse deleteComment(@PathVariable Long messageBoardIdx,@PathVariable Long commentIdx,@PathVariable Long userIdx) throws BaseException {

        try {

            commentInfoService.deleteComment(messageBoardIdx,commentIdx,userIdx);
            jwtService.getUserId();
        }catch (BaseException e){
            return new BaseResponse(e.getStatus());
        }

        return new BaseResponse(BaseResponseStatus.SUCCESS_DELETE_COMMENT);
    }


    /**
     * 게시글에 존재하는 댓글 조회
     */
    @GetMapping("/messageBoards/{messageBoardIdx}/comments")
    public BaseResponse<GetCommentListRes> readCommentList(@PathVariable Long messageBoardIdx, @RequestParam int page){

        GetCommentListRes commentList;
        try {

            jwtService.getUserId();
             commentList = commentInfoService.getCommentList(messageBoardIdx, page);
        }catch (BaseException e){
            return new BaseResponse(e.getStatus());
        }


        return new BaseResponse(BaseResponseStatus.SUCCESS_READ_COMMENT,commentList);
    }





}
