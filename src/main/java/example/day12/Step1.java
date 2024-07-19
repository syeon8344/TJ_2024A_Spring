package example.day12;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.stream.Collectors;

public class Step1 {
    public static void main(String[] args) {
        
        // 1. HashSet 선언
        Set<String> set = new HashSet<>();
        // 2.
        set.add("임정순");
        set.add("박현정");
        set.add("홍연의");
        set.add("강감찬");
        set.add("강감찬");
        // 3.
        System.out.println("set = " + set);
        // 4.
        System.out.println("set.size() = " + set.size());
        // 5.
        set.remove("강감찬");
        System.out.println("set = " + set);
        // 6.
        boolean bool = set.contains("홍연의");
        System.out.println("bool = " + bool);
        // 7. set 컬렉션 순회 방법
            // set - 인덱스가 없어 get() 불가
        // (1)
        Iterator<String> it = set.iterator();
        while (it.hasNext()){
            System.out.println("it.next() = " + it.next());
        }
        // (2) 향상된 반복문
        for (String s : set){
            System.out.println("s = " + s);
        }
        // (3) forEach : 리턴값 X
        set.forEach(s -> System.out.println(s));
        set.forEach(System.out::println);
        // (4) .stream().map() : 리턴값 O, + .collect() => 새로운 Set
        Set<String> newSet = set.stream().map(s -> {return s;}).collect(Collectors.toSet());
        
        // 필드가 두개 이상인 객체 Set의 중복처리
        Set<Member> memberSet = new HashSet<>();
        //
        memberSet.add(new Member(1001, "이지원"));
        memberSet.add(new Member(1002, "손민국"));
        memberSet.add(new Member(1003, "박서훤"));
        //
        System.out.println("memberSet = " + memberSet);
        //
        memberSet.add(new Member(1003, "박서훤"));
        System.out.println("memberSet = " + memberSet);
        
    }
}
