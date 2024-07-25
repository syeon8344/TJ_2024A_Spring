package web.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import web.model.dto.MemberDto;
import web.service.MemberService;

import java.util.Map;

@RestController
@RequestMapping("/member")
public class MemberController {

    @Autowired
    MemberService memberService;

    // 1. 회원가입
    @PostMapping("/signup")
    public boolean doSignup(@RequestBody MemberDto dto) {
        return memberService.doSignup(dto);
    }

    // 2. 로그인
    @PostMapping("/login")
    public boolean login(@RequestBody MemberDto dto) {
        return memberService.login(dto);
    }

    // 3. 아이디 찾기
    @PostMapping("/findId")
    public String findId(@RequestBody MemberDto dto) {
        return memberService.findId(dto);
    }

    // 4. 비밀번호 찾기
    @PostMapping("/findPw")
    public String findPw(@RequestBody MemberDto dto) {
        return memberService.findPw(dto);
    }

    // 5. 로그인 체크
    @GetMapping("/login/check")
    public MemberDto mLoginCheck() {
        return memberService.mLoginCheck();
    }

    // 6. 로그아웃
    @GetMapping("/logout")
    public boolean mLogout() {
        return memberService.mLogOut();
    }

    // 7. 마이페이지
    @GetMapping("/my/info")
    public MemberDto mInfo() {
        return memberService.mInfo();
    }
    // 8. 회원가입 아이디 중복검사
    // 대소문자 구분 : SQL = binary(id)
        // binary(id) = asdf != ASDF
        // id = asdf == ASDF
    @GetMapping("/idCheck")
    public boolean idCheck(String id){
        return memberService.idCheck(id);
    }

    // 9. 회원 정보 수정
    @PutMapping("/updateInfo")
    public boolean updateInfo(@RequestBody MemberDto dto){
        return memberService.updateInfo(dto);
    }

    // 10. 회원 탈퇴
    @DeleteMapping("/delAccount")
    public boolean delAccount(@RequestBody MemberDto dto){
        return memberService.delAccount(dto);
    }

    // 11. 회원정보수정/비밀번호확인
    @PostMapping("/update/pwCheck")
    public boolean updatePwCheck(@RequestBody Map<String, String> map){
        return memberService.updatePwCheck(map);
    }

}
