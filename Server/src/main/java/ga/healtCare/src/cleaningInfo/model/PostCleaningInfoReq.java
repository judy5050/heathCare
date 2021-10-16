package ga.healtCare.src.cleaningInfo.model;


import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PUBLIC)
@Getter
public class PostCleaningInfoReq {
    private Integer liquidAmount;
    private Long recentlyCleaningTime;
    private Integer isCleaned;
    private Integer autoCleaningCycle;
}
