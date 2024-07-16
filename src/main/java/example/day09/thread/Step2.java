package example.day09.thread;

public class Step2 {
    public static void main(String[] args) {

        // 1. 계산기 객체
        Calculator myCalc = new Calculator();

        // 2. 사용자 객체
        User1 user1 = new User1();
            user1.setName("user1's thread");

        user1.calculator = myCalc;
        user1.value = 100;
        user1.start();

        // 2. 사용자 객체 2
        User1 user2 = new User1();
            user2.setName("user2's thread");
        user2.calculator = myCalc; // user1, user2 객체내 동일한 필드값 대입
        user2.value = 200;
        user2.start();

        //synchronized 아닐 때 (비동기화)
        //user2's thread
        //user1's thread
        //memory : 200
        //memory : 200
    }
}
