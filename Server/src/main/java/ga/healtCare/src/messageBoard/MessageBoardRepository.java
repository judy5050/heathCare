package ga.healtCare.src.messageBoard;


import ga.healtCare.src.group.models.GroupInfo;
import ga.healtCare.src.messageBoard.model.GetMessageBoardListRes;
import ga.healtCare.src.messageBoard.model.GetMessageBoardRes;
import ga.healtCare.src.messageBoard.model.MessageBoardInfo;
import ga.healtCare.src.user.models.GetUserRes;
import ga.healtCare.src.user.models.UserInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.net.ContentHandler;
import java.util.List;

public interface MessageBoardRepository extends JpaRepository<MessageBoardInfo,Long> {

    @Query("select new ga.healtCare.src.user.models.GetUserRes(m.id,m.userName,m.userNickName,m.birth) from UserInfo  m where m.groupInfo = :groupInfo")
    List<GetUserRes> findAllByGroupIdx(@Param("groupInfo") GroupInfo groupInfo);

    @Query("select m  from MessageBoardInfo  m order by m.createdAt desc ")
    Page<MessageBoardInfo> findMessageList(PageRequest pageRequest);

}
