package example.day09.thread;

public class Step1 {
    // 메인 thread 제공
    public static void main(String[] args) {

        // 1. 현재 코드를 읽는 스레드 이름 호출
        Thread thread = Thread.currentThread();
        System.out.println("현재 코드를 읽어들이는 스레드명 : " + thread.getName());

        // 2. 여러개의 스레드를 만들어 스레드 이름 확인
        for (int i = 0; i < 5; i++){ // for
            int num = i;
            Thread threadA = new Thread(new Runnable() { // new()
                @Override
                public void run() { // run
                    Thread thread = Thread.currentThread();
                    thread.setName("New Name " + num); // 스레드 이름 정의하기
                    System.out.println("현재 코드를 읽어들이는 스레드명 : " + thread.getName());
                } // run
            }); // new()
            threadA.start();
        } // for

        // 3. 현재 스레드를 주어진 시간동안 일시정지
        try {
            System.out.println(">> 3초 대기중");
            Thread.sleep(3000); // 매개변수 : 밀리초, 1/1000초
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println(">> 3초후 실행됨");

        // 4. 서로 다른 스레드의 종료를 기다리기
        SumThread sumThread = new SumThread();
        sumThread.start();
            // sumThread가 sum을 구하기 전에 결과를 출력함 : 0
        System.out.println(".join() 전 합계 결과 : " + sumThread.sum);
            // main 스레드가 sumThread가 종료될때까지 기다려야 한다
        try {
            sumThread.join(); // main과 sumThread 스레드를 join : 흐름을 합친다 -> sumThread가 끝날 때까지 기다린다
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println(".join() 후 합계 결과 : " + sumThread.sum);

        // 5. 다른 스레드의 순서 양보
            // 스레드 객체 생성
        WorkThread workThreadA = new WorkThread();
        workThreadA.setName("workingA"); // 이름 설정
        WorkThread workThreadB = new WorkThread();
        workThreadB.setName("workingB");
            // 스레드 실행
        workThreadA.start();
        workThreadB.start();

        try {
            Thread.sleep(5000); // main 5초 정지
            workThreadA.work = false; // workThread A 필드값 변경
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        try {
            Thread.sleep(5000); // main 5초 정지
            workThreadA.work = true;// workThread A 필드값 변경
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    } // /main
} // /class
