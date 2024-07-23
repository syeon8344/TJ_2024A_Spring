package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import web.service.AuthService;

@RestController
@RequestMapping ("/auth")
public class AuthController {

    @Autowired
    AuthService authService;
    // 1. 인증 번호 요청
    @GetMapping ("/code")
    public boolean getAuthCode(String email){
        return authService.getAuthCode(email);
    }
    @PostMapping ("/check")
    public boolean checkAuthCode(String authCodeInput){
        return authService.checkAuthCode(authCodeInput);
    }
}
