package ga.healtCare.src.user.models;

import ga.healtCare.config.BaseEntity;
import lombok.*;

import javax.persistence.*;

@NoArgsConstructor(access = AccessLevel.PUBLIC) // Unit Test 를 위해 PUBLIC
@EqualsAndHashCode(callSuper = false)
@Getter
@Setter
@Entity // 필수, Class 를 Database Table화 해주는 것이다
@Table(name = "UserInfo") // Table 이름을 명시해주지 않으면 class 이름을 Table 이름으로 대체한다.
public class UserInfo extends BaseEntity {
    /**
     * 유저 인덱스
     */
    @Id // PK를 의미하는 어노테이션
    @Column(name = "userIdx", nullable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    /**
     * 그룹인덱스
     */
    @Column(name = "groupIdx", nullable = false)
    private Long groupIdx;

    /**
     * 유저이름
     */
    @Column(name = "userName", nullable = false, length = 45)
    private String userName;

    /**
     * 생년월일
     */
    @Column(name = "birth", length = 45)
    private String birth;

    /**
     * 특이사항
     */
    @Column(name = "msg",length =100 )
    private String msg;


    /**
     * 로그인 아이디
     */
    @Column(name = "loginId", nullable = false, length = 45)
    private String loginId;

    /**
     * 비밀번호
     */
    @Column(name = "password", nullable = false)
    private String password;






//    public UserInfo(String email, String password, String nickname, String phoneNumber) {
//        this.email = email;
//        this.password = password;
//        this.nickname = nickname;
//        this.phoneNumber = phoneNumber;
//    }
}
