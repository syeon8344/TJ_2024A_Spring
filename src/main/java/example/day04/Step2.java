package example.day04;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class Step2 {
    public static void main(String[] args) {

        // 문자열에서 자주 사용되는 함수
        // [1] 문자열.charAt(인덱스) : 해당 인덱스번호의 문자 1개 반환
        String ssn = "123123-1231234";
        char gender = ssn.charAt(7);
        switch (gender){
            case '1':
            case '3':
                System.out.println("남자");
                break;
            case '2':
            case '4':
                System.out.println("여자");
                break;
        }
        // 활용 : Scanner에서 문자 입력 메소드로 쓰기
        //Scanner scanner = new Scanner(System.in);
        //char _char = scanner.next().charAt(0); // 문자 1개 입력받기
        //System.out.println("_char = " + _char);

        // [2] 문자열.length() : 문자열의 길이 반환
        System.out.println(ssn.length());
        // 활용
        for( int i = 0; i < ssn.length(); i++){
            System.out.println(ssn.charAt(i)); // 문자열의 i번째 인덱스의 문자 1개씩 출력
        }

        // [3] 문자열.replace(기존문자열,새 문자열); : 기존 문자열이 존재하면 새로운 문자열로 치환/교체된 새 문자열 반환
        String oldStr = "자바의 문자열은 불변입니다. 자바 문자열은 String 입니다.";
        String newStr = oldStr.replace(" ", "_"); // 문자열은 불변이므로 새로운 변수명으로 받아야 한다
        System.out.println(newStr);
        // 활용 : 서로 다른 문법들간의 문법 치환, e.g. HTML 줄바꿈 </br> <--> JAVA \n
        String htmlStr = "안녕하세요</br>유재석";
        System.out.println("htmlStr = " + htmlStr);
        String javaStr = htmlStr.replace("</br>", "\n");
        System.out.println("javaStr = " + javaStr);

        // [4] 문자열.subString(시작인덱스, [끝인덱스]) : 문자열을 인덱스 기준으로 잘라낸 새 문자열 반환
            // ssn = "123123-1231234"
        String birthDate = ssn.substring(0,6); // 0 ~ 5번 인덱스 까지 (끝 인덱스 바로 전까지)
        System.out.println("birthDate = " + birthDate);
        String endNum = ssn.substring(7);
        System.out.println("endNum = " + endNum);
        
        // [5] 문자열.split("구분 문자") : 문자열 분해해서 새로운 문자열 배열 반환
        String[] ssns = ssn.split("-");
        System.out.println("ssns = " + Arrays.toString(ssns)); //Arrays.toString : 배열 내 요소를 출력
        System.out.println("ssns[0] = " + ssns[0]);
        // 활용 : CSV 문자열 다루기
        String csv = "유재석,80,90,100\n강호동,60,70,50\n신동엽,50,40,60";
            // 행 구분문자 \n 기준으로 분해
        String[] rows = csv.split("\n");
        System.out.println("Arrays.toString(rows) = " + Arrays.toString(rows));
            // 열 구분문자 , 기준으로 분해
        for (int i = 0; i < rows.length; i++){
            String[] cols = rows[i].split(",");
            System.out.println("Arrays.toString(cols) = " + Arrays.toString(cols));
            for (int j = 0; j < cols.length; j++){
                System.out.println(cols[j]);
            }
        }
        
        //[6] 문자열.indexOf() : 문자열 내 찾을 문자가 있으면 해당 인덱스 반환, 없으면 -1
        String subject = "자바 프로그래밍 언어";
        
        int foundIndex = subject.indexOf("프로");
        System.out.println("foundIndex = " + foundIndex); // "자바" : 0, "프로" : 3, "파이썬" : -1
        
        //[7] 문자열.contains("찾을 문자열") : 문자열 내 찾을 문자열 있으면 true 아니면 false
        boolean findCheck = subject.contains("자바");
        System.out.println("findCheck = " + findCheck); // "자바" : true, "프로" : true, "파이썬" : false
        
        //[8] 문자열.getBytes() : 문자열 내 문자 하나씩 바이트로 변환된 바이트 배열로 변환
        byte[] bytes = subject.getBytes();
        System.out.println("Arrays.toString()bytes = " + Arrays.toString(bytes));
        
        // 문자와 바이트 관계
            //1 . 영문, 특수문자 : 문자 1개당 1바이트
            // 2. 한글 : 문자 1개당 2바이트, 규칙 1바디트 -> 총합 3바이드'\
        byte _byte1 = 'a';
        System.out.println("_byte1 = " + _byte1); // 97 : 유니코드 (아스키코드 값)
        _byte1++;
        System.out.println("_byte1 = " + _byte1); // 98
        System.out.println("(char)_byte1 = " + (char)_byte1); // 'b'

        System.out.println("a".getBytes().length); // 1 바이트, 영문
        
        System.out.println("가".getBytes().length); // 3 바이트, 한글
            // -char : 문자 1개 표현, 655536자 표현, 부호 : singed, 부호없음 : unsigned vs short (+- 3만정도)
        char _char1 = '가';
        System.out.println("_char1 = " + _char1);
        System.out.println("(int)_char1 = " + (int)_char1);
        
        char _char2 = 'a';
        System.out.println("(int)_char2 = " + (int)_char2);
        // [활용] 난수 비밀번호 생성
        System.out.println(new Random().nextInt()); //int타입의 허용범위
        System.out.println(new Random().nextInt(26)); // 0~25
        System.out.println(new Random().nextInt(26)+97);
        System.out.println((char) (new Random().nextInt()));
        String newPwd = "";
        for (int i = 0; i < 10; i++){
            newPwd += (char) (new Random().nextInt(26)+97);
        }
        System.out.println(newPwd);
    }
}
