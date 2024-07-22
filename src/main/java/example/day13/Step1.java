package example.day13;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class Step1 {
    public static void main(String[] args) {
        
        // 1. 스택
        Stack<Integer> coinBox = new Stack<>();
        
        // 2. push()
        coinBox.push(100);
        coinBox.push(500);
        coinBox.push(50);
        System.out.println("coinBox = " + coinBox);

        // 4. peek()
        int topData = coinBox.peek();
        System.out.println("topData = " + topData);

        // 3. pop()
        coinBox.pop();
        System.out.println("coinBox = " + coinBox);
        coinBox.pop();
        System.out.println("coinBox = " + coinBox);
        coinBox.pop();
        System.out.println("coinBox = " + coinBox);

        // 4. Queue
        Queue<Integer> pointBox = new LinkedList<>();

        // 5. 큐의 Enque
        pointBox.offer(100);
        pointBox.offer(500);
        pointBox.offer(10);
        pointBox.offer(50);
        System.out.println("pointBox = " + pointBox);

        // 6. Queue Peek()
        int frontData = pointBox.peek();
        System.out.println("frontData = " + frontData);

        // 7. 큐 deque
        pointBox.poll();
        System.out.println("pointBox = " + pointBox);
        pointBox.poll();
        System.out.println("pointBox = " + pointBox);
        pointBox.poll();
        System.out.println("pointBox = " + pointBox);
        pointBox.poll();
        System.out.println("pointBox = " + pointBox);
    }
}
