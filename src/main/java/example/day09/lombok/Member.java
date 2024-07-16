package example.day09.lombok;

import lombok.*;

@Builder // 해당 클래스의 빌더 패턴 주입/생성
@AllArgsConstructor // 모든 매개변수 생성자
@NoArgsConstructor // 매개변수 없는 생성자 ( = default)
//@Getter // 해당 클래스의 필드 getter 메소드 자동 주입/생성
//@Setter // 해당 클래스의 필드 setter 메소드 자동 생성
//@ToString // toString 자동 재정의
@Data // @Getter + @Setter + @ToString
public class Member {
    // 필드
    private String id;
    private String name;
    private String address;
        // - 필드 변화에 따라 유동적으로 getter, setter, 생성자, toString() 사용 가능

//    public Member(String id, String name){};
//
//    public Member(String id, String address){};
}
