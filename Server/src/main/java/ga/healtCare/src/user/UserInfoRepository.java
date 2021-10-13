package ga.healtCare.src.user;

import ga.healtCare.src.group.models.GroupInfo;
import ga.healtCare.src.user.models.GetUserRes;
import ga.healtCare.src.user.models.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository // => JPA => Hibernate => ORM => Database 객체지향으로 접근하게 해주는 도구이다
public interface UserInfoRepository extends JpaRepository<UserInfo, Long> {
    Optional<UserInfo> findByUserNickName(String userNickName);
    List<UserInfo> findAllById(Long groupId);

    @Query("select new ga.healtCare.src.user.models.GetUserRes(m.id,m.userName,m.userNickName,m.birth) from UserInfo  m where m.groupInfo = :groupInfo")
    List<GetUserRes> findAllByGroupIdx(@Param("groupInfo") GroupInfo groupInfo);
    //    List<UserInfo> findByStatus(String status);
//    List<UserInfo> findByEmailAndStatus(String email, String status);
//    List<UserInfo> findByStatusAndNicknameIsContaining(String status, String word);


}