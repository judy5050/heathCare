package ga.healtCare.src.user;

import ga.healtCare.config.BaseResponseStatus;
import ga.healtCare.config.secret.Secret;
import ga.healtCare.src.user.models.*;
import ga.healtCare.src.user.models.*;
import ga.healtCare.src.user.models.*;
import ga.healtCare.utils.AES128;
import ga.healtCare.config.BaseException;
import ga.healtCare.utils.JwtService;
import ga.healtCare.src.user.models.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserInfoProvider {
    private final UserInfoRepository userInfoRepository;
    private final JwtService jwtService;



//    /**
//     * 전체 회원 조회
//     * @return List<UserInfoRes>
//     * @throws BaseException
//     */
//    public List<GetUsersRes> retrieveUserInfoList(String word) throws BaseException {
//        // 1. DB에서 전체 UserInfo 조회
//        List<UserInfo> userInfoList;
//        try {
//            if (word == null) { // 전체 조회
//                userInfoList = userInfoRepository.findByStatus("ACTIVE");
//            } else { // 검색 조회
//                userInfoList = userInfoRepository.findByStatusAndNicknameIsContaining("ACTIVE", word);
//            }
//        } catch (Exception ignored) {
//            throw new BaseException(BaseResponseStatus.FAILED_TO_GET_USER);
//        }
//
//        // 2. UserInfoRes로 변환하여 return
//        return userInfoList.stream().map(userInfo -> {
//            int id = userInfo.getId();
//            String email = userInfo.getEmail();
//            return new GetUsersRes(id, email);
//        }).collect(Collectors.toList());
//    }
//
//    /**
//     * 회원 조회
//     * @param userId
//     * @return UserInfoDetailRes
//     * @throws BaseException
//     */
//    public GetUserRes retrieveUserInfo(int userId) throws BaseException {
//        // 1. DB에서 userId로 UserInfo 조회
//        UserInfo userInfo = retrieveUserInfoByUserId(userId);
//
//        // 2. UserInfoRes로 변환하여 return
//        int id = userInfo.getId();
//        String email = userInfo.getEmail();
//        String nickname = userInfo.getNickname();
//        String phoneNumber = userInfo.getPhoneNumber();
//        return new GetUserRes(id, email, nickname, phoneNumber);
//    }
//
//    /**
//     * 로그인
//     * @param postLoginReq
//     * @return PostLoginRes
//     * @throws BaseException
//     */
//    public PostLoginRes login(PostLoginReq postLoginReq) throws BaseException {
//        // 1. DB에서 email로 UserInfo 조회
//        UserInfo userInfo = retrieveUserInfoByEmail(postLoginReq.getEmail());
//
//        // 2. UserInfo에서 password 추출
//        String password;
//        try {
//            password = new AES128(Secret.USER_INFO_PASSWORD_KEY).decrypt(userInfo.getPassword());
//        } catch (Exception ignored) {
//            throw new BaseException(BaseResponseStatus.FAILED_TO_LOGIN);
//        }
//
//        // 3. 비밀번호 일치 여부 확인
//        if (!postLoginReq.getPassword().equals(password)) {
//            throw new BaseException(BaseResponseStatus.WRONG_PASSWORD);
//        }
//
//        // 3. Create JWT
//        String jwt = jwtService.createJwt(userInfo.getId());
//
//        // 4. PostLoginRes 변환하여 return
//        int id = userInfo.getId();
//        return new PostLoginRes(id, jwt);
//    }
//
//    /**
//     * 회원 조회
//     * @param userId
//     * @return UserInfo
//     * @throws BaseException
//     */
//    public UserInfo retrieveUserInfoByUserId(int userId) throws BaseException {
//        // 1. DB에서 UserInfo 조회
//        UserInfo userInfo;
//        try {
//            userInfo = userInfoRepository.findById(userId).orElse(null);
//        } catch (Exception ignored) {
//            throw new BaseException(BaseResponseStatus.FAILED_TO_GET_USER);
//        }
//
//        // 2. 존재하는 회원인지 확인
//        if (userInfo == null || !userInfo.getStatus().equals("ACTIVE")) {
//            throw new BaseException(BaseResponseStatus.NOT_FOUND_USER);
//        }
//
//        // 3. UserInfo를 return
//        return userInfo;
//    }
//
//    /**
//     * 회원 조회
//     * @param email
//     * @return UserInfo
//     * @throws BaseException
//     */
//    public UserInfo retrieveUserInfoByEmail(String email) throws BaseException {
//        // 1. email을 이용해서 UserInfo DB 접근
//        List<UserInfo> existsUserInfoList;
//        try {
//            existsUserInfoList = userInfoRepository.findByEmailAndStatus(email, "ACTIVE");
//        } catch (Exception ignored) {
//            throw new BaseException(BaseResponseStatus.FAILED_TO_GET_USER);
//        }
//
//        // 2. 존재하는 UserInfo가 있는지 확인
//        UserInfo userInfo;
//        if (existsUserInfoList != null && existsUserInfoList.size() > 0) {
//            userInfo = existsUserInfoList.get(0);
//        } else {
//            throw new BaseException(BaseResponseStatus.NOT_FOUND_USER);
//        }
//
//        // 3. UserInfo를 return
//        return userInfo;
//    }
}
