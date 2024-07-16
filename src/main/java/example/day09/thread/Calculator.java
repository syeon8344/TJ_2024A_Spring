package example.day09.thread;

public class Calculator {
    //field
    int memory;

    // synchronized : 동기 처리화
        // 현재 과정이 끝나야 넘어간다
    public synchronized void setMemory(int memory){
        //매개변수 값을 필드에 저장
        this.memory = memory;
        // 2초간 일시정지
        try {
            Thread.sleep(2000);

        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println(Thread.currentThread().getName());
        System.out.println("memory : " + this.memory);


    }
}
