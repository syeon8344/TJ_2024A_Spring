package example.day09.lombok;

public class Step1 {
    public static void main(String[] args) {

        // Member 객체 생성
        Member m1 = new Member();
        m1.setId("id1");
        m1.setName("유재석");

        System.out.println(m1.getId()); // @getter
        System.out.println(m1.getName()); // @getter

        System.out.println(m1); // Member(id=id1, name=유재석) @toString

        // Member 객체 생성
       //Member m2 = new Member("id2","강호동"); // 후에 필드가 바뀌면 오류


        // 생성자 규칙 : 메소드와 동일하게 매개변수의 순서, 타입, 갯수 일치해야 한다
        // 빌더 패턴 : 객체 생성 과정에서 표현 방법을 분리하여 다른 표현으로 객체 생성하는 방법
            // - 필요한 데이터만 설정할 수 있다, 유연성 확보, 가독성 향상, 변경 가능성 적음
            // 클래스명.builder().필드명(값).필드명(값). ... .build();
        Member m3 = Member.builder() // 필드가 바뀌어도 문제없음
                .name("신동엽")
                .id("abc")
                .build();
        System.out.println(m3);
    }
}
