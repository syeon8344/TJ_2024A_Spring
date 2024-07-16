package example.day09.todo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
// [2] Web (SpringBoot)
@SpringBootApplication
// 1. 내장 톰캣(웹서버) 실행 2. RESTful 지원 3. 스프링MVC
public class AppStart {
    public static void main(String[] args) {
        SpringApplication.run(AppStart.class);
    }
    // [1] 콘솔로 테스트
//        // 1. 할일 등록 (->) X (<-) 결과
//        TodoController.getInstance().todoCreate("파이썬공부");
//        // 2. 할일 전체 출력 (->) X (<-) 할일목록
//        ArrayList<TodoDto> result = TodoController.getInstance().todoReadAll();
//        System.out.println(result);
//        // 3. 할일 상태 수정 (->) tno (<-) 결과
//        System.out.println(TodoController.getInstance().todoUpdate(2));
//        // 4. 할일 삭제 (->) tno (<-) 결과
//        System.out.println(TodoController.getInstance().todoDelete(4));


}
