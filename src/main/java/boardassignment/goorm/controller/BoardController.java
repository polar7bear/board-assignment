package boardassignment.goorm.controller;

import boardassignment.goorm.dto.BoardForm;
import boardassignment.goorm.entity.Board;
import boardassignment.goorm.repository.BoardRepository;
import boardassignment.goorm.service.BoardService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(value = "/board")
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;

    private final BoardRepository boardRepository;

    @GetMapping("/add")
    public String boardForm(Model model) {
        model.addAttribute("boardForm", new BoardForm());
        return "board/boardForm";
    }

    @PostMapping("/add")
    public String create(@Valid BoardForm boardForm, BindingResult result) {
        if(result.hasErrors()) {
            return "board/boardForm";
        }
        Board board = new Board();
        board.setTitle(boardForm.getTitle());
        board.setContent(boardForm.getContent());

        boardService.create(board);
        return "redirect:/board/detail";
    }


    @GetMapping("/list")
    public String boardList(@RequestParam(defaultValue = "1") int page, Model model) {
        int size = 10; //페이지당 게시글의 수
        List<Board> boardList = boardService.findAll(page, size);

        long total = boardRepository.countBoards();
        int totalPage = (int) Math.ceil((double) total / size);

        model.addAttribute("boardList", boardList);
        model.addAttribute("totalPage", totalPage);
        model.addAttribute("currentPage", page);

        return "board/boardList";
    }


    @GetMapping("/detail")
    public String boardDetail() {
        return "";
    }




}
