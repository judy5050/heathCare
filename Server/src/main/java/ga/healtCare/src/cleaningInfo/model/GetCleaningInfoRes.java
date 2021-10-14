package ga.healtCare.src.cleaningInfo.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class GetCleaningInfoRes {
    private Long cleaningIdx;
    private Integer liquidAmount;
    private Integer recentlyCleaning;
    private Integer isCleaned;
    private Integer autoCleaningCycle;

}
