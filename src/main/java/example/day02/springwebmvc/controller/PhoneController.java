package example.day02.springwebmvc.controller;


import example.day02.springwebmvc.model.dao.PhoneDao;
import example.day02.springwebmvc.model.dto.PhoneDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

// 웹/인터넷 이용한 통신환경 구축 (JS-JAVA간 통신하기 위해서)

@RestController // (서블릿) 해당 클래스에 MVC 컨트롤러 역할 주입
public class PhoneController {
    // [1] 싱글톤 패턴 --> Spring에서 자동 객체 관리 기능이 있어 주석처리.
//    private static PhoneController phoneCtrl = new PhoneController();
//    private PhoneController(){};
//    public static PhoneController getInstance(){
//        return phoneCtrl;
//    }

    // 1.
    @PostMapping("/phone/create") // url : http:localhost:8080/phone/create
    public boolean postPhone(@RequestBody PhoneDto phoneDto) {
        System.out.println("==============postPhone 컨트롤 송신 성공------------------------------");
        return PhoneDao.getInstance().postPhone(phoneDto);
    }
    // 2.
    @GetMapping("/phone/read") // url : http:localhost:8080/phone/read
    public ArrayList<PhoneDto> getPhone() {
        System.out.println("==========getPhone()==================");
        return PhoneDao.getInstance().getPhone();
    }
}
