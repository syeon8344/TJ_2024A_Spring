package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import web.model.dto.BoardDto;
import web.service.BoardService;

import java.util.ArrayList;

@RequestMapping("/board")
@RestController
public class BoardController {
    @Autowired
    BoardService boardService;

    // 1. 글 전체 출력
    @GetMapping("/all")
    public ArrayList<BoardDto> bAllPrint(){
        return boardService.bAllPrint();
    }   // bAllPrint() end

    // 2. 글 쓰기 카테고리 불러오기
    @GetMapping("/getcategory")
    public ArrayList<BoardDto> getBoardCategory(){
        return boardService.getBoardCategory();
    }

//    // 3. 글 쓰기 (@RequestBody JSON String 방식)
//    @PostMapping("/write")
//    public boolean bWrite(@RequestBody BoardDto boardDto){
//        return boardService.bWrite(boardDto);
//    }

    // 3-1. 글 쓰기 + 파일첨부 (formData 방식) ( @RequestBody + formData : 예외발생 )
    @PostMapping("/write")
    public boolean bWrite(BoardDto boardDto){
        return boardService.bWrite(boardDto);
    }

    // 4. 상세페이지
    @GetMapping("/read")
    public BoardDto bRead(int bno){
        return boardService.bRead(bno);
    }

    // 5. 글 수정
    @PutMapping("/edit")
    public boolean bEdit(int bno, @RequestBody BoardDto dto){
        return boardService.bEdit(bno, dto);
    }
    // 6. 글 삭제
    @DeleteMapping("/delete")
    public boolean bDelete(int bno){
        return boardService.bDelete(bno);
    }


}   // class end
