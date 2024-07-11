package example.day06.servlet;


import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.boot.web.servlet.ServletComponentScan;


import java.io.IOException;

@WebServlet ("/day06/servlet") //http://서버ip:포트번호/day06/servlet/
public class ServletController extends HttpServlet {
    // extends = 상속 ( 특정 클래스를 상속받으면 해당 클로스는 상속받은 클래스의 모든 필드와  소ㅔ맏
        // - @Override : 오버라이딩 : 상속받은 클래스의 메서드를 재정의

    // 0. init()
    @Override
    public void init() throws ServletException{
        System.out.println("ServletController.init");
        System.out.println(">>해당 클래스의 서블릿 객체가 생성되었다.");
        super.init();
    }

    // 0. service()
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        System.out.println("ServletCOntroller.service");
        System.out.println(">>해당 클래스의 서블릿 객체 서비스가 실행되었다.");
        super.service(req, resp);
    }

    // 0. destroy()
    @Override
    public void destroy(){
        super.destroy();
    }
    // 1. doGet
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        System.out.println(">>ServletController.doGet");
        System.out.println(">>HTTP의 GET 메소드 방식으로 요청이 들어왔습니다.");
        //super.doGet(req,resp); // super.메소드() : 부모 클래스의 메소드 호출
        // - 요청데이터 : 매개변수처럼 HTTP 요청시 들어오는 데이터, HTTP 요청정보 관련 객체  : HttpServletRequest
        System.out.println("request data : " + req.getParameter("data"));
        // /servlet?data=serverHi -> ? 쿼리스트링 키 = 값 (?키=값  == ?data=serverHi)
        // - 응답데이터 : 리턴값처럼 HTTP 응답시 반환하는 데이터, HTTP 응답정보 관련 객체 : HttpServletResponse
        resp.getWriter().print("response Data : [GET] Hello");
    };

    // 2. doPost
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        System.out.println(">>ServletController.doPost");
        System.out.println(">>HTTP의 POST 메소드 방식으로 요청이 들어왔습니다.");
        //super.doPost(req,resp);
        // 1. request data
        System.out.println("request Data : " + req.getParameter("data"));
        // 2. response data
        resp.getWriter().print("response Data : [POST]clientHi");
    }

    // 3. doPut
    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        System.out.println(">>ServletController.doPut");
        System.out.println(">>HTTP의 PUT 메소드 방식으로 요청이 들어왔습니다.");
        //super.doPut(req,resp);
        // 1. request data
        System.out.println("request Data : " + req.getParameter("data"));
        // 2. response data
        resp.getWriter().print("response Data : [PUT]clientHi");
    }

    // 4. doDelete
    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println(">>ServletController.doDelete");
        System.out.println(">>HTTP의 DELETE 메소드 방식으로 요청이 들어왔습니다.");
        //super.doDelete(req,resp);}
        // 1. request data
        System.out.println("request Data : " + req.getParameter("data"));
        // 2. response data
        resp.getWriter().print("response Data : [DELETE]clientHi");
    }
}
