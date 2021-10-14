package ga.healtCare.src.toiletInfo;


import ga.healtCare.config.BaseException;
import ga.healtCare.config.BaseResponseStatus;
import ga.healtCare.src.group.GroupInfoRepository;
import ga.healtCare.src.group.models.GroupInfo;
import ga.healtCare.src.toiletInfo.model.*;
import ga.healtCare.src.user.models.UserInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ToiletInfoService {

    private final GroupInfoRepository groupInfoRepository;
    private final ToiletInfoRepository toiletInfoRepository;
    /**
     * 화장실 이용시간 정보등록
     * @param postToiletInfoReq
     */
    public PostToiletInfoRes postToiletTime(PostToiletInfoReq postToiletInfoReq, Long groupIdx) {
        GroupInfo groupInfo = groupInfoRepository.findById(groupIdx).orElse(null);
        ToiletInfo toiletInfo = new ToiletInfo(postToiletInfoReq, groupInfo);
        ToiletInfo saveToiletInfo = toiletInfoRepository.save(toiletInfo);
        return new PostToiletInfoRes(saveToiletInfo.getId());
    }

    /**
     * 그룹별 화장실 사용내역 리스트 조회
     * @param groupIdx
     * @return
     */
    public GetToiletInfoListRes getToiletTimeListByGroup(Long groupIdx, int page) throws BaseException {
         PageRequest pageRequest=PageRequest.of(page,10);
        Page<ToiletInfo> toiletInfoPage = toiletInfoRepository.findAllById(pageRequest,groupIdx);
        if(toiletInfoPage.getContent().size()==0||toiletInfoPage.getContent().isEmpty()){
            throw new BaseException(BaseResponseStatus.NOT_TOILET);
        }
        System.out.println("toiletInfoPage = " + toiletInfoPage.getContent());
        List<GetToiletInfoRes> content = toiletInfoPage.map((ToiletInfo toiletInfo) -> new GetToiletInfoRes(toiletInfo)).getContent();
        return new GetToiletInfoListRes(content);

    }

    /**
     * 유저 화장실 사용내용 체크
     * @param toiletIdx
     * @param userInfo
     */
    public void postToiletCheck(Long toiletIdx, UserInfo userInfo) {


        ToiletInfo toiletInfo = toiletInfoRepository.findById(toiletIdx).orElse(null);
        toiletInfo.setUserInfo(userInfo);
        toiletInfoRepository.save(toiletInfo);
    }

    /**
     * 유저별 화장실 이용내역 조회
     * @param userIdx
     * @return
     */
    public GetToiletInfoListUserRes getToiletTimeListByUser(Long userIdx,int page) throws BaseException {

        PageRequest pageRequest=PageRequest.of(page,10);
        Page<ToiletInfo> toiletInfoPage = toiletInfoRepository.findAllByUserIdx(pageRequest,userIdx);
        if(toiletInfoPage.getContent().size()==0||toiletInfoPage.getContent().isEmpty()){
            throw new BaseException(BaseResponseStatus.NOT_TOILET);
        }
        System.out.println("toiletInfoPage = " + toiletInfoPage.getContent());
        List<GetToiletInfoUserRes> content = toiletInfoPage.map((ToiletInfo toiletInfo) -> new GetToiletInfoUserRes(toiletInfo)).getContent();
        return new GetToiletInfoListUserRes(content);
    }
}
