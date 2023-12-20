package boardassignment.goorm.repository;

import boardassignment.goorm.entity.Board;
import boardassignment.goorm.entity.Reply;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class ReplyRepository {

    private final EntityManager em;

    public void save(Reply reply) {
        em.persist(reply);
    }

    public Reply remove(Long replyId) {

        return em.createQuery("select r from Reply r where r.id = :replyId and r.deletedAt is null", Reply.class)
                .setParameter("replyId", replyId)
                .getSingleResult();
    }

    public Reply findOne(Long replyId) {
        return em.find(Reply.class, replyId);
    }

}
