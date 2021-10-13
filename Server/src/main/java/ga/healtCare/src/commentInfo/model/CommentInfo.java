package ga.healtCare.src.commentInfo.model;


import ga.healtCare.config.BaseEntity;
import ga.healtCare.src.messageBoard.model.MessageBoardInfo;
import ga.healtCare.src.user.models.UserInfo;
import lombok.*;

import javax.persistence.*;

@NoArgsConstructor(access = AccessLevel.PUBLIC) // Unit Test 를 위해 PUBLIC
@EqualsAndHashCode(callSuper = false)
@Getter
@Setter
@Table(name = "commentInfo")
@Entity
public class CommentInfo extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "commentIdx")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userIdx")
    private UserInfo userInfo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "messageBoardIdx")
    private MessageBoardInfo messageBoardInfo;



}
