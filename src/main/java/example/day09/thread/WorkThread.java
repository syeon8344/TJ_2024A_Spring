package example.day09.thread;

public class WorkThread extends Thread{
    // 필드
    public boolean work = true;


    @Override
    public void run() {
        for(;;){
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } // /catch

            if (work){
                System.out.println("workThread : " + getName());
            } else {
                Thread.yield(); // 다른 스레드에게 양보
            }
        }// /for
    }// /run()
}// /class
