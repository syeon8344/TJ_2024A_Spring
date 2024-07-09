package example.day04.spring;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MyController {
//    // JSP 설정
//    @RequestMapping("/")
//    public @ResponseBody String root() throws Exception{
//        return "JSP in Gradle";
//    }
//
//    @RequestMapping("/test1") // localhost:8080/test1
//    public String test1(){
//        return "test1"; // 호출 : /WEB-INF/views/test1.jsp
//    }
//
//    @RequestMapping("/test2") // localhost:8080/test2
//    public String test2(){
//        return "sub/test2"; // 호출 : /WEB-INF/views/test1.jsp
//    }
//    // JSP 설정 끝

    // Thymeleaf
    @RequestMapping("/test3")
    public String test3() {
        return "test3.html";
    }
}
