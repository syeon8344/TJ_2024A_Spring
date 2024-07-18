package web.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import web.model.dto.MemberDto;
import web.service.MemberService;

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
}
