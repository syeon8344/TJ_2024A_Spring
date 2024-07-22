package example.day13;

public class Member implements Comparable<Member>{
    String name;
    int age;

    public Member(){};
    public Member(String name, int age) {
        this.name = name;
        this.age = age;
    }

    // * Comparable 인터페이스의 추상메소드를 정의

    @Override
    public int compareTo(Member o) {
//        // 1. name 기준으로 정렬, String 클래스 내 compareTo 활용
//        return this.name.compareTo(o.name);
        // 2. age 기준으로 정렬, int 는 기본타입이므로 직접 정렬기준 비교
            // 오름차순 : -1 : 매개변수보다 작음, 0 : 같음, 1 : 매개변수보다 큼
            // 내림차순 : 1 : 매개변수보다 작음, 0 : 같음, 2 : 매개변수보다 큼
        if (this.age< o.age) {return -1;}
        else if (this.age == o.age){return 0;}
        else return 1;
    }

    @Override
    public String toString() {
        return "Member{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
