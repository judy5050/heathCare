package ga.healtCare.src.group;

import ga.healtCare.config.BaseException;
import ga.healtCare.config.BaseResponse;
import ga.healtCare.config.BaseResponseStatus;
import ga.healtCare.src.group.models.GroupInfo;
import ga.healtCare.src.group.models.PostGroupReq;
import ga.healtCare.src.group.models.PostGroupRes;
import ga.healtCare.src.user.models.*;
import ga.healtCare.utils.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static ga.healtCare.utils.ValidationRegex.isRegexEmail;

@RestController
@RequiredArgsConstructor
public class GroupInfoController {


    private final GroupInfoService groupInfoService;
    private final JwtService jwtService;
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




    /**
     * 로그인 API
     * [POST] /users/login
     * @RequestBody PostLoginReq
     * @return BaseResponse<PostLoginRes>
     */
    @PostMapping("/login")
    public BaseResponse<PostLoginRes> login(@RequestBody PostLoginReq postLoginReq) {
        // 1. Body Parameter Validation
        if (postLoginReq.getUserId() == null || postLoginReq.getUserId().length() == 0) {
            return new BaseResponse<>(BaseResponseStatus.EMPTY_ID);
        }
        else if (postLoginReq.getPassword() == null || postLoginReq.getPassword().length() == 0) {
            return new BaseResponse<>(BaseResponseStatus.EMPTY_PASSWORD);
        }

        // 2. Login
        try {
            PostLoginRes postLoginRes = groupInfoService.login(postLoginReq);
            return new BaseResponse<>(BaseResponseStatus.SUCCESS_LOGIN, postLoginRes);
        } catch (BaseException exception) {
            return new BaseResponse<>(exception.getStatus());
        }
    }

    /**
     * 자동로그인
     */
    @GetMapping("/jwt")
    public BaseResponse<PostLoginRes> jwt() {
        try {
            Long userId = jwtService.getUserId();
            GroupInfo groupInfo = groupInfoService.retrieveGroupInfoByGroupIdx(userId);
            return new BaseResponse<>(BaseResponseStatus.SUCCESS_JWT);
        } catch (BaseException exception) {
            return new BaseResponse<>(exception.getStatus());
        }
    }

}

