package ga.healtCare.src.messageBoard;

import ga.healtCare.config.BaseException;
import ga.healtCare.config.BaseResponse;
import ga.healtCare.config.BaseResponseStatus;
import ga.healtCare.src.messageBoard.model.*;
import ga.healtCare.src.user.UserInfoRepository;
import ga.healtCare.src.user.UserInfoService;
import ga.healtCare.src.user.models.UserInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MessageBoardService {

    private final UserInfoRepository userInfoRepository;
    private final MessageBoardRepository messageBoardRepository;
    /**
     * 게시글 등록
     * @param userIdx
     */
    public Long  createMessageBoard(Long userIdx, PostMessageBoardReq postMessageBoardReq) throws BaseException {

        UserInfo userInfo = userInfoRepository.findById(userIdx).orElse(null);
        if(userInfo==null){
            throw new BaseException(BaseResponseStatus.NOT_FOUND_USER);
        }
        MessageBoardInfo messageBoardInfo = new MessageBoardInfo(postMessageBoardReq.getMessage(),userInfo, postMessageBoardReq.getTitle());
        MessageBoardInfo message = messageBoardRepository.save(messageBoardInfo);
         return message.getId();


    }

    /**
     * 게시글 수정
     */

    public void updateMessageBoard(Long messageBoardIdx,Long userIdx, PathMessageBoardReq pathMessageBoardReq) throws BaseException {

        UserInfo userInfo = userInfoRepository.findById(userIdx).orElse(null);
        if(userInfo==null){
            throw new BaseException(BaseResponseStatus.NOT_FOUND_USER);
        }
        //게시글 작성 여부 파악 (다른유저가 게시글을 수정할 수 없도록)
        MessageBoardInfo messageBoardInfo = messageBoardRepository.findById(messageBoardIdx).orElse(null);
        //수정하려는 게시물 존재여부 확인
        if(messageBoardInfo==null||messageBoardInfo.getIsDeleted().equals("Y")){
            throw new BaseException(BaseResponseStatus.NOT_FOUND_MESSAGE_BOARD);
        }
        if(messageBoardInfo.getId()!=messageBoardIdx){
            throw new BaseException(BaseResponseStatus.NOT_MATCH_MESSAGE_BOARD);
        }

        if(pathMessageBoardReq.getMessage()!=null&&pathMessageBoardReq.getMessage().length()!=0){
            messageBoardInfo.setMessage(pathMessageBoardReq.getMessage());
        }
        if(pathMessageBoardReq.getTitle()!=null&&pathMessageBoardReq.getTitle().length()!=0){
            messageBoardInfo.setTitle(pathMessageBoardReq.getTitle());
        }

        messageBoardRepository.save(messageBoardInfo);
    }
    /**
     * 게시글 삭제
     */
    public void deleteMessageBoard(Long messageBoardIdx,Long userIdx) throws BaseException {
        UserInfo userInfo = userInfoRepository.findById(userIdx).orElse(null);
        if(userInfo==null){
            throw new BaseException(BaseResponseStatus.NOT_FOUND_USER);
        }
        //게시글 작성 여부 파악 (다른유저가 게시글을 수정할 수 없도록)
        MessageBoardInfo messageBoardInfo = messageBoardRepository.findById(messageBoardIdx).orElse(null);
        //수정하려는 게시물 존재여부 확인
        if(messageBoardInfo==null){
            throw new BaseException(BaseResponseStatus.NOT_FOUND_MESSAGE_BOARD);
        }
        if(messageBoardInfo.getId()!=messageBoardIdx){
            throw new BaseException(BaseResponseStatus.NOT_MATCH_MESSAGE_BOARD);
        }
        messageBoardInfo.setIsDeleted("Y");
        messageBoardRepository.save(messageBoardInfo);
    }

    /**
     * 전체 게시글 리스트 조회
     * @param page
     * @return
     */
    public GetMessageBoardListRes readMessageBoardList(int page) {

        PageRequest pageRequest=PageRequest.of(page,10);
        Page<MessageBoardInfo> messageList1 = messageBoardRepository.findMessageList(pageRequest,"N");
        List<GetMessageBoardRes> content = messageList1.map((MessageBoardInfo t) -> new GetMessageBoardRes(t)).getContent();
          return new  GetMessageBoardListRes(content);
    }

    /**
     * 게시글 읽기
     * @param messageBoardIdx
     */
    public GetMessageBoardRes readMessageBoard(Long messageBoardIdx) throws BaseException {
        MessageBoardInfo messageBoardInfo = messageBoardRepository.findById(messageBoardIdx).orElse(null);
        if(messageBoardInfo==null||messageBoardInfo.getIsDeleted()=="Y"){
            throw  new BaseException(BaseResponseStatus.NOT_FOUND_MESSAGE_BOARD);
        }

        return new GetMessageBoardRes(messageBoardInfo);
    }

    /**
     * 내가 쓴 게시글 조회
     * @param page
     * @return
     */
    public GetMessageBoardListRes readMyMessageBoardList(int page,Long userIdx) {

        PageRequest pageRequest=PageRequest.of(page,10);
        Page<MessageBoardInfo> messageList1 = messageBoardRepository.findMyMessageList(pageRequest,userIdx,"N");
        List<GetMessageBoardRes> content = messageList1.map((MessageBoardInfo t) -> new GetMessageBoardRes(t)).getContent();
        return new  GetMessageBoardListRes(content);
    }
}
