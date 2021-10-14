package ga.healtCare.src.cleaningInfo;


import ga.healtCare.config.BaseException;
import ga.healtCare.config.BaseResponse;
import ga.healtCare.config.BaseResponseStatus;
import ga.healtCare.src.cleaningInfo.model.GetCleaningInfoRes;
import ga.healtCare.src.cleaningInfo.model.PostCleaningInfoReq;
import ga.healtCare.utils.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class CleaningInfoController {

    private final JwtService jwtService;
    private final CleaningInfoService cleaningInfoService;

    /**
     * 청소관련 정보 등록
     * @return
     */
    @PostMapping("/cleaningInfo")
    public BaseResponse createCleaningInfo(@RequestBody PostCleaningInfoReq postCleaningInfoReq){

        Long groupIdx;
        if(postCleaningInfoReq.getAutoCleaningCycle()==null){
            return new BaseResponse(BaseResponseStatus.EMPTY_AUTO_CLEANING);
        }
        if(postCleaningInfoReq.getIsCleaned()==null){
            return new BaseResponse(BaseResponseStatus.EMPTY_IS_CLEANED);

        }
        if(postCleaningInfoReq.getRecentlyCleaningTime()==null){
            return new BaseResponse(BaseResponseStatus.EMPTY_CLEANING_TIME);
        }
        if(postCleaningInfoReq.getLiquidAmount()==null){
            return new BaseResponse(BaseResponseStatus.EMPTY_LIQUID);
        }
        try {
             groupIdx = jwtService.getUserId();
             cleaningInfoService.createCleaningInfo(postCleaningInfoReq,groupIdx);

        }catch (BaseException e){
            return new BaseResponse(e.getStatus());
        }



        return new BaseResponse(BaseResponseStatus.SUCCESS_POST_CLEANING);
    }

    /**
     * 청소관련 정보 조회
     */

    @GetMapping("/cleaningInfo")
    public BaseResponse<GetCleaningInfoRes> readCleaningInfo(){

        GetCleaningInfoRes cleaningInfo;
        Long groupIdx;
        try {
            groupIdx = jwtService.getUserId();
             cleaningInfo = cleaningInfoService.getCleaningInfo(groupIdx);

        }catch (BaseException e){
            return new BaseResponse(e.getStatus());
        }



        return new BaseResponse(BaseResponseStatus.SUCCESS_READ_CLEANING,cleaningInfo);



    }



}
