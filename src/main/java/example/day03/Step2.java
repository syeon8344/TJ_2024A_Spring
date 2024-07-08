package example.day03;

public class Step2 {
    public static void main(String[] args) {
        String str1 = new String("abc");    // str1 -> 302번지 (-> 502번지)
        String str2 = new String("abc");    // str2 -> 402번지 (-> 502번지)
        String str3 = "abc";                // str3 -> 502번지

        int i1 = 100;
        int i2 = 100;
        Integer i3 = 100;
        // 래퍼(감싼) 클래스 : int형 기본타입은 메소드 사용불가, int타입도 메서드 쓰기 위해 만든 참조타입
            // JAVA : Integer.parseInt() : 문자열 타입을 정수변환해서 반환
            // vs JS: : parseInt();


        System.out.println(str1.hashCode()); // 96354 : 리터럴 "abc"의 해시코드값 -> "문자열의 불변성"
        System.out.println(str2.hashCode()); // 96354
        System.out.println(str3.hashCode()); // 96354

        System.out.println(str1==str2); // false, 302 = 402?
        System.out.println(str1.equals(str2)); //true, 502 = 502?
        System.out.println(str1 == str3); // false, 302 = 502?
        System.out.println(str1.equals(str3)); //true, 502 = 502?

        System.out.println(i1 == i2); // true
        //System.out.println(i1.equals(i2)); // int는 참조타입이 아니라 Object클래스부터 상속받지 못함 : 오류 발생
        System.out.println(i3.equals(i1)); // Integer는 참조타입 ->Object 상속받음
        //Integer.parseInt()

        //
        //str1.clone(); // 기본적으로 clone() 메소드 사용 불가
        Object object = new Object();
        //object.clone(); // 기본적으로 clone() 메소드 사용 불가
         /*
           [1]
             스택 메모리           힙 메모리
               str1                new String() : 302번지 주소값을 가지는 객체 생성
               str2                new String() : 402번지 주소값을 가지는 객체 생성
             1. == : false, 302번지와 402번지를 가리키는 두 스택변수는 다르다
             2. equals() : true, 302번지와 402번지 객체를 비교했더니 같다
           [2]
              메소드 메모리          스택 메모리           힙 메모리
                                   str1                new String() : 302번지 주소값을 가지는 객체 생성
               리터럴(상수)                                멤버변수 : "abc"의 주소 502번지 주소값
                 1, 3.15           str2                new String() : 402번지 주소값을 가지는 객체 생성
                 true, 'a'...                             멤버변수 : "abc"의 주소 502번지 주소값
                                   str3                   "abc" : 502번지
                                 1. == : false, 302번지와 402번지를 가리키는 두 스택변수는 다르다
                                 2. equals() : true, 302번지와 402번지 객체를 비교했더니 같다
         */

    }
}
