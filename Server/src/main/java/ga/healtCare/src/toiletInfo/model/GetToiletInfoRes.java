package ga.healtCare.src.toiletInfo.model;


import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class GetToiletInfoRes {


    private Long groupIdx;
    private Integer startTime;
    private Integer endTime;

    public GetToiletInfoRes(ToiletInfo toiletInfo) {
        this.groupIdx=toiletInfo.getGroupInfo().getId();
        this.startTime=toiletInfo.getStartTime();
        this.endTime=toiletInfo.getEndTime();
    }
}
