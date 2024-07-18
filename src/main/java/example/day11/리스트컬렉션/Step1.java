package example.day11.리스트컬렉션;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Vector;
import java.util.stream.Collectors;

public class Step1 {
    public static void main(String[] args){

        // 1. 리스트 컬렉션
        List<String> list = new ArrayList<>();
        list = new Vector<>();
        list = new LinkedList<>();
        // ArrayList<String> list2 = new ArrayList<>();

        // 2. 리스트 요소 추가
        list.add("유재석");
        list.add("강호동");
        list.add("신동엽");
        list.add("서장훈");

        // 3. 리스트 특정 위치의 요소 수정
        list.set(0, "박명수");
        list.set(3, "김희철");

        System.out.println("list = " + list);
        
        // 4. 요소 갯수
        int size = list.size();
        System.out.println("size = " + size);
        
        // 5. 특정 위치의 요소 값 호출
        String str1 = list.get(1);
        System.out.println("str1 = " + str1);
        
        // 6. 리스트 내 특정 요소의 값 검색
        boolean bool1 = list.contains("강호동"); // true or false
        System.out.println("bool1 = " + bool1);

        // 7. 리스트 내 특정 요소 검색, 인덱스 값으로 반환
        int index1 = list.indexOf("강호동"); // 인덱스 or -1
        System.out.println("index1 = " + index1);

        // 8. 리스트 내 특정 요소 삭제
        list.remove(1);
        System.out.println("list = " + list);

        // 9. 리스트 내 요소 순회
        // 9-1. 일반 for문
        for ( int i = 0; i < list.size(); i++ ){
            System.out.println(list.get(i));
        }
        // 9-2. 향상된 for문
        for (String s : list){
            System.out.println(s);
        }
        // 9-3. forEach 함수, 요소 하나씩 반환해서 반복, return 없음
        list.forEach( s -> System.out.println(s));
        list.forEach(System.out::println);
        
        // 9-4 .stream().map().collect(), 요소 하나씩 반환해서 반복후 결과(return)를 반환
            // 반복하면서 return값들을 하나의 배열/컬렉션으로 반환받을 수 있다
            // 주로 카피/복사 등에 쓰인다
        List<String> newList = list.stream()
                .map(s -> {return s;})
                .collect(Collectors.toList());
        System.out.println("newList = " + newList);
        newList.remove(0);
        System.out.println("list = " + list);
        System.out.println("newList = " + newList);
        // === Vector === //
        List<String> vector = new Vector<>();
        vector.add("유재석");
        System.out.println("vector = " + vector);
        // ============
        List<String> list1 = new ArrayList<>();
        List<String> list2 = new LinkedList<>();
        long startTime;
        long endTime;

        startTime = System.nanoTime();
        for (int i = 0; i < 10000; i++){
            list1.add(0, String.valueOf(i));
        }
        endTime = System.nanoTime();
        long result = endTime - startTime;
        System.out.println("10000개 저장하는 ArrayList 걸린시간 : " + result);

        startTime = System.nanoTime();
        for(int i = 0; i < 10000; i ++){
            list2.add(0, String.valueOf(i));
        }
        endTime = System.nanoTime();
        result = endTime - startTime;
        System.out.println("10000개 저장하는 LinkedList 걸린시간 : " + result);
    }
}
