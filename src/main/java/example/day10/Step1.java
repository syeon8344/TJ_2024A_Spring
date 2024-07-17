package example.day10;

public class Step1 {
    public static void main(String[] args) {

        /*
        상속 : extends
        - 상위 클래스로부터 멤버변수 & 메서드 물려받기
        - 자식 타입/클래스는 부모 타입/클래스로 형변환 가능
        - 부모 타입/클래스는 자식 타입/클래스로 조건부로 형변환 가능
            - 1. 자료의 태생이 자식타입이어야 한다. e.g. Object obj = new String;
            - 2. (자식타입)부모타입                    String objString = (String)obj;
        */

        // 1. 제네릭 안 쓰는 경우
        Box1 box1 = new Box1();
        box1.content = "안녕하세요";
        String strContent = box1.content;

        Box2 box2 = new Box2();
        box2.content = 100;
        int intContent = box2.content;

        // 2. Object
        Box3 box3 = new Box3(); // Object 자료형
        box3.content = "안녕하세요";
        String objContent = (String) box3.content; // 부모->자식; 강제 형변환 필요

        box3.content = 100;
        int objContent1 = (int) box3.content;

        // Object 사용시 단점 : 형변환을 자주 해줘야 해서 번거롭다
        // Generic 제네릭 : Java 5에서 처음 등장
        // 생성자의 <> 내부 생략 : Java 7에서 처음 등장
        // T의 자료형이 정해지는 순간 : 인스턴스화
        //   -> static 변수 자료형 또는 메서드는 T 사용불가 (static 생성시점이 더 빠름)
        // Java 10부터 지역변수에 한해 var varName = new 생성자() 사용가능
        //   -> 생성자에서 자료형 유추

        // 3. 제네릭 사용
        Box4<String> box4 = new Box4<>();
        box4.content = "안녕하세요";
        String content4 = box4.content;

        Box4<Integer> box41 = new Box4<>();
        box41.content = 100;
        int content41 = box41.content;

        GenericArrayList<Integer> list = new GenericArrayList<>();
        for (int i = 0; i < 10; i++){
            list.add(i+1);
        }
        list.remove(5);
        System.out.println(list);

        GenericArrayList<String> strList = new GenericArrayList<>();
        strList.add("AAA");
        strList.add("BBB");
        strList.remove(1);
        System.out.println(strList);
    }
}
