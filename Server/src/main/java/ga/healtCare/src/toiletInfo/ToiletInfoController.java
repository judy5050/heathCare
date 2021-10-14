package ga.healtCare.src.toiletInfo;


import ga.healtCare.config.BaseException;
import ga.healtCare.config.BaseResponse;
import ga.healtCare.config.BaseResponseStatus;
import ga.healtCare.src.toiletInfo.model.*;
import ga.healtCare.src.user.UserInfoRepository;
import ga.healtCare.src.user.models.UserInfo;
import ga.healtCare.utils.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class ToiletInfoController {
    /**
     * 화장실 이용시간 등록
     */
    private final JwtService jwtService;
    private final ToiletInfoService toiletInfoService;
    private final UserInfoRepository userInfoRepository;

    @PostMapping("/toiletTime")
    public BaseResponse createToiletTime(@RequestBody PostToiletInfoReq postToiletInfoReq){

        Long groupIdx;
        PostToiletInfoRes postToiletInfoRes;
        if(postToiletInfoReq.getStartTime()==null){
            return new BaseResponse(BaseResponseStatus.EMPTY_START_TIME);
        }
        if(postToiletInfoReq.getEndTime()==null){
            return new BaseResponse(BaseResponseStatus.EMPTY_END_TIME);
        }
        try {
            groupIdx = jwtService.getUserId();
             postToiletInfoRes = toiletInfoService.postToiletTime(postToiletInfoReq, groupIdx);


        }catch (BaseException e){
            return new BaseResponse(e.getStatus());
        }



        return new BaseResponse(BaseResponseStatus.SUCCESS_POST_TOILET,postToiletInfoRes);
    }

    @GetMapping("/toiletTime")
    public BaseResponse<GetToiletInfoListRes> readToiletTimeList(@Param("page")int page){

        Long groupIdx;
        PostToiletInfoRes postToiletInfoRes;
        GetToiletInfoListRes getToiletInfoListRes;

        try {
            groupIdx = jwtService.getUserId();
            getToiletInfoListRes = toiletInfoService.getToiletTimeListByGroup(groupIdx,page);


        }catch (BaseException e){
            return new BaseResponse(e.getStatus());
        }



        return new BaseResponse(BaseResponseStatus.SUCCESS_READ_TOILET,getToiletInfoListRes);
    }

    @PostMapping("/users/{userIdx}/toilet")
    public BaseResponse createToiletCheck(@PathVariable Long userIdx, @RequestBody PostToiletCheckListReq postToiletCheckListReq){


        UserInfo userInfo = userInfoRepository.findById(userIdx).orElse(null);
        if(userInfo==null||userInfo.getIsDeleted()=="Y"){
            return new BaseResponse(BaseResponseStatus.NOT_FOUND_USER);
        }
        if(postToiletCheckListReq.getPostToiletCheckReqList().isEmpty()){
            return new BaseResponse(BaseResponseStatus.EMPTY_TOILET_TIME);
        }
        for (PostToiletCheckReq postToiletCheckReq : postToiletCheckListReq.getPostToiletCheckReqList()) {
            toiletInfoService.postToiletCheck(postToiletCheckReq.getToiletIdx(),userInfo);
        }

        return new BaseResponse(BaseResponseStatus.SUCCESS_POST_TOILET_USER);
    }

    @GetMapping("/users/{userIdx}/toilet")
    public BaseResponse<GetToiletInfoListUserRes> readToiletTimeList(@PathVariable Long userIdx,@RequestParam int page){

        Long groupIdx;
        PostToiletInfoRes postToiletInfoRes;
        GetToiletInfoListUserRes toiletTimeListByUser;

        try {
            groupIdx = jwtService.getUserId();
            toiletTimeListByUser = toiletInfoService.getToiletTimeListByUser(userIdx, page);



        }catch (BaseException e){
            return new BaseResponse(e.getStatus());
        }



        return new BaseResponse(BaseResponseStatus.SUCCESS_READ_TOILET,toiletTimeListByUser);
    }

}
