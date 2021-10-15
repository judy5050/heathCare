package ga.healtCare.src.cleaningInfo;


import ga.healtCare.src.cleaningInfo.model.CleaningInfo;
import ga.healtCare.src.cleaningInfo.model.GetCleaningInfoRes;
import ga.healtCare.src.cleaningInfo.model.PostCleaningInfoReq;
import ga.healtCare.src.cleaningInfo.model.PostCleaningInfoRes;
import ga.healtCare.src.group.GroupInfoRepository;
import ga.healtCare.src.group.models.GroupInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CleaningInfoService {

    private final CleaningInfoRepository cleaningInfoRepository;
    private final GroupInfoRepository groupInfoRepository;
    /**
     * 청소 관련 정보 등록
     * @param postCleaningInfoReq
     */
    public PostCleaningInfoRes createCleaningInfo(PostCleaningInfoReq postCleaningInfoReq, Long groupIdx) {

        //그룹탈퇴가 없기 때문에 무조건 그룹은 존재한다고 가정
        GroupInfo groupInfo = groupInfoRepository.findById(groupIdx).orElse(null);
//        if(groupInfo==null){
//            throw new BaseException(BaseResponseStatus.NOT_FOUND_USER)
//        }
        CleaningInfo cleaningInfo = new CleaningInfo(groupInfo,postCleaningInfoReq);
        CleaningInfo cleaningInfoSave = cleaningInfoRepository.save(cleaningInfo);

        return new PostCleaningInfoRes(cleaningInfo.getId(),groupInfo.getId());

    }

    /**
     * 최근 청소정보 가져오기
     * @param groupIdx
     */
    public GetCleaningInfoRes getCleaningInfo(Long groupIdx) {
        CleaningInfo cleaningInfo = cleaningInfoRepository.recentlyCleaningInfo(groupIdx).get(0);

        return new GetCleaningInfoRes(cleaningInfo.getId(),cleaningInfo.getLiquidAmount(),cleaningInfo.getRecentlyCleaningTime(),cleaningInfo.getIsCleaned(),cleaningInfo.getAutoCleaningCycle());
    }
}
