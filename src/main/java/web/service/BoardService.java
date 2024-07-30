package web.service;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import web.model.dao.BoardDao;
import web.model.dto.BoardDto;
import web.model.dto.BoardPageDto;
import web.model.dto.MemberDto;

import java.util.ArrayList;
import java.util.List;

@Service
public class BoardService {
    @Autowired
    BoardDao boardDao;
    @Autowired
    MemberService memberService;
    @Autowired
    FileService fileService;

    @Autowired // 현재 요청을 보낸 클라이언트의 HTTP 요청정보를 가지고 있는 객체를 주입
    HttpServletRequest request;

    // 1. 글 전체 출력
    public BoardPageDto bAllPrint(BoardPageDto dto){
        // 페이지당 표시할 게시물 수
        int pageSize = 5; // 하나의 페이지당 10개씩 표시
        // 페이지당 시작 레코드 번호
        int startRow = (dto.getPage()-1) * pageSize;
        // 전체 게시물 수
        int totalBoardSize = boardDao.getTotalBoardSize(dto.getBcno());
        // 전체 페이지 수 : 전체게시물수 / 페이지당게시물수
            // e.g. 총 게시물 수 23개, 페이지당 5개 게시물 출력 : 4+1 5페이지
        int totalPage = totalBoardSize % pageSize == 0 ? totalBoardSize / pageSize : totalBoardSize / pageSize + 1;
        // 게세물 정보 조회
        List<BoardDto> data = boardDao.bAllPrint(dto.getBcno(), startRow, pageSize);
        // 페이지별 시작 버튼 번호, 끝 버튼 번호
            // start 계산 :
            // e.g. 총 게시물 수가 13개이고 페이지당 게시물 3개일 때 : 4페이지 + 1 =5페이지
                                            //page        start       end       page -1   /최대버튼수    몫   *최대버튼수 , +1
            // 1페이지 : [1] [2] [3] [4] [5]     1           1           5           0       0 / 5        0      0           1
            // 2페이지 : [1] [2] [3] [4] [5]     2           1           5           1       1 / 5        0      0           1
            // 3페이지 : [1] [2] [3] [4] [5]     3           1           5           2       2 / 5        0      0           1
            // 4페이지 : [1] [2] [3] [4] [5]     4           1           5           3       3 / 5        0      0           1
            // 5페이지 : [1] [2] [3] [4] [5]     5           1           5           4       4 / 5        0      0           1
            // 6페이지 : [6] [7] [8] [9] [10]    6           6           10          5       5 / 5        1      5           6
        int btnSize = 5; // 페이지당 최대 버튼 수 5개
        int startBtn = (((dto.getPage()-1)/btnSize)*btnSize + 1); // 페이지별 시작 버튼 번호 변수
        int endBtn = startBtn + btnSize - 1; // 페이지의 끝버튼
        if(endBtn >= totalPage) {endBtn = totalPage;} // 끝 번호가 마지막이면
        // 반환 객체 구성
        BoardPageDto pageDto = BoardPageDto.builder()
                .page(dto.getPage()) // 현재 페이지 번호
                .totalBoardSize(totalBoardSize) // 전체 게시물 수
                .totalPage(totalPage) // 전체 페이지 수
                .data(data) // 조회된 게시물 목록
                .startBtn(startBtn) // 페이지 표시되는 시작 버튼
                .endBtn(endBtn) // 페이지에 표시되는 끝 버튼
                .build();
        return pageDto;
    }   // bAllPrint() end

    // 2. 글 쓰기 카테고리 불러오기
    public ArrayList<BoardDto> getBoardCategory() {
        return boardDao.getBoardCategory();
    }

    // 3. 글 쓰기
    public boolean bWrite(BoardDto boardDto){
        //로그인 체크
        MemberDto loginDto=memberService.mLoginCheck();
        int loginMno;
        if (loginDto == null) {
            return false;
        } else {
            loginMno=loginDto.getNo();
        }
        // 파일 업로드 처리
        if (!boardDto.getUploadFile().isEmpty()) {
            String uploadFileName = fileService.fileUpload(boardDto.getUploadFile());
            // 파일 업로드 오류 여부
            if (uploadFileName == null) {
                return false;
            } else {
                // 파일 이름 DTO 등록
                boardDto.setBfile(uploadFileName);
            }
        }
        return boardDao.bWrite(boardDto,loginMno);
//        System.out.println("multipartfile = " + mFile);
//        System.out.println(mFile.getContentType());
//        System.out.println(mFile.getName());
//        System.out.println(mFile.getSize());
//        System.out.println(mFile.isEmpty());
    }

    // 4. 상세페이지
    public BoardDto bRead(int bno){
        return boardDao.bRead(bno);
    }
    // 5. 글 수정
    public boolean bEdit(BoardDto dto){
        //로그인 체크
        MemberDto loginDto=memberService.mLoginCheck();
        int loginMno;
        if (loginDto == null) {
            return false;
        } else {
            loginMno=loginDto.getNo();
        }
        return boardDao.bEdit(loginMno, dto);
    }
    // 6. 글 삭제
    public boolean bDelete(int bno) {
        //로그인 체크
        MemberDto loginDto=memberService.mLoginCheck();
        int loginMno;
        if (loginDto == null) {
            return false;
        } else {
            loginMno=loginDto.getNo();
        }
        return boardDao.bDelete(loginMno, bno);
    }
    // 5-1. 글 수정 권한 확인
    public boolean bEditCheck(int bno) {
        //로그인 체크
        MemberDto loginDto=memberService.mLoginCheck();
        int loginMno;
        if (loginDto == null) {
            return false;
        } else {
            loginMno=loginDto.getNo();
        }
        return boardDao.bEditCheck(loginMno, bno);
    }

    // 4-1. 상세페이지 진입시 조회수 증가
    public void bView(int bno) {
        boardDao.bView(bno);
    }

    // 로그인 체크
/*    public MemberDto mLoginCheck() {
        // 1. 요청을 보낸 클라이언트의 세션 객체 호출
        HttpSession session = request.getSession();
        // 2. 세션 객체내 속성 값 호출, 타입 변환 필요(모두 Object로 저장되어 있다)
        MemberDto loginDto = (MemberDto) session.getAttribute("loginDto");
        return loginDto;
    }*/
}   // class end
