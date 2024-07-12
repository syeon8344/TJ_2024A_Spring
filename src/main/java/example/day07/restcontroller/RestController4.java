package example.day07.restcontroller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/example")
public class RestController4 {

    // [1] HTTP GET
    @GetMapping("/test1")
    public String test1(HttpServletRequest request){
        System.out.println("RestController4.test1");
        String key1 = request.getParameter("key1");
        System.out.println("key1 = " + key1);
        String key2 = request.getParameter("key2");
        System.out.println("key2 = " + key2);
        return "test1Hello";
    }
    // [2] HTTP GET
    @GetMapping("/test2") // 전제조건 : 쿼리스트링의 key 이름과 메소드의 매개변수명이 동일해야 한다
    public String test2(String key1, int key2){
        System.out.println("RestController4.test2");
        System.out.println("key1 = " + key1 + ", key2 = " + key2);
        return "test2Hello";
    }
    // [3] HTTP GET
    @GetMapping("/test3")
    public String test3(@RequestParam("key1") String name, @RequestParam("key2") int age){
        // QueryString의 KEY 이름과 메소드 매개변수명이 동일하지 않아 오류가 있을 때 -> @RequestParam("키") 타입명 매개변수명
        System.out.println("RestController4.test3");
        System.out.println("name = " + name + ", age = " + age);
        return "test3Hello";
    }
    // [4] HTTP GET
    @GetMapping("/test4")
    public String test4(RestDto restDto){
        System.out.println("RestController4.test4");
        System.out.println("restDto = " + restDto);
        return restDto.toString();
    }
}
