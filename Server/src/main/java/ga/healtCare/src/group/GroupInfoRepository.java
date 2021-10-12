package ga.healtCare.src.group;

import ga.healtCare.src.group.models.GroupInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface GroupInfoRepository extends JpaRepository<GroupInfo,Long> {

    Optional<GroupInfo>findByLoginId(String loginId);
    Optional<GroupInfo>findById(Long groupIdx);
}
