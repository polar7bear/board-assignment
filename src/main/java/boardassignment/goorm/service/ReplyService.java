package boardassignment.goorm.service;

import boardassignment.goorm.entity.Board;
import boardassignment.goorm.entity.Reply;
import boardassignment.goorm.repository.ReplyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
@Transactional
public class ReplyService {

    private final ReplyRepository replyRepository;

    public Long save(Reply reply) {
        replyRepository.save(reply);
        return reply.getReplyId();
    }


    public void remove(Long replyId) {
        Reply reply = replyRepository.findOne(replyId);

        Board board = reply.getBoard();
        if(board != null) {
            board.deleteReply(reply);
        }

        reply.setDeletedAt(LocalDate.now());
    }
}
