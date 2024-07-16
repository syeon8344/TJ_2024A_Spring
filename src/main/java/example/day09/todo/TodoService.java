package example.day09.todo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;

@Service
public class TodoService {

    @Autowired TodoDao todoDao;

    // 1. 할 일 등록
    public boolean todoCreate(String tContent){ return todoDao.todoCreate(tContent); }

    // 2. 할일 전체 출력
    public ArrayList<TodoDto> todoReadAll(){
        return todoDao.todoReadAll();
    }

    // 3. 할일 상태 수정
    public boolean todoUpdate(int tno) {
        return todoDao.todoUpdate(tno);
    }

    // 4. 할일 삭제
    public boolean todoDelete(int tno) { return todoDao.todoDelete(tno); }
}
