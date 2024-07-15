package example.day08.thread;

public class Timer implements Runnable {
    public int second; // 타이머의 초를 저장
    public boolean state = true;
    @Override
    public void run() {
        while (state){
            this.second++;
            System.out.println(">>Timer : " + this.second);
            try {
                Thread.sleep(1000);
            } catch (Exception e ) {
                System.out.println(e);
            }
        }
    }
}
