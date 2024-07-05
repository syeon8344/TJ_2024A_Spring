package web;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AppStart {
    public static void main(String[] args) {
        // run() 메서드 호출, run(현재클래스명.class)
        SpringApplication.run(AppStart.class);
        /*
            * 다른 클래스 메소드 호출법

            1. 인스턴스(static 없는) 메소드 호출방법
                클래스명 변수명 = new 생성자();
                변수명.메소드명;
                ( new 생성자명().메소드명() <- 1회용 )

            2. 싱글톤 (미리 인스턴스를 만들고 호출 후 메소드 호출)
                클래스명.getInstance().메소드명();
                -> 전역변수를 쓸 필요가 있는가? (e.g. loginMno)
            3. 해당 메소드가 static:
                클래스명.메소드명();
         */
    }
}
