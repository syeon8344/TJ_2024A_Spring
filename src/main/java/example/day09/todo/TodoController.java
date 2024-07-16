package example.day09.todo;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;


@RestController// 해당 클래스는 스프링 MVC 패턴에서 Controller 역할, 스프링 컨테이너(JVM 저장소) 빈(객체) 등록
@RequestMapping(value = "/day09/todo")
public class TodoController {

    @Autowired
    TodoService todoService;

    // 1. 할 일 등록
    @PostMapping
    public boolean todoCreate(String tContent){ return todoService.todoCreate(tContent);}

    // 2. 할일 전체 출력
    @GetMapping
    public ArrayList<TodoDto> todoReadAll(){ return todoService.todoReadAll();}

    // 3. 할일 상태 수정
    @PutMapping
    public boolean todoUpdate(int tno){ return todoService.todoUpdate(tno);}

    // 4. 할일 삭제
    @DeleteMapping
    public boolean todoDelete(int tno){ return todoService.todoDelete(tno);}
}
