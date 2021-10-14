package ga.healtCare.src.hospitalInfo;


import ga.healtCare.config.BaseException;
import ga.healtCare.config.BaseResponse;
import ga.healtCare.config.BaseResponseStatus;
import ga.healtCare.src.hospitalInfo.model.GetHospitalInfoListRes;
import ga.healtCare.utils.JwtService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class HospitalInfoController {
    private final HospitalInfoService hospitalInfoService;
    private final JwtService jwtService;
    /**
     * 병원정보 넘기기
     *
     */

    @GetMapping("/hospitalInfo")
    public BaseResponse<GetHospitalInfoListRes> readHospitalInfo(){


        try {
            jwtService.getUserId();
        }catch (BaseException e){
            return new BaseResponse(e.getStatus());
        }
        GetHospitalInfoListRes hospitalInfoList = hospitalInfoService.getHospitalInfoList();

        return new BaseResponse(BaseResponseStatus.SUCCESS_READ_HOSPITAL,hospitalInfoList);
    }

}
