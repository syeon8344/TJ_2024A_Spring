package example.day07.restcontroller;


import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.io.IOException;

// including RESTful : HTTP 기반 자원을 제공하는 인터페이스 구축 (CRUD)
    // SPRING WEB 아닐 때 : Servlet 클래스를 직접 구현, servlet 클래스를 controller
    // SPRING WEB 환경 : MVC 2 3 티어 제공하여 controller 자동으로 servlet 등록

// 해당 클래스가 SPRING MVC에서 controller 클래스임을 스프링 컨테이너(저장소) 빈(객체) 등록
    // - 제어 역전 (IOC) : 객체 관리를 개발자가 아니라 스프링이 한다. (이유: 여러 개발자가 공동사용할 객체는 한명이 관리하면 좋은데 SPRING에게 위임)
        // Inversion Of Control
@Controller
public class RestController1 {

    // @RequestMapping(value = "해당 메소드 매핑하는 MTTP 주소정의", method=RequestMethod.(HTTP메소드))
        // value : "(http://localhost:8080/[ip와 포트는 생략])/example/rest1"
            // 서버 내 동일한 URL을 정의할 수 없지만 HTTP 메소드가 다르면 같은 URL 정의 가능.
        // method : RequestMethod.HTTP메소드 (GET,POST,PUT,DELETE)

    // [1] HTTP GET
    @RequestMapping(value ="/example/rest1", method = RequestMethod.GET)
    public void getRest1(HttpServletRequest request, HttpServletResponse resp) throws IOException {
        System.out.println("RestController1.getRest1");
        String key = request.getParameter("key");
        System.out.println("key = " + key);
        resp.getWriter().println("[POST]serverHi2");
    }

    // [2] HTTP POST
    @RequestMapping(value = "/example/rest1", method = RequestMethod.POST)
    public void postRest1(HttpServletRequest request, HttpServletResponse resp) throws IOException {
        System.out.println("RestController1.postRest1");;
        String key = request.getParameter("key");
        System.out.println("key = " + key);
        resp.getWriter().println("[POST]serverHi2");
    }

    // [3] HTTP PUT
    @RequestMapping(value = "/example/rest1", method = RequestMethod.PUT)
    public void putRest1(HttpServletRequest request, HttpServletResponse resp) throws IOException {
        System.out.println("RestController1.putRest1");
        String key = request.getParameter("key");
        System.out.println("key = " + key);
        resp.getWriter().println("[POST]serverHi2");
    }

    // [4] HTTP DELETE
    @RequestMapping(value = "/example/rest1", method = RequestMethod.DELETE)
    public void deleteRest1(HttpServletRequest request, HttpServletResponse resp) throws IOException {
        System.out.println("RestController1.deleteRest1");
        String key = request.getParameter("key");
        System.out.println("key = " + key);
        resp.getWriter().println("[POST]serverHi2");
    }
}
