package web.service;

import jakarta.mail.internet.MimeMessage;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class AuthService {

    @Autowired
    HttpServletRequest request;
    @Autowired
    JavaMailSender mailSender;

    // 1. 인증번호 요청/생성
    public boolean getAuthCode(String email) {
        try {
            System.out.println("email" + email);
            // 앞자리 0 포함을 위해 String
            StringBuilder sb = new StringBuilder();
            // 난수 생성 (Random 클래스)
            Random rand = new Random();
            //
            for (int i = 0; i < 6; i++){
                sb.append(rand.nextInt(10));
            }
            // 3. (선택) 저장 : DB, JVM, 세션(웹서버 저장소-브라우저마다)
            String authCode = sb.toString();
            System.out.println("authCode : " + authCode);
            // 1. 세션 : 서버 세션의 인증 코드를 저장
            request.getSession().setAttribute("authCode", authCode);
            // 2. 서버 세션의 생명주기(세션 유지 시간 초단위)
            request.getSession().setMaxInactiveInterval(180);
            // 3. 이메일 전송
            // emailSend(email, "AAA 홈페이지의 인증코드입니다", "인증코드는 " + authCode + " 입니다.");
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    // 2. 입력받은 값과 인증 코드 비교하기
    public boolean checkAuthCode(String authCodeInput) {
        // 1. 인증번호 호출
        Object obj = request.getSession().getAttribute("authCode");
        // 2. 인증코드 체크 및 결과 반환
        if (obj != null) {
            String code = (String)obj;
            return authCodeInput.equals(code);
        }
        return false;
    }

    // 3. 이메일 전송 함수, 매개변수 : 받는사람의 이메일
    public void emailSend(String toEmail, String subject, String content){
        try {
            // 1. 메일 내용물을 포맷/형식 맞추기 위해 MIME
            MimeMessage mimeMessage = mailSender.createMimeMessage();
            // 2. 메일 내용 구성
                // new MimeMessageHelper(MIME 객체, 첨부파일여부, 인코딩 타입)
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true, "utf-8");
            // 3. 메일 보내는 사람
            mimeMessageHelper.setFrom("dbstjddusdl@naver.com");
            // 4. 메일 받는 사람
            mimeMessageHelper.setTo(toEmail);
            //  5. 메일 제목
            mimeMessageHelper.setSubject(subject);
            // 6. 메일 내용
            mimeMessageHelper.setText(content);
            // 7. ** 메일 전송
            mailSender.send(mimeMessage); // MIME 객체물 보내기
        } catch (Exception e) {
            System.out.println(e);
        }


    }
}
