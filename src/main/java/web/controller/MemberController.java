package web.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import web.model.dto.MemberDto;
import web.service.MemberService;

@RestController
public class MemberController {

    @Autowired
    MemberService memberService;

    // 1. 회원가입
    @PostMapping("/member/signup")
    public boolean doSignup(@RequestBody MemberDto dto){
        return memberService.doSignup(dto);
    }

    // 2. 로그인
    @PostMapping("/member/login")
    public boolean login(@RequestBody MemberDto dto){
        return memberService.login(dto);
    }
}
