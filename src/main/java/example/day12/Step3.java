package example.day12;

import java.util.Scanner;
import java.util.LinkedList;
import java.util.List;

public class Step3 {
    public static void main(String[] args) {
        List<User> list = new LinkedList<>();
        Scanner scanner = new Scanner(System.in);
        while(true) {
            System.out.print(">>1.등록 2.전체출력 3.수정 4.삭제 5.종료");
            try {
                int ch = scanner.nextInt();
                if (ch == 1){
                    System.out.print(">>이름을 입력 : "); String name = scanner.next();
                    System.out.print(">>나이를 입력 : "); int age = scanner.nextInt();
                    list.add(new User(name, age));
                } else if (ch == 2){
                    list.forEach(System.out::println);
                } else if (ch == 3){
                    System.out.println("name   index");
                    list.forEach(u -> System.out.print(u.name + "   " + list.indexOf(u)));
                    System.out.println();
                    System.out.print(">>수정할 인덱스 번호 입력 : "); int index = scanner.nextInt();
                    if (index < list.size() && index >= 0){
                        System.out.print(">>새로운 나이 입력 : ");
                        list.get(index).age = scanner.nextInt();
                    } else {
                        System.out.println(">>인덱스 번호가 잘못되었습니다");
                    }
                } else if (ch == 4){
                    System.out.println("name   index");
                    list.forEach(u -> System.out.print(u.name + "   " + list.indexOf(u)));
                    System.out.println();
                    System.out.print(">>삭제할 인덱스 번호 입력 : "); int index = scanner.nextInt();
                    if (index < list.size() && index >= 0){
                        list.remove(index);
                    } else {
                        System.out.println(">>인덱스 번호가 잘못되었습니다");
                    }
                } else if (ch == 5){
                    break;
                } else {throw new RuntimeException(">>잘못된 입력");}
            } catch (Exception e ){
                System.out.println(e);
                scanner.nextLine();
            }

        }
    }
}
