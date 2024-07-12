package example.day07.todo;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.ArrayList;

@RestController
@RequestMapping(value = "todo") //localhost:8080/todo
public class TodoController {

    //GET 으로 띄울 todolist DB 가져오기
    @GetMapping("")
    public ArrayList<TodoDto> getTodoList(){
        return TodoDao.getInstance().getTodoList();
    }

    //POST 로 할 일 이름을 받아와서 DB에 저장하기
    @PostMapping("")
    public boolean todoAdd(@RequestBody TodoDto todoDto){
        return TodoDao.getInstance().todoAdd(todoDto.getListName());
    }


    //해당 리스트의 목록코드를 받아 상태 변경하기 (true/false)
    @PutMapping("")
    public boolean todoModify(@RequestBody TodoDto todoDto) {
        return TodoDao.getInstance().todoModify(todoDto.getListCode());
    }


    //해당 리스트의 목록코드를 받아 삭제하기
    @DeleteMapping("")
    public boolean todoDelete(@RequestBody TodoDto todoDto){
        return TodoDao.getInstance().todoDelete(todoDto.getListCode());
    }


}
