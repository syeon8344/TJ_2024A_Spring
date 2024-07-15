package example.day08.todo;


import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController// 해당 클래스는 스프링MVC패턴에서 Controller 역할, 스프링 컨테이너(JVM저장소) 빈(객체) 등록
public class TodoController {
//    // [1] 싱글톤 패턴
//    private static TodoController todoController = new TodoController();
//    public static TodoController getInstance(){
//        return todoController;
//    }
    // 1. 할 일 등록
    @PostMapping("/day08/todo")
    public boolean todoCreate(String tcontent){
        boolean result = TodoDao.getInstance().todoCreate(tcontent);
        return result;
    }
    // 2. 할일 전체 출력
    @GetMapping("/day08/todo")
    public ArrayList<TodoDto> todoReadAll(){
        return TodoDao.getInstance().todoReadAll();
    }
    // 3. 할일 상태 수정
    @PutMapping("/day08/todo")
    public boolean todoUpdate(int tno) {
        return TodoDao.getInstance().todoUpdate(tno);
    }
    // 4. 할일 삭제
    @DeleteMapping("/day08/todo")
    public boolean todoDelete(int tno) {
        System.out.println(tno);
        return TodoDao.getInstance().todoDelete(tno);
    }


}
