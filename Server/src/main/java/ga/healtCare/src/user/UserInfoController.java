package ga.healtCare.src.user;

import ga.healtCare.config.BaseResponse;
import ga.healtCare.config.BaseResponseStatus;
import ga.healtCare.config.BaseException;
import ga.healtCare.src.group.GroupInfoRepository;
import ga.healtCare.src.group.GroupInfoService;
import ga.healtCare.src.group.models.GroupInfo;
import ga.healtCare.src.user.models.*;
import ga.healtCare.src.user.models.*;
import ga.healtCare.src.user.models.*;
import ga.healtCare.utils.JwtService;
import ga.healtCare.src.user.models.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static ga.healtCare.utils.ValidationRegex.*;



@RestController
@RequiredArgsConstructor

public class UserInfoController {
    private final UserInfoProvider userInfoProvider;
    private final UserInfoService userInfoService;
    private final JwtService jwtService;
    private final GroupInfoService groupInfoService;
    private final UserInfoRepository userInfoRepository;
    private final GroupInfoRepository groupInfoRepository;
    /**
     * 그룹별 유저 등록 API
     */
    @PostMapping("/group/user")
    BaseResponse<PostUserRes> createGroupUser(@RequestBody PostUserReq postUserReq){
        UserInfo userInfo;
        try {
            Long groupId = jwtService.getUserId();
            GroupInfo groupInfo = groupInfoService.retrieveGroupInfoByGroupIdx(groupId);
            //유저 닉네임 중복여부 확인
            userInfoService.existUserCheck(postUserReq.getUserNickName());
            userInfo= userInfoRepository.save(new UserInfo(postUserReq.getUserNickName(), groupInfo, postUserReq.getUserNickName(), postUserReq.getBirth()));

        } catch (BaseException exception) {
            return new BaseResponse<>(exception.getStatus());
        }

        return new BaseResponse<>(BaseResponseStatus.SUCCESS_POST_GROUP_USER,new PostUserRes(userInfo.getId()));

    }
    /**
     * 그룹별 유저 정보 가져오기
     */
    @GetMapping("/group/userList")
    BaseResponse<GetUserInfoListRes> getUserInfoList() throws BaseException {
        //1.그룹 아이디 가져오기
        Long groupId = jwtService.getUserId();
        GroupInfo groupInfo = groupInfoService.retrieveGroupInfoByGroupIdx(groupId);

        //2.그룹별 회원정보 가져오기
        List<GetUserRes> userInfoList = userInfoService.getUserInfoList(groupInfo);
        GetUserInfoListRes getUserInfoListRes = new GetUserInfoListRes(userInfoList, Long.valueOf(userInfoList.size()));
        return new BaseResponse<>(BaseResponseStatus.SUCCESS_READ_GROUP_USER,getUserInfoListRes);
    }
    /**
     * 회원 정보 수정
     *
     */
    @PatchMapping("/groups/users/{userIdx}")
    public BaseResponse updateUserInfo(@PathVariable Long userIdx,@RequestBody PatchUserReq patchUserReq) throws BaseException {


        System.out.println("patchUserReq.() = " + patchUserReq.getUserNickName());
        try {
            Long groupId = jwtService.getUserId();
            userInfoService.patchUserInfo(userIdx,patchUserReq);
        }catch (BaseException e){
            return new BaseResponse(e.getStatus());
        }




        return new BaseResponse(BaseResponseStatus.SUCCESS_PATCH_USER);
    }




//    /**
//     * 회원가입 API
//     */
//
//    @PostMapping("")
//    public BaseResponse postUsers(@RequestBody PostUserReq postUserReq){
//
//            // 1. Body Parameter Validation
//            if (postUserReq.getLoginId() == null || postUserReq.getLoginId().length() == 0) {
//                return new BaseResponse<>(BaseResponseStatus.EMPTY_ID);
//            }
//
//            if (postUserReq.getPassword() == null || postUserReq.getPassword().length() == 0) {
//                return new BaseResponse<>(BaseResponseStatus.EMPTY_PASSWORD);
//            }
//
//
//            // 2. Post UserInfo
//            try {
//                PostUserRes postUserRes = userInfoService.createUserInfo(parameters);
//                return new BaseResponse<>(BaseResponseStatus.SUCCESS_POST_USER, postUserRes);
//            } catch (BaseException exception) {
//                return new BaseResponse<>(exception.getStatus());
//            }
//        }
//
//
//    }
//
//
//    /**
//     * 회원 전체 조회 API
//     * [GET] /users
//     * 회원 닉네임 검색 조회 API
//     * [GET] /users?word=
//     * @return BaseResponse<List<GetUsersRes>>
//     */
//    @ResponseBody
//    @GetMapping("") // (GET) 127.0.0.1:9000/users
//    public BaseResponse<List<GetUsersRes>> getUsers(@RequestParam(required = false) String word) {
//        try {
//            List<GetUsersRes> getUsersResList = userInfoProvider.retrieveUserInfoList(word);
//            if (word == null) {
//                return new BaseResponse<>(BaseResponseStatus.SUCCESS_READ_USERS, getUsersResList);
//            } else {
//                return new BaseResponse<>(BaseResponseStatus.SUCCESS_READ_SEARCH_USERS, getUsersResList);
//            }
//        } catch (BaseException exception) {
//            return new BaseResponse<>(exception.getStatus());
//        }
//    }
//
//    /**
//     * 회원 조회 API
//     * [GET] /users/:userId
//     * @PathVariable userId
//     * @return BaseResponse<GetUserRes>
//     */
//    @ResponseBody
//    @GetMapping("/{userId}")
//    public BaseResponse<GetUserRes> getUser(@PathVariable Integer userId) {
//        if (userId == null || userId <= 0) {
//            return new BaseResponse<>(BaseResponseStatus.EMPTY_USERID);
//        }
//
//        try {
//            GetUserRes getUserRes = userInfoProvider.retrieveUserInfo(userId);
//            return new BaseResponse<>(BaseResponseStatus.SUCCESS_READ_USER, getUserRes);
//        } catch (BaseException exception) {
//            return new BaseResponse<>(exception.getStatus());
//        }
//    }
//
//    /**
//     * 회원가입 API
//     * [POST] /users
//     * @RequestBody PostUserReq
//     * @return BaseResponse<PostUserRes>
//     */
//    @ResponseBody
//    @PostMapping("")
//    public BaseResponse<PostUserRes> postUsers(@RequestBody PostUserReq parameters) {
//        // 1. Body Parameter Validation
//        if (parameters.getEmail() == null || parameters.getEmail().length() == 0) {
//            return new BaseResponse<>(BaseResponseStatus.EMPTY_EMAIL);
//        }
//        if (!isRegexEmail(parameters.getEmail())){
//            return new BaseResponse<>(BaseResponseStatus.INVALID_EMAIL);
//        }
//        if (parameters.getPassword() == null || parameters.getPassword().length() == 0) {
//            return new BaseResponse<>(BaseResponseStatus.EMPTY_PASSWORD);
//        }
//        if (parameters.getConfirmPassword() == null || parameters.getConfirmPassword().length() == 0) {
//            return new BaseResponse<>(BaseResponseStatus.EMPTY_CONFIRM_PASSWORD);
//        }
//        if (!parameters.getPassword().equals(parameters.getConfirmPassword())) {
//            return new BaseResponse<>(BaseResponseStatus.DO_NOT_MATCH_PASSWORD);
//        }
//        if (parameters.getNickname() == null || parameters.getNickname().length() == 0) {
//            return new BaseResponse<>(BaseResponseStatus.EMPTY_NICKNAME);
//        }
//
//        // 2. Post UserInfo
//        try {
//            PostUserRes postUserRes = userInfoService.createUserInfo(parameters);
//            return new BaseResponse<>(BaseResponseStatus.SUCCESS_POST_USER, postUserRes);
//        } catch (BaseException exception) {
//            return new BaseResponse<>(exception.getStatus());
//        }
//    }
//
//    /**
//     * 회원 정보 수정 API
//     * [PATCH] /users/:userId
//     * @PathVariable userId
//     * @RequestBody PatchUserReq
//     * @return BaseResponse<PatchUserRes>
//     */
//    @ResponseBody
//    @PatchMapping("/{userId}")
//    public BaseResponse<PatchUserRes> patchUsers(@PathVariable Integer userId, @RequestBody PatchUserReq parameters) {
//        if (userId == null || userId <= 0) {
//            return new BaseResponse<>(BaseResponseStatus.EMPTY_USERID);
//        }
//
//        if (!parameters.getPassword().equals(parameters.getConfirmPassword())) {
//            return new BaseResponse<>(BaseResponseStatus.DO_NOT_MATCH_PASSWORD);
//        }
//
//        try {
//            return new BaseResponse<>(BaseResponseStatus.SUCCESS_PATCH_USER, userInfoService.updateUserInfo(userId, parameters));
//        } catch (BaseException exception) {
//            return new BaseResponse<>(exception.getStatus());
//        }
//    }
//
//    /**
//     * 로그인 API
//     * [POST] /users/login
//     * @RequestBody PostLoginReq
//     * @return BaseResponse<PostLoginRes>
//     */
//    @PostMapping("/login")
//    public BaseResponse<PostLoginRes> login(@RequestBody PostLoginReq parameters) {
//        // 1. Body Parameter Validation
//        if (parameters.getEmail() == null || parameters.getEmail().length() == 0) {
//            return new BaseResponse<>(BaseResponseStatus.EMPTY_EMAIL);
//        } else if (!isRegexEmail(parameters.getEmail())) {
//            return new BaseResponse<>(BaseResponseStatus.INVALID_EMAIL);
//        } else if (parameters.getPassword() == null || parameters.getPassword().length() == 0) {
//            return new BaseResponse<>(BaseResponseStatus.EMPTY_PASSWORD);
//        }
//
//        // 2. Login
//        try {
//            PostLoginRes postLoginRes = userInfoProvider.login(parameters);
//            return new BaseResponse<>(BaseResponseStatus.SUCCESS_LOGIN, postLoginRes);
//        } catch (BaseException exception) {
//            return new BaseResponse<>(exception.getStatus());
//        }
//    }
//
//    /**
//     * 회원 탈퇴 API
//     * [DELETE] /users/:userId
//     * @PathVariable userId
//     * @return BaseResponse<Void>
//     */
//    @DeleteMapping("/{userId}")
//    public BaseResponse<Void> deleteUsers(@PathVariable Integer userId) {
//        if (userId == null || userId <= 0) {
//            return new BaseResponse<>(BaseResponseStatus.EMPTY_USERID);
//        }
//
//        try {
//            userInfoService.deleteUserInfo(userId);
//            return new BaseResponse<>(BaseResponseStatus.SUCCESS_DELETE_USER);
//        } catch (BaseException exception) {
//            return new BaseResponse<>(exception.getStatus());
//        }
//    }
//
//    /**
//     * JWT 검증 API
//     * [GET] /users/jwt
//     * @return BaseResponse<Void>
//     */
//    @GetMapping("/jwt")
//    public BaseResponse<Void> jwt() {
//        try {
//            int userId = jwtService.getUserId();
//            userInfoProvider.retrieveUserInfo(userId);
//            return new BaseResponse<>(BaseResponseStatus.SUCCESS_JWT);
//        } catch (BaseException exception) {
//            return new BaseResponse<>(exception.getStatus());
//        }
//    }
}