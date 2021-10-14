package ga.healtCare.src.toiletInfo;

import ga.healtCare.src.toiletInfo.model.ToiletInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ToiletInfoRepository extends JpaRepository<ToiletInfo,Long> {


    @Query("select t from ToiletInfo  t where t.groupInfo.id =:groupIdx")
    Page<ToiletInfo> findAllById(Pageable pageable, @Param("groupIdx") Long groupIdx);

    @Query("select t from ToiletInfo  t where t.userInfo.id =:userIdx")
    Page<ToiletInfo> findAllByUserIdx(Pageable pageable,@Param("userIdx") Long userIdx);
}
