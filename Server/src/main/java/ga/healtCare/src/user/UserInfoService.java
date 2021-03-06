package ga.healtCare.src.user;

import ga.healtCare.config.BaseResponseStatus;
import ga.healtCare.config.secret.Secret;
import ga.healtCare.src.group.models.GroupInfo;
import ga.healtCare.src.user.models.*;
import ga.healtCare.src.user.models.*;
import ga.healtCare.src.user.models.*;
import ga.healtCare.utils.JwtService;
import ga.healtCare.utils.AES128;
import ga.healtCare.config.BaseException;
import ga.healtCare.src.user.models.*;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@RequiredArgsConstructor
@Service
public class UserInfoService {
    private final UserInfoRepository userInfoRepository;
    private final UserInfoProvider userInfoProvider;
    private final JwtService jwtService;

    /**
     * 유저 닉네임 기준으로 유저 유무 확인
     */
    public void  existUserCheck(String userNickName) throws BaseException {
        UserInfo userInfo = getUserInfo(userNickName).orElse(null);
        if(userInfo!=null){
            throw new BaseException(BaseResponseStatus.DUPLICATED_NICKNAME);
        }
    }

    /**
     * 유저 닉네임기준으로 유저 가져오기
     *
     */
    public Optional<UserInfo> getUserInfo(String userNickName){
        Optional<UserInfo> userInfo = userInfoRepository.findByUserNickName(userNickName);

        return userInfo;
    }


    /**
     * 그룹 인덱스 기준으로 유저 정보 가져오기
     */
    public List<GetUserRes> getUserInfoList(GroupInfo groupInfo) {
        List<GetUserRes> userInfoList = userInfoRepository.findAllByGroupIdx(groupInfo.getId(),"N");
        System.out.println("userInfoList = " + userInfoList);
        return userInfoList;
    }

    /**
     * 회원 정보 수정
     * @param userIdx
     */
    public void patchUserInfo(Long userIdx,PatchUserReq patchUserReq) throws BaseException {

        UserInfo userInfo = userInfoRepository.findById(userIdx).orElse(null);
        if(userInfo==null||userInfo.getIsDeleted().equals("Y")){
            throw new BaseException(BaseResponseStatus.NOT_FOUND_USER);
        }
        userInfo.setUserNickName(patchUserReq.getUserNickName());
        userInfoRepository.save(userInfo);


    }

    /**
     * 회원 삭제 API
     */
    public void deleteUserInfo(Long userIdx) throws BaseException {

        UserInfo userInfo = userInfoRepository.findById(userIdx).orElse(null);
        if(userInfo==null||userInfo.getIsDeleted().equals("Y")){
            throw  new BaseException(BaseResponseStatus.NOT_FOUND_USER);
        }

        userInfo.setIsDeleted("Y");
        userInfoRepository.save(userInfo);
    }


//    @Autowired
//    public UserInfoService(UserInfoRepository userInfoRepository, UserInfoProvider userInfoProvider, JwtService jwtService) {
//        this.userInfoRepository = userInfoRepository;
//        this.userInfoProvider = userInfoProvider;
//        this.jwtService = jwtService;
//    }
//
//    /**
//     * 회원가입
//     * @param postUserReq
//     * @return PostUserRes
//     * @throws BaseException
//     */
//    public PostUserRes createUserInfo(PostUserReq postUserReq) throws BaseException {
//        UserInfo existsUserInfo = null;
//        try {
//            // 1-1. 이미 존재하는 회원이 있는지 조회
//            existsUserInfo = userInfoProvider.retrieveUserInfoByEmail(postUserReq.getEmail());
//        } catch (BaseException exception) {
//            // 1-2. 이미 존재하는 회원이 없다면 그대로 진행
//            if (exception.getStatus() != BaseResponseStatus.NOT_FOUND_USER) {
//                throw exception;
//            }
//        }
//        // 1-3. 이미 존재하는 회원이 있다면 return DUPLICATED_USER
//        if (existsUserInfo != null) {
//            throw new BaseException(BaseResponseStatus.DUPLICATED_USER);
//        }
//
//        // 2. 유저 정보 생성
//        String email = postUserReq.getEmail();
//        String nickname = postUserReq.getNickname();
//        String phoneNumber = postUserReq.getPhoneNumber();
//        String password;
//        try {
//            password = new AES128(Secret.USER_INFO_PASSWORD_KEY).encrypt(postUserReq.getPassword());
//        } catch (Exception ignored) {
//            throw new BaseException(BaseResponseStatus.FAILED_TO_POST_USER);
//        }
//        UserInfo userInfo = new UserInfo(email, password, nickname, phoneNumber);
//
//        // 3. 유저 정보 저장
//        try {
//            userInfo = userInfoRepository.save(userInfo);
//        } catch (Exception exception) {
//            throw new BaseException(BaseResponseStatus.FAILED_TO_POST_USER);
//        }
//
//        // 4. JWT 생성
//        String jwt = jwtService.createJwt(userInfo.getId());
//
//        // 5. UserInfoLoginRes로 변환하여 return
//        int id = userInfo.getId();
//        return new PostUserRes(id, jwt);
//    }
//
//    /**
//     * 회원 정보 수정 (POST uri 가 겹쳤을때의 예시 용도)
//     * @param patchUserReq
//     * @return PatchUserRes
//     * @throws BaseException
//     */
//    public PatchUserRes updateUserInfo(@NonNull Integer userId, PatchUserReq patchUserReq) throws BaseException {
//        try {
//            String email = patchUserReq.getEmail().concat("_edited");
//            String nickname = patchUserReq.getNickname().concat("_edited");
//            String phoneNumber = patchUserReq.getPhoneNumber().concat("_edited");
//            return new PatchUserRes(email, nickname, phoneNumber);
//        } catch (Exception ignored) {
//            throw new BaseException(BaseResponseStatus.FAILED_TO_PATCH_USER);
//        }
//    }
//
//    /**
//     * 회원 탈퇴
//     * @param userId
//     * @throws BaseException
//     */
//    public void deleteUserInfo(int userId) throws BaseException {
//        // 1. 존재하는 UserInfo가 있는지 확인 후 저장
//        UserInfo userInfo = userInfoProvider.retrieveUserInfoByUserId(userId);
//
//        // 2-1. 해당 UserInfo를 완전히 삭제
////        try {
////            userInfoRepository.delete(userInfo);
////        } catch (Exception exception) {
////            throw new BaseException(DATABASE_ERROR_USER_INFO);
////        }
//
//        // 2-2. 해당 UserInfo의 status를 INACTIVE로 설정
//        userInfo.setStatus("INACTIVE");
//        try {
//            userInfoRepository.save(userInfo);
//        } catch (Exception ignored) {
//            throw new BaseException(BaseResponseStatus.FAILED_TO_DELETE_USER);
//        }
//    }
}