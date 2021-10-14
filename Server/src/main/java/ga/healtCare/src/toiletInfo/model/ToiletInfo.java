package ga.healtCare.src.toiletInfo.model;

import ga.healtCare.src.group.models.GroupInfo;
import ga.healtCare.src.user.models.UserInfo;
import lombok.*;

import javax.persistence.*;

@NoArgsConstructor(access = AccessLevel.PUBLIC) // Unit Test 를 위해 PUBLIC
@EqualsAndHashCode(callSuper = false)
@Getter
@Setter
@Entity // 필수, Class 를 Database Table화 해주는 것이다
@Table(name = "ToiletInfo") // Table 이름을 명시해주지 않으면 class 이름을 Table 이름으로 대체한다.
public class ToiletInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "toiletIdx")
    private Long id;

    @Column(name = "startTime")
    private Integer startTime;

    @Column(name = "endTime")
    private Integer endTime;

    @ManyToOne
    @JoinColumn(name = "groupIdx")
    private GroupInfo groupInfo;

    @ManyToOne
    @JoinColumn(name = "userIdx")
    private UserInfo userInfo;


    public ToiletInfo(PostToiletInfoReq postToiletInfoReq, GroupInfo groupInfo) {
        this.startTime= postToiletInfoReq.getStartTime();
        this.endTime= postToiletInfoReq.getEndTime();
        this.groupInfo=groupInfo;
    }
}