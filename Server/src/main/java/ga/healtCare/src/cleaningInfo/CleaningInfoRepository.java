package ga.healtCare.src.cleaningInfo;

import ga.healtCare.src.cleaningInfo.model.CleaningInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CleaningInfoRepository extends JpaRepository<CleaningInfo,Long> {

    @Query("select c from CleaningInfo  c where c.groupInfo.id =:groupIdx order by c.createdAt desc ")
    List<CleaningInfo> recentlyCleaningInfo(@Param("groupIdx") Long groupIdx);
}
