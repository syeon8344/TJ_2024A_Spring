package example.day04;

public class Step1 {
    public static void main(String[] args) {

        // [1] 문자열 선언하는 방법
        String str1 = new String("abc"); // 힙 영역, 객체 참조 -> 리터럴 "abc" 참조
        String str21 = new String("test");
        String str2 = "test"; // 상수풀 (리터럴) : 메소드 영역
        String str3 = "test"; // 상수풀 (리터럴) : 메소드 영역
        String str4 = new String("abc"); // 힙 영역, 또 다른 객체 참조 -> 리터럴 "abc" 참조

        System.out.println(str2 == str3); //true : 참조변수의 참조주소가 같다.
        System.out.println(str21 == str2);
        System.out.println(str2.equals(str3)); //true
        System.out.println(str1 == str4); //false : 참조주소가 서로 다르다
        System.out.println(str1.equals(str4)); //true
            // String : private final char value[] -> 한 번 생성된 문자열은 불변(immutable)
            // 문자열을 연결 = 새로운 문자열 생성 ("abc".concat("def") = 새로운 문자열 "abcdef")

        // [2] 문자열 연결
            // [2-1] 문자열1.concat(문자열2)
        String javaStr = new String("java");
        String androidStr = new String("android");
        System.out.println("javaStr : " + System.identityHashCode(javaStr));
        System.out.println("androidStr : " + System.identityHashCode(androidStr));
        javaStr = javaStr.concat(androidStr);
        System.out.println("javaStr : " + javaStr);
        System.out.println("javaStr : " + System.identityHashCode(javaStr));
            // [2-2] 문자열1 += 문자열2
        String html1 = "<div>";
        System.out.println(System.identityHashCode(html1));
        String html2 = "하하</div>";
        System.out.println(System.identityHashCode(html2));
        html1 += html2;
        System.out.println(html1);
        System.out.println(System.identityHashCode(html1));
            // [2-3] StringBuilder : 기존 메모리 문자열을 사용하는 문자열 연결 클래스, 메모리 효율성
        String javaStr2 = new String("java");
        String androidStr2 = new String("android");
        System.out.println(System.identityHashCode(javaStr2));

        StringBuilder builder = new StringBuilder(javaStr2);
        System.out.println(">>연결 전 버퍼 : " + System.identityHashCode(builder));
        builder.append(androidStr2);
        System.out.println(">>연결 후 버퍼 : " + System.identityHashCode(builder));
        javaStr2 = builder.toString();

        System.out.println(javaStr2);
        System.out.println(">>연결 후 javaStr2: " + System.identityHashCode(javaStr2));
    }
}
