package example.day12;

import java.util.*;
import java.util.stream.Collectors;

public class Step2 {
    public static void main(String[] args) {
        
        // 1. Map 컬렉션 객체
        Map <String, Integer> map = new HashMap<>();
        // 2.
        map.put("유재석", 85);
        map.put("강호동", 80);
        map.put("홍길동", 90);
        map.put("신동엽", 90); // value 값은 중복 가능
        map.put("유재석", 75); // key 값이 중복이면 기존 key value 덮어씌운다
        System.out.println("map = " + map);
        // 3. 
        int size = map.size();
        System.out.println("size = " + size);
        // 4.
        int point = map.get("강호동");
        System.out.println("point = " + point);
        // 5.
        map.remove("강호동");
        System.out.println("map = " + map);
        
        // 6.
        Set<String> keySet = map.keySet();
        System.out.println("keySet = " + keySet);
        
        Collection<Integer> values = map.values();
        System.out.println("values = " + values);
        
        Set < Map.Entry<String, Integer> > entrySet = map.entrySet();
        System.out.println("entrySet = " + entrySet);

        // map 객체 내 엔트리 순회
        // (1)
        Iterator <String> ir = map.keySet().iterator();
        while (ir.hasNext()){
            String key = ir.next();
            System.out.println("key = " + key);
            System.out.println("value = " + map.get(key));
        }
        // (2) 향상된 for문
        for (String key : map.keySet()){
            System.out.println("key = " + key);
            System.out.println("value = " + map.get(key));
        }
        for (Integer value : map.values()){
            System.out.println("value = " + value);
        }
        // (3) .forEach
        map.keySet().forEach(key -> {
                System.out.println(key);
                System.out.println(map.get(key));
        });
        // (4) .map
        Set<String> newKeySet = map.keySet().stream().map(key ->{
            System.out.println("key = " + key);
            System.out.println("value = " + map.get(key));
            return key;
        }).collect(Collectors.toSet());
        System.out.println("newKeySet = " + newKeySet);
    }
}
