package web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

// == AJAX 통신용이 아닌 템플릿을 반환하는 컨트롤러 == //
// @RestController // @Controller + @ResponseBody
@Controller // JSON객체가 아닌 템플릿 파일을 반환하므로 @ResponseBody 없이 사용
public class ViewController {
    // [1] 레이아웃
    @GetMapping("/")
    public String index(){
        return "/index.html"; // templates 폴더 내 반환할 경로와 파일명
    }
    // [2] 회원 메뉴
    @GetMapping("/member/signup")
    public String mSignup(){
        return "/member/signup.html";
    }
    // [3] 로그인 메뉴
    @GetMapping("/member/login")
    public String mLogin(){
        return "/member/login.html";
    }
    // [4] 아이디 찾기
    @GetMapping("/member/findid")
    public String mFindID(){
        return "/member/findid.html";
    }
    // [5] 비밀번호 찾기
    @GetMapping("/member/findpw")
    public String mFindPw(){
        return "/member/findpw.html";
    }
    // [6] 마이페이지
    @GetMapping("/member/mypage")
    public String mInfo(){
        return "/member/myinfo.html";
    }

    // [7] 회원정보수정
    @GetMapping("/member/update")
    public String mUpdate(){
        return "/member/update.html";
    }
    // [8] 회원탈퇴
    @GetMapping("/member/leave")
    public String mDelete(){
        return "/member/leave.html";
    }
}
