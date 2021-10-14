package ga.healtCare.src.cleaningInfo.model;


import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class PostCleaningInfoRes {
    private Long cleaningIdx;
    private Long groupIdx;
    private Integer liquidAmount;
    private Integer recentlyCleaningTime;
    private Integer isCleaned;
    private Integer autoCleaningCycle;
}
