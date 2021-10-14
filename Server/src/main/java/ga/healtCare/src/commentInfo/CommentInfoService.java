package ga.healtCare.src.commentInfo;



import ga.healtCare.config.BaseException;
import ga.healtCare.config.BaseResponseStatus;
import ga.healtCare.src.commentInfo.model.CommentInfo;
import ga.healtCare.src.commentInfo.model.GetCommentListRes;
import ga.healtCare.src.commentInfo.model.GetCommentRes;
import ga.healtCare.src.commentInfo.model.PostCommentRes;
import ga.healtCare.src.messageBoard.MessageBoardRepository;
import ga.healtCare.src.messageBoard.model.MessageBoardInfo;
import ga.healtCare.src.user.UserInfoRepository;
import ga.healtCare.src.user.models.UserInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentInfoService {

    private final MessageBoardRepository messageBoardRepository;
    private final CommentInfoRepository commentInfoRepository;
    private final UserInfoRepository userInfoRepository;
    /**
     * 댓글 등록
     */
    public PostCommentRes postComment(Long messageBoardIdx, Long userIdx, String msg) throws BaseException {

        //게시글 존재 유무 확인
        MessageBoardInfo messageBoardInfo;

        messageBoardInfo=messageBoardRepository.findById(messageBoardIdx).orElse(null);
        if(messageBoardInfo==null||messageBoardInfo.getIsDeleted()=="Y"){
            throw new BaseException(BaseResponseStatus.NOT_FOUND_MESSAGE_BOARD);
        }
        //회원 존재 유무 확인
        UserInfo userInfo = userInfoRepository.findById(userIdx).orElse(null);
        if(userInfo==null||userInfo.getIsDeleted()=="Y"){ //회원 없을경우 오류 던짐
            throw  new BaseException(BaseResponseStatus.NOT_FOUND_USER);
        }

        CommentInfo commentInfo = new CommentInfo(userInfo, messageBoardInfo,msg);
        CommentInfo saveCommentInfo = commentInfoRepository.save(commentInfo);
        return  new PostCommentRes(saveCommentInfo.getId());

    }


    /**
     * 댓글 삭제
     * @param messageBoardIdx
     * @param commentIdx
     * @param userIdx
     */
    public void deleteComment(Long messageBoardIdx, Long commentIdx, Long userIdx) throws BaseException {
        //게시글 존재 유무 확인
        MessageBoardInfo messageBoardInfo;

        messageBoardInfo=messageBoardRepository.findById(messageBoardIdx).orElse(null);
        if(messageBoardInfo==null||messageBoardInfo.getIsDeleted().equals("Y")){
            throw new BaseException(BaseResponseStatus.NOT_FOUND_MESSAGE_BOARD);
        }
        //회원 존재 유무 확인
        UserInfo userInfo = userInfoRepository.findById(userIdx).orElse(null);
        if(userInfo==null||userInfo.getIsDeleted().equals("Y")){ //회원 없을경우 오류 던짐
            throw  new BaseException(BaseResponseStatus.NOT_FOUND_USER);
        }
       //댓글 존재 유무 확인
        CommentInfo commentInfo = commentInfoRepository.findById(commentIdx).orElse(null);

        if(commentInfo==null||commentInfo.getIsDeleted().equals("Y")){
            throw  new BaseException(BaseResponseStatus.NOT_FOUND_COMMENT);
        }
        if(commentInfo.getMessageBoardInfo().getId()!=messageBoardInfo.getId()){
            throw new BaseException(BaseResponseStatus.NOT_MATCH_MESSAGE_BOARD_COMMENT);
        }
        //댓글을 작성한 유저와 동일한지 확인
        if(commentInfo.getUserInfo().getId()!=userIdx){
            throw  new BaseException(BaseResponseStatus.NOT_MATCH_COMMENT);
        }

        commentInfo.setIsDeleted("Y");
        commentInfoRepository.save(commentInfo);
    }

    /**
     * 게시글에 해당하는 댓글 조회
     * @param messageBoardIdx
     */
    public GetCommentListRes getCommentList(Long messageBoardIdx,int page) throws BaseException {
        PageRequest pageRequest = PageRequest.of(page, 10);
        MessageBoardInfo messageBoardInfo = messageBoardRepository.findById(messageBoardIdx).orElse(null);
        if(messageBoardInfo==null||messageBoardInfo.getIsDeleted().equals("Y")){
            throw new BaseException(BaseResponseStatus.NOT_FOUND_MESSAGE_BOARD);
        }
        Page<CommentInfo> commentInfos = commentInfoRepository.findAllByMessageBoardIdx(pageRequest, messageBoardIdx);
        List<GetCommentRes> content = commentInfos.map((CommentInfo commentInfo) -> new GetCommentRes(commentInfo)).getContent();
        GetCommentListRes getCommentListRes = new GetCommentListRes(content);
        return getCommentListRes;
    }
}
