package ga.healtCare.src.messageBoard.model;


import ga.healtCare.config.BaseEntity;
import ga.healtCare.src.user.models.UserInfo;
import lombok.*;

import javax.persistence.*;


@NoArgsConstructor(access = AccessLevel.PUBLIC) // Unit Test 를 위해 PUBLIC
@EqualsAndHashCode(callSuper = false)
@Getter
@Setter
@Entity // 필수, Class 를 Database Table화 해주는 것이다
@Table(name = "MessageBoardInfo") // Table 이름을 명시해주지 않으면 class 이름을 Table 이름으로 대체한다.
public class MessageBoardInfo extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "messageBoardIdx")
    private Long id;


    @ManyToOne
    @JoinColumn(name = "userIdx")
    private UserInfo userInfo;

    @Column(name = "message")
    private String message;

    @Column(name = "title")
    private String title;

    public MessageBoardInfo(String message, UserInfo userInfo) {
        this.message=message;
        this.userInfo=userInfo;
    }


}
