package ga.healtCare.src.commentInfo;

import ga.healtCare.src.commentInfo.model.CommentInfo;
import ga.healtCare.src.commentInfo.model.GetCommentRes;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CommentInfoRepository extends JpaRepository<CommentInfo,Long> {


    @Query("select c from CommentInfo  c where c.messageBoardInfo.id =:messageBoardIdx")
    Page<CommentInfo> findAllByMessageBoardIdx(Pageable pageable, Long messageBoardIdx);
}
