package example.day08.thread;

import java.awt.*;

public class 작업스레드B implements Runnable{
    // implements : 인터페이스 구현
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
