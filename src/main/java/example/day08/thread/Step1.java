package example.day08.thread;

import java.awt.*;

public class Step1 {
    public static void main(String[] Args){
        // ======   싱글 스레드    ====== //
        // 1. 비프음 재생
        Toolkit toolkit = Toolkit.getDefaultToolkit();
            // Toolkit = java.awt JAVA UI(소리, 화면 등) 라이브러리
        for (int i = 1; i <= 5; i++) {    // 5회 반복
            toolkit.beep(); // 비프음
            // 비프음 소리 1회 출력 속도보다 for문 루프가 빨라 소리가 1회밖에 나지 않는다
            // for문을 처리하는 흐름[스레드]을 일시정지
            try {
                Thread.sleep(1000); // 매개변수 밀리초만큼 스레드 일시정지 -> 비프음 5번
            } catch (Exception e) {
                System.out.println("Exception " + e);
            }
        }
        // 2. "beep" 콘솔 출력
        for (int i = 0; i < 5; i++){ // 위에 비프음 5번 출력 후 println 5회
            System.out.println("beep");
        }
        //  ================================== //
        //  ========= 멀티스레드 1 ============ //
        // 1. 작업스레드 A의 객체 생성
        작업스레드A threadA = new 작업스레드A();
        // 2. 작업스레드A의 스레드 실행 ( run() )
        threadA.start();
        // [2] "beep" 5회 콘솔 출력
        for (int i = 0; i < 5; i++){
            System.out.println("beep");
            try{Thread.sleep(1000);}catch(Exception e){
                System.out.println(e);
            }
        }
        //  ========= 멀티스레드 2 ============ //
        // 1. 작업스레드B 구현(객)체 생성
        Runnable runnable1 = new 작업스레드B();
        // 2. Thread 객체
        Thread threadB = new Thread(runnable1);
        // 3. Thread 실행
        threadB.start();
        // [2]. "beep" 콘솔 5회 출력
        for (int i = 0; i < 5; i++){
            System.out.println("beep");
            try{Thread.sleep(1000);}catch(Exception e){
                System.out.println(e);
            }
        }
        //  ========= 멀티스레드 3 - 1 ============ //
        // 1. 익명 객체/구현체 : 이름 없는 객체
            // new 생성자(){익명구현체 정의}
        Thread threadC = new Thread(){
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
        };
        // 2. 멀티스레드 실행
        threadC.start();
        // [2]. "beep" 콘솔 5회 출력
        for (int i = 0; i < 5; i++){
            System.out.println("beep");
            try{Thread.sleep(1000);}catch(Exception e){
                System.out.println(e);
            }
        }
        //  ========= 멀티스레드 3 - 2 ============ //
        // 1. 구현체
        // new Thread(new Runnable(){코드});
        Thread threadD = new Thread(new Runnable() {
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
        });
        // 2. 스레드 실행
        threadD.start();
        // [2]. "beep" 콘솔 5회 출력
        for (int i = 0; i < 5; i++){
            System.out.println("beep");
            try{Thread.sleep(1000);}catch(Exception e){
                System.out.println(e);
            }
        }


    }

}
