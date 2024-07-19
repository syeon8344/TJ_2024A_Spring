package example.day12;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Step5 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Map<String, Integer>map = new HashMap<>();
        while(true) {
            System.out.print(">>1.등록 2.전체출력 3.수정 4.삭제 5.종료");
            try {
                int ch = scanner.nextInt();
                if (ch == 1){
                    System.out.print(">>이름을 입력 : "); String name = scanner.next();
                    System.out.print(">>나이를 입력 : "); int age = scanner.nextInt();
                    if (map.containsKey(name)){
                        System.out.print(">>이름이 이미 존재하여 등록할 수 없습니다");
                    } else {
                        map.put(name, age);
                    }
                } else if (ch == 2){
                    map.keySet().forEach(key -> {
                        System.out.println("name : " + key + " age : " + map.get(key));
                    });
                } else if (ch == 3){
                    map.keySet().forEach(key -> {
                        System.out.println("name : " + key + " age : " + map.get(key));
                    });
                    System.out.print(">>수정할 이름을 입력해주세요 : "); String name = scanner.next();
                    if (map.containsKey(name)){
                        System.out.print(">>새 나이를 입력해주세요 : "); int age = scanner.nextInt();
                        map.put(name, age);
                    }
                } else if (ch == 4){
                    map.keySet().forEach(key -> {
                        System.out.println("name : " + key + " age : " + map.get(key));
                    });
                    System.out.print(">>삭제할 이름을 입력해주세요 : "); String name = scanner.next();
                    if (map.containsKey(name)){
                        map.remove(name);
                    } else {
                        System.out.println(">>존재하지 않는 이름입니다.");
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
