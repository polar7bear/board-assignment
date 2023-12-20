package boardassignment.goorm.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "goorm_reply")
@Getter @Setter
public class Reply {

    @Id
    @GeneratedValue
    private Long replyId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_id")
    private Board board;

    private String reply;

    private LocalDate deletedAt;

    public void setBoard(Board board) {
        this.board = board;
    }

    public boolean isDeleted() {
        return deletedAt != null;
    }
}
