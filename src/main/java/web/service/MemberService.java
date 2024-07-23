package web.service;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import web.model.dao.MemberDao;
import web.model.dto.MemberDto;

@Service
public class MemberService {
    @Autowired
    MemberDao memberDao;
    // 1. 회원가입
    public boolean doSignup(MemberDto dto) {
        if (dto.getId().isBlank() || dto.getPw().isBlank() || dto.getName().isBlank() || dto.getPhone().isBlank() || dto.getEmail().isBlank()){
            return false;
        } else {
            return memberDao.doSignup(dto);
        }
    }

    @Autowired
    HttpServletRequest request;

    // 2. 로그인
    public boolean login(MemberDto dto) {
        if (dto.getId().isBlank() || dto.getPw().isBlank()){
            return false;
        } else {
            int result = memberDao.login(dto);
            if(result > 0) {
                // - 빌더 패턴 : 생성자가 아닌 메서드를 이용한 객체 생성 방식
                MemberDto loginDto = MemberDto.builder()
                        .no(result)
                        .id(dto.getId())
                        .build();
                // ====  HTTP 세션 처리 ==== /
                // 1. 현재 요청을 보내온 클라이언트의 세션객체 호출
                HttpSession session = request.getSession();
                // 2. 세션객체에 속성 추가
                session.setAttribute("loginDto", loginDto);
                return true;
            }
            return false;
        }
    }

    // 3. 아이디 찾기
    public String findId(MemberDto dto) {
        return memberDao.findId(dto);
    }
    // 4. 비밀번호 찾기
    public String findPw(MemberDto dto) {
        return memberDao.findPw(dto);
    }
    // 5. 로그인체크
    public MemberDto mLoginCheck() {
        // 1. 요청을 보낸 클라이언트의 세션 객체 호출
        HttpSession session = request.getSession();
        // 2. 세션 객체내 속성 값 호출, 타입 변환 필요(모두 Object로 저장되어 있다)
        MemberDto loginDto = (MemberDto) session.getAttribute("loginDto");
        return loginDto;
    }
    // 6. 로그아웃
    public boolean mLogOut(){
        // 1. 요청을 보낸 클라이언트의 세션 객체 호출
        request.getSession().invalidate();
        return true;
    }
    // 7. 마이페이지
    public MemberDto mInfo() {
        HttpSession session = request.getSession();
        MemberDto dto = (MemberDto) session.getAttribute("loginDto");
        if (dto == null){
            return null;
        } else {
            return memberDao.mInfo(dto.getNo());
        }
    }

    public boolean idCheck(String id) {
        return memberDao.idCheck(id);
    }

    public boolean updateInfo(MemberDto dto) {
        HttpSession session = request.getSession();
        MemberDto loginDto = (MemberDto) session.getAttribute("loginDto");
        if (loginDto == null){return false;}
        return memberDao.updateInfo(dto, loginDto.getNo());
    }

    public boolean delAccount(MemberDto dto) {
        HttpSession session = request.getSession();
        MemberDto loginDto = (MemberDto) session.getAttribute("loginDto");
        if (loginDto == null){return false;}
        if (memberDao.delAccount(dto, loginDto.getNo())){
            session.invalidate();
            return true;
        }
        return false;
    }
}
