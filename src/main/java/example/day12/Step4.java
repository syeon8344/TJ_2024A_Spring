package example.day12;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Step4 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Set<User> set = new HashSet<>();
        while (true) {
            System.out.print(">>1.등록 2.전체출력 3.수정 4.삭제 5.종료");
            try {
                int ch = scanner.nextInt();
                if (ch == 1) {
                    System.out.print(">>이름을 입력 : "); String name = scanner.next();
                    System.out.print(">>나이를 입력 : "); int age = scanner.nextInt();
                    if (!set.add(new User(name, age))){
                        System.out.print(">>이름이 중복되어 등록되지 않았습니다.");
                    }
                } else if (ch == 2) {
                    set.forEach(System.out::println);
                } else if (ch == 3) {
                    System.out.println("name");
                    set.forEach(s -> {
                        System.out.println(s.name);
                    });
                    System.out.print(">>나이를 수정할 이름 입력 : "); String name = scanner.next();
                    for (User u : set){
                        if (u.name.equals(name)){
                            System.out.print(">>새로운 나이 입력 : "); int age = scanner.nextInt();
                            u.age = age;
                        } else {
                            System.out.println(">>이름이 존재하지 않습니다");
                        }
                    }
                } else if (ch == 4) {
                    System.out.println("name");
                    set.forEach(s -> {
                        System.out.println(s.name);
                    });
                    System.out.print(">>삭제할 이름 입력 : "); String name = scanner.next();
                    for (User u : set){
                        if (u.name.equals(name)){
                            set.remove(u);
                        } else {
                            System.out.println(">>이름이 존재하지 않습니다");
                        }
                    }
                } else if (ch == 5) {
                    break;
                } else {
                    throw new RuntimeException(">>잘못된 입력");
                }
            } catch (Exception e) {
                System.out.println(e);
                scanner.nextLine();
            }

        }

    }
}
