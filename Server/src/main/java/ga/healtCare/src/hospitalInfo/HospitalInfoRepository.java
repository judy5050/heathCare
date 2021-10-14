package ga.healtCare.src.hospitalInfo;

import ga.healtCare.src.hospitalInfo.model.HospitalInfo;
import org.springframework.data.jpa.repository.JpaRepository;



public interface HospitalInfoRepository extends JpaRepository<HospitalInfo,Long> {
}
