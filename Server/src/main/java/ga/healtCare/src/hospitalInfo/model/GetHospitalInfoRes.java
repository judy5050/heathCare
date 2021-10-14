package ga.healtCare.src.hospitalInfo.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class GetHospitalInfoRes {
    private Long hospitalIdx;
    private String hospitalName;
    private Double latitude;
    private Double longitude;


    public GetHospitalInfoRes(HospitalInfo hospitalInfo) {
        this.hospitalIdx=hospitalInfo.getId();
        this.hospitalName = hospitalInfo.getHospitalName();
        this.latitude = hospitalInfo.getLatitude();
        this.longitude = hospitalInfo.getLongitude();
    }
}
