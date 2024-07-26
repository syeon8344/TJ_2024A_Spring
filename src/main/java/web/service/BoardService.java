package web.service;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.multipart.MultipartFile;
import web.model.dao.BoardDao;
import web.model.dto.BoardDto;
import web.model.dto.MemberDto;

import java.io.File;
import java.util.ArrayList;

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
    public ArrayList<BoardDto> bAllPrint(){
        return boardDao.bAllPrint();
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
