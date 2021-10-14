package ga.healtCare.src.toiletInfo.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@AllArgsConstructor
@Getter
public class GetToiletInfoListRes {
    private List<GetToiletInfoRes>getToiletInfoResList;
}
