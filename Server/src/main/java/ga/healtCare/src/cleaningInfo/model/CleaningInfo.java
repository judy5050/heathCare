package ga.healtCare.src.cleaningInfo.model;


import ga.healtCare.config.BaseEntity;
import ga.healtCare.src.group.models.GroupInfo;
import ga.healtCare.src.user.models.UserInfo;
import lombok.*;

import javax.persistence.*;

@NoArgsConstructor(access = AccessLevel.PUBLIC) // Unit Test 를 위해 PUBLIC
@EqualsAndHashCode(callSuper = false)
@Getter
@Setter
@Entity // 필수, Class 를 Database Table화 해주는 것이다
@Table(name = "CleaningInfo") // Table 이름을 명시해주지 않으면 class 이름을 Table 이름으로 대체한다.
public class CleaningInfo extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cleaningIdx")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "groupIdx")
    private GroupInfo groupInfo;


    @Column(name = "liquidAmount")
    private Integer liquidAmount;

    @Column(name = "recentlyCleaningTime")
    private Long recentlyCleaningTime;

    @Column(name = "isCleaned")
    private Integer isCleaned;

    @Column(name = "autoCleaningCycle")
    private Integer autoCleaningCycle;

    public CleaningInfo(GroupInfo groupInfo, PostCleaningInfoReq postCleaningInfoReq) {

        this.groupInfo=groupInfo;
        this.liquidAmount= postCleaningInfoReq.getLiquidAmount();
        this.recentlyCleaningTime= postCleaningInfoReq.getRecentlyCleaningTime();
        this.isCleaned= postCleaningInfoReq.getIsCleaned();
        this.autoCleaningCycle=postCleaningInfoReq.getAutoCleaningCycle();
    }
}
