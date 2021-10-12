package ga.healtCare.src.group;

import ga.healtCare.config.BaseException;
import ga.healtCare.config.BaseResponseStatus;
import ga.healtCare.config.secret.Secret;
import ga.healtCare.src.group.models.GroupInfo;
import ga.healtCare.src.group.models.PostGroupReq;
import ga.healtCare.src.group.models.PostGroupRes;
import ga.healtCare.src.user.models.*;
import ga.healtCare.utils.AES128;
import ga.healtCare.utils.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
@RequiredArgsConstructor
public class GroupInfoService {

    private final GroupInfoRepository groupInfoRepository;
    private final JwtService jwtService;
    /**
     * 회원가입
     * @param postGroupReq
     * @return PostGroupRes
     * @throws BaseException
     */
    public PostGroupRes createGroupInfo(PostGroupReq postGroupReq) throws BaseException {
        UserInfo existsUserInfo = null;
        try {
            // 1-1. 이미 존재하는 회원이 있는지 조회
            retrieveUserInfoByUserId(postGroupReq.getLoginId());
        } catch (BaseException exception) {
//            // 1-2. 이미 존재하는 회원이 없다면 그대로 진행
//            if (exception.getStatus() != BaseResponseStatus.NOT_FOUND_USER) {
                throw exception;
//            }
        }
        // 1-3. 이미 존재하는 회원이 있다면 return DUPLICATED_USER
//        if (existsUserInfo != null) {
//            throw new BaseException(BaseResponseStatus.DUPLICATED_USER);
//        }

        // 2. 유저 정보 생성
        String loginId = postGroupReq.getLoginId();
        String password;
        try {
            password = new AES128(Secret.USER_INFO_PASSWORD_KEY).encrypt(postGroupReq.getPassword());
        } catch (Exception ignored) {
            throw new BaseException(BaseResponseStatus.FAILED_TO_POST_USER);
        }
        GroupInfo groupInfo = new GroupInfo(loginId,password);

        // 3. 유저 정보 저장
        try {
            groupInfo = groupInfoRepository.save(groupInfo);
        } catch (Exception exception) {
            throw new BaseException(BaseResponseStatus.FAILED_TO_POST_USER);
        }

        // 4. JWT 생성
        String jwt = jwtService.createJwt(groupInfo.getId());

        // 5. UserInfoLoginRes로 변환하여 return
        Long id = groupInfo.getId();
        return new PostGroupRes(jwt);
    }
    /**
     * 회원 중복여부 확인
     */

    GroupInfo retrieveUserInfoByUserId(String userId) throws BaseException{

        GroupInfo groupInfo  = groupInfoRepository.findByLoginId(userId).orElse(null);
        System.out.println("groupInfo = " + groupInfo.getId());
        if(groupInfo!=null){
            System.out.println("groupInfo = " + groupInfo.getId());
            throw new BaseException(BaseResponseStatus.FAILED_TO_POST_USER);
        }

            return groupInfo;
    }
    //회원 아이디 기준 회원 정보 가져오기
    GroupInfo retrieveGroupInfoByUserId(String userId) throws BaseException{

        GroupInfo groupInfo  = groupInfoRepository.findByLoginId(userId).orElse(null);
        if(groupInfo==null){
            System.out.println("groupInfo = " + groupInfo.getId());
            throw new BaseException(BaseResponseStatus.NOT_FOUND_USER);
        }

        return groupInfo;
    }



    /**
     * 그룹 인덱스 기준 그룹 정보 가져오기
     * @param groupIdx
     * @return
     * @throws BaseException
     */
    public GroupInfo retrieveGroupInfoByGroupIdx(Long groupIdx) throws BaseException{

        GroupInfo groupInfo  = groupInfoRepository.findById(groupIdx).orElse(null);
        if(groupInfo==null){
            throw new BaseException(BaseResponseStatus.NOT_FOUND_USER);
        }

        return groupInfo;
    }


    /**
     * 로그인
     * @param postLoginReq
     * @return PostLoginRes
     * @throws BaseException
     */
    public PostLoginRes login(PostLoginReq postLoginReq) throws BaseException {



        // 1. DB에서 email로 UserInfo 조회
        // 2. UserInfo에서 password 추출
        String password;
        GroupInfo groupInfo;
        try {
            groupInfo = retrieveGroupInfoByUserId(postLoginReq.getUserId());
        }catch (Exception e){
            throw new BaseException(BaseResponseStatus.NOT_FOUND_USER);
        }
        try {

           password = new AES128(Secret.USER_INFO_PASSWORD_KEY).decrypt(groupInfo.getPassword());
        } catch (Exception ignored) {
            throw new BaseException(BaseResponseStatus.FAILED_TO_LOGIN);
        }

        // 3. 비밀번호 일치 여부 확인
        if (!postLoginReq.getPassword().equals(password)) {
            throw new BaseException(BaseResponseStatus.WRONG_PASSWORD);
        }

        // 3. Create JWT
        String jwt = jwtService.createJwt(groupInfo.getId());

        // 4. PostLoginRes 변환하여 return
        return new PostLoginRes(jwt);
    }




}
