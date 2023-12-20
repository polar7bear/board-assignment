package boardassignment.goorm.controller;

import boardassignment.goorm.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;

@org.springframework.web.bind.annotation.RestController
@RequiredArgsConstructor
public class RestController {

    private final BoardService boardService;

    @DeleteMapping("/board/{id}/delete")
    public ResponseEntity<Void> deleteBoard(@PathVariable Long id) {
        boardService.remove(id);
        return ResponseEntity.ok().build();
    }
}
