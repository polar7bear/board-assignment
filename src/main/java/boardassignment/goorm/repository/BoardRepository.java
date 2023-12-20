package boardassignment.goorm.repository;

import boardassignment.goorm.entity.Board;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class BoardRepository {

    private final EntityManager em;

    //게시글 저장
    public void save(Board board) {
        em.persist(board);
    }

    //soft delete 하기 위해 삭제 한 날짜를 넣어준다. (영속성 컨텍스트에서 실제로 remove 하지는 않음)
    public void remove(Long id) {
        Board board = em.find(Board.class, id);
        board.setDeletedAt(LocalDate.now());
    }

    //게시글 수정은 서비스단에서 변경 감지를 통해서 작성


    //게시글 목록 조회
    public List<Board> findAll(int page, int size) {
        int firstResult = (page - 1) * size;

        return em.createQuery("SELECT b FROM Board b WHERE b.deletedAt IS NULL", Board.class)
                .setFirstResult(firstResult)
                .setMaxResults(size)
                .getResultList();
    }

    //페이징을 위한 게시글 전체 개수 조회
    public long countBoards() {
        return em.createQuery("select count(b) from Board b", Long.class)
                .getSingleResult();
    }

    //게시글 단건 조회
    public Board findOne(Long id) {
        return em.find(Board.class, id);
    }

    //게시글 삭제
    public void delete(Long id) {

    }


}
