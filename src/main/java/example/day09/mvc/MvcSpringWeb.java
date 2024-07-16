package example.day09.mvc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication // 1. 내장 톰캣 실행 2. MVC 어노테이션들을 스캔해서 Bean(객체) 등록
public class MvcSpringWeb {
    public static void main(String[] args) {
        SpringApplication.run(MvcSpringWeb.class);
    }
}
// view : resources 폴더 안에 있는 HTML, CSS, JS 검색

//@Controller
@RestController // Bean Stereotypes
class MvcSpringWebController{
    @Autowired MvcSpringWebService service; // 객체가 들어있음, @Autowired 없으면 새로운 빈 객체
}

@Service
class MvcSpringWebService{
    @Autowired MvcSpringWebDao dao;

}

@Component
class MvcSpringWebDao{

}
