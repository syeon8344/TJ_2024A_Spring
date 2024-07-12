package example.day07.restcontroller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/example")
public class RestController5 {
    // ============ HTTP BODY 매개변수들 매핑하는 방법 ============= ㅣ/
    
    //[1] HTTP POST : QueryString 가능
    @PostMapping ("/test5")
    public String test5 (String key1, int key2){ // 쿼리스트링의 key1 value, key2 value 각각 변수명으로 등록
        System.out.println("RestController5.test5");
        System.out.println("key1 = " + key1 + ", key2 = " + key2);
        return "test5 Hello";
    }
    //[1] 
    @PostMapping ("/test6")
    //public String test6 (String key1, int key2){
    public String test6(@RequestBody RestDto restDto){
        System.out.println("RestController5.test6");
        //System.out.println("key1 = " + key1 + ", key2 = " + key2);
        System.out.println("restDto = " + restDto);
        return "test5 Hello";
    }
}
