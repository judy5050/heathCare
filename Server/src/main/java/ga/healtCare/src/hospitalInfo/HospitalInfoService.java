package ga.healtCare.src.hospitalInfo;


import ga.healtCare.src.hospitalInfo.model.GetHospitalInfoListRes;
import ga.healtCare.src.hospitalInfo.model.GetHospitalInfoRes;
import ga.healtCare.src.hospitalInfo.model.HospitalInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class HospitalInfoService {

    private final HospitalInfoRepository hospitalInfoRepository;
    /**
     * 병원정보 리스트로 전송하기
     */
    public GetHospitalInfoListRes getHospitalInfoList() {

//        PageRequest pageRequest=PageRequest.of(page,10);
        List<HospitalInfo> hospitalInfoRepositoryList = hospitalInfoRepository.findAll();
        List<GetHospitalInfoRes> getHospitalInfoRes = hospitalInfoRepositoryList.stream().map((HospitalInfo hospitalInfo) -> new GetHospitalInfoRes(hospitalInfo)).collect(Collectors.toList());
        return new GetHospitalInfoListRes(getHospitalInfoRes);
    }
}
