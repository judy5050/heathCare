package ga.healtCare.src.group.models;

import ga.healtCare.config.BaseEntity;
import ga.healtCare.src.cleaningInfo.model.CleaningInfo;
import ga.healtCare.src.user.models.UserInfo;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PUBLIC) // Unit Test 를 위해 PUBLIC
@EqualsAndHashCode(callSuper = false)
@Getter
@Setter
@Entity // 필수, Class 를 Database Table화 해주는 것이다
@Table(name = "GroupInfo") // Table 이름을 명시해주지 않으면 class 이름을 Table 이름으로 대체한다.
public class GroupInfo extends BaseEntity
{

    /**
     * 그룹 인덱스
     */
    @Id // PK를 의미하는 어노테이션
    @Column(name = "groupIdx", nullable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    /**
     * 유저 인덱스
     */

    @OneToMany(mappedBy = "groupInfo")
    private List<UserInfo> userInfoList=new ArrayList<>();

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

    /**
     * 청소정보
     * @param loginId
     * @param password
     */
    @OneToMany(mappedBy = "groupInfo")
    private List<CleaningInfo> cleaningInfoList=new ArrayList<>();

    public GroupInfo(String loginId, String password) {
        this.loginId=loginId;
        this.password=password;
    }
}
