package boardassignment.goorm.service;

import boardassignment.goorm.entity.Board;
import boardassignment.goorm.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;

    @Transactional
    public Long create(Board board) {
        boardRepository.save(board);
        return board.getId();
    }

    @Transactional
    public void delete(Long id) {
        boardRepository.remove(id);
    }

    public List<Board> findAll(int page, int size) {
        return boardRepository.findAll(page, size);
    }

    public Board findOne(Long id) {
        return boardRepository.findOne(id);
    }

}
