package ga.healtCare.src.user.models;

import ga.healtCare.config.BaseEntity;
import ga.healtCare.src.group.models.GroupInfo;
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
    private Long id;

    /**
     * 그룹인덱스
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "groupIdx")
    private GroupInfo groupInfo;

    /**
     * 유저이름
     */
    @Column(name = "userName", nullable = false, length = 45)
    private String userName;

    /**
     * 유저닉네임
     */
    @Column(name = "userNickName", nullable = false, length = 45)
    private String userNickName;

    /**
     * 생년월일
     */
    @Column(name = "birth", nullable = false, length = 45)
    private String birth;

    @Column(name = "photoIdx")
    private Integer photoIdx;


    /**
     *
     * @param userNickName
     * @param
     * @param userName
     * @param birth
     */
    public UserInfo(String userNickName, GroupInfo groupInfo, String userName, String birth,Integer photoIdx) {
        this.userNickName=userNickName;
        this.userName=userName;
        this.groupInfo=groupInfo;
        this.birth=birth;
        this.photoIdx=photoIdx;

    }


//    public UserInfo(String email, String password, String nickname, String phoneNumber) {
//        this.email = email;
//        this.password = password;
//        this.nickname = nickname;
//        this.phoneNumber = phoneNumber;
//    }
}
