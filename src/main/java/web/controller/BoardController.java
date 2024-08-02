package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import web.model.dto.BoardDto;
import web.model.dto.BoardPageDto;
import web.service.BoardService;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RequestMapping("/board")
@RestController
public class BoardController {
    @Autowired
    BoardService boardService;

    // 1. 글 전체 출력
    @GetMapping("/all")
    public BoardPageDto bAllPrint(BoardPageDto dto){
        System.out.println(dto);
        // 1. page : 현재 페이지 번호
        // 2. bcno : 현재 선택된 카테고리 번호
        // 3. searchKey : 검색 조회시 사용되는 필드명
        // 4. searchKeyword : 검색 조회시 사용되는 필드값
        return boardService.bAllPrint(dto);
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

    // 4-1. 상세페이지 진입시 조회수 증가
    @PutMapping("/view")
    public void bView(int bno){
        boardService.bView(bno);
    }
    // 5-1. 글 수정 권한 확인
    @GetMapping("/edit/check")
    public boolean bEditCheck(int bno) {return boardService.bEditCheck(bno);}
    // 5. 글 수정
    @PutMapping("/edit")
    public boolean bEdit(@RequestBody BoardDto dto){
        return boardService.bEdit(dto);
    }

    // 6. 글 삭제
    @DeleteMapping("/delete")
    public boolean bDelete(int bno){
        return boardService.bDelete(bno);
    }

    // 7. 글 수정/삭제 권한
    @GetMapping("/authorize")
    public boolean authorize(int bno){
        return boardService.authorize(bno);
    }
    // 게시물 댓글
    @GetMapping("/reply")
    public List<Map<String,String>> bReplyFindBno(int bno){
        System.out.println("BoardController.bReplyFindBno" + bno);
        return boardService.bReplyFindBno(bno);
    }
    // 게시물 쓰기
    @PostMapping ("/reply/write")// DB 추가이므로 POST
    public boolean bReplyWrite(@RequestBody Map<String, String> map){
        // @RequestBody : HTTP 요청 수신시 HTML Body에서 오는 데이터 타입에 맞도록 한다
        // String 키 String 밸류의 집합 = MAP, 미리 키값을 알고 있지 않아도 수신 가능
        System.out.println(map);
        return boardService.bReplyWrite(map); // 데이터 가공 및 DAO 처리는 Service 클래스에서 (Spring MVC)
    }

}   // class end
