package boardassignment.goorm.repository;

import boardassignment.goorm.entity.Reply;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
@RequiredArgsConstructor
public class ReplyRepository {

    private final EntityManager em;

    public void save(Reply reply) {
        em.persist(reply);
    }

    public void remove(Long id) {
        Reply reply = em.find(Reply.class, id);
        reply.setDeletedAt(LocalDate.now());
    }

}
