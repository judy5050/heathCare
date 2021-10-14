package ga.healtCare.src.cleaningInfo.model;


import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PUBLIC)
@Getter
public class PostCleaningInfoReq {
    private Integer liquidAmount;
    private Integer recentlyCleaningTime;
    private Integer isCleaned;
    private Integer autoCleaningCycle;
}
