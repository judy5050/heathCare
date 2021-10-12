package ga.healtCare.src.group;

import ga.healtCare.config.BaseException;
import ga.healtCare.config.BaseResponse;
import ga.healtCare.config.BaseResponseStatus;
import ga.healtCare.src.group.models.PostGroupReq;
import ga.healtCare.src.group.models.PostGroupRes;
import ga.healtCare.src.user.models.PostUserReq;
import ga.healtCare.src.user.models.PostUserRes;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class GroupInfoController {


    private final GroupInfoService groupInfoService;
    /**
     * 회원가입 API
     */

    @PostMapping("/group")
    public BaseResponse postUsers(@RequestBody PostGroupReq postGroupReq){

        // 1. Body Parameter Validation
        if (postGroupReq.getLoginId() == null || postGroupReq.getLoginId().length() == 0) {
            return new BaseResponse<>(BaseResponseStatus.EMPTY_ID);
        }

        if (postGroupReq.getPassword() == null || postGroupReq.getPassword().length() == 0) {
            return new BaseResponse<>(BaseResponseStatus.EMPTY_PASSWORD);
        }


        // 2. Post GroupInfo
        try {
            PostGroupRes postGroupRes = groupInfoService.createGroupInfo(postGroupReq);
            return new BaseResponse<>(BaseResponseStatus.SUCCESS_POST_USER);
        } catch (BaseException exception) {
            return new BaseResponse<>(exception.getStatus());
        }
    }



}

