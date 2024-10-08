package example.day08.thread;

import java.awt.*;

public class 작업스레드A extends Thread {
    // extends : 상속
    // - 멀티스레드 실행코드 정의
        // - run() : 동시실행할 코드를 정의
    @Override
    public void run() {
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        for (int i = 1; i <= 5; i++) {
            toolkit.beep();
            try {
                Thread.sleep(1000);
            } catch (Exception e) {
                System.out.println("Exception " + e);
            }
        }
    }

}
