package boardassignment.goorm.service;

import boardassignment.goorm.entity.Reply;
import boardassignment.goorm.repository.ReplyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class ReplyService {

    private final ReplyRepository replyRepository;

    public Long save(Reply reply) {
        replyRepository.save(reply);
        return reply.getReplyId();
    }

    public void remove(Long id) {
        replyRepository.remove(id);
    }
}
