package boardassignment.goorm.controller;

import boardassignment.goorm.dto.BoardForm;
import boardassignment.goorm.entity.Board;
import boardassignment.goorm.entity.Reply;
import boardassignment.goorm.repository.BoardRepository;
import boardassignment.goorm.service.BoardService;
import boardassignment.goorm.service.ReplyService;
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

    private final ReplyService replyService;

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
        return "redirect:/board/list";
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

    @GetMapping("/{id}/edit")
    public String boardUpdateForm(@PathVariable("id") Long id, Model model) {
        Board editBoard = new Board();
        Board board = boardService.findOne(id);

        editBoard.setId(board.getId());
        editBoard.setTitle(board.getTitle());
        editBoard.setContent(board.getContent());

        model.addAttribute("board", editBoard);
        return "board/updateBoardForm";
    }

    @PostMapping("/{id}/edit")
    public String boardUpdate(@PathVariable("id") Long id, @ModelAttribute("board") BoardForm boardForm, BindingResult result, Model model) {
        if(result.hasErrors()) {
            return "redirect:/{id}/edit";
        }
        Board board = boardService.update(id, boardForm.getTitle(), boardForm.getContent());
        model.addAttribute("board", board);

        return "board/boardDetail";
    }

    @GetMapping("/{id}/detail")
    public String boardDetail(@PathVariable("id") Long id, Model model) {
        Board board = boardService.findOne(id);
        model.addAttribute("board", board);
        return "board/boardDetail";
    }

    @PostMapping("/{id}/addReply")
    public String addReply(@PathVariable Long id, @RequestParam String replyContent) {
        Reply reply = new Reply();
        reply.setReply(replyContent);
        reply.setBoard(boardService.findOne(id));
        replyService.save(reply);

        return "redirect:/board/{id}/detail";
    }

}
