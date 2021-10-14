package ga.healtCare.src.toiletInfo.model;


import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class GetToiletInfoUserRes {
    private Long userIdx;
    private Integer startTime;
    private Integer endTime;

    public GetToiletInfoUserRes(ToiletInfo toiletInfo) {
        this.userIdx=toiletInfo.getUserInfo().getId();
        this.startTime=toiletInfo.getStartTime();
        this.endTime=toiletInfo.getEndTime();
    }
}
