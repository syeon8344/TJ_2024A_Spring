package example.day06;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class Step1 {
    public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException {

        // Wrapper 래퍼 클래스 : 기본타입 -> 참조타입, 기본타입을 참조타입으로 만들기
            // - 기본 타입을 참조타입으로 사용해야 하는 경우에 쓴다
                // - 기본타입은 클래스가 아니므로 매개변수나 메소드를 포함하지 않는다
                // - 기본타입 데이터로 메소드/기능을 사용해야 하는 경우 (주로 타입변환)
        int val1 = 100; // 기본타입 100, 도트 연산자 사용불가
        Integer val2 = 100; //Integer val2 = new Integer(100) (deprecated); 참조타입 100, 래퍼클래스
        val2.intValue(); // 도트연산자 사용가능 (참조타입, 클래스)
        
        // 자주 사용되는 메소드
            // 1. .intValue() : 기본타입의 값을 반환
        int myValue = val2.intValue();
        System.out.println("myValue = " + myValue);
            // 2. Integer.valueOf(정수 또는 문자열) : Integer객체 반환
        Integer val3 = Integer.valueOf("100");
        Integer val4 = Integer.valueOf(100);
            // 3. ** Integer.parseInt(문자열) : int타입 값 반환
        // == 문자열 타입을 기본 타입으로 변환 : 주로 자바 밖의 데이터를 가져올 떄 (ResultSet getInt() 처럼) == //
        int val5 = Integer.parseInt("100");
        double val6 = Double.parseDouble("3.1415");
        float val7 = Float.parseFloat("3.14");
        byte var8 = Byte.parseByte("120");
        short var9 = Short.parseShort("30000");
        long var10 = Long.parseLong("10000000000");
            // 4. 오토박싱, 언박싱
        System.out.println("val1(int) + val2(Integer) = " + (val1 + val2));
        int var11 = val1 + val2;
        System.out.println("var11 = " + var11); // 기본타입 + 참조타입 = 200, 언박싱 적용
        Integer var12 = var11; // 오토박싱, = Integer var12 = Integer.valueOf(var11)
        
        // ===============================================================================//
        // Class 클래스
        // [1]
        String s = new String();
        Class c = s.getClass();
        System.out.println("c.toString() = " + c.toString()); // class java.lang.String
        // [2]
        Class c2 = String.class;
        System.out.println("c2.toString() = " + c2.toString()); // class java.lang.String
        // [3]
        try {
            Class c3 = Class.forName("java.lang.String");
            System.out.println("c3.toString() = " + c3.toString()); // class java.lang.String
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        // 이전 예시 : JDBC
        try {
            Class c4 = Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("c4.toString() = " + c4.toString());
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        Person person = new Person();

        Class class1 = person.getClass();
        System.out.println("class1.toString() = " + class1.toString()); // class example.day06.Person

        Class class2 = Person.class;
        System.out.println("class2.toString() = " + class2.toString());

        try {
            Class class3 = Class.forName("example.day06.Person");
            System.out.println("class3.toString() = " + class3.toString());
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        
        // --------------------------------------------------------------------
        // 리플렉션 : 자료형을 모르는 클래스의 정보를 가져와서 인스턴스 생성이나 메서드 호출하기
            // 1. String 클래스의 모든 생성자 (선언부) 호출
        Constructor[] cons = c.getConstructors();
        for(Constructor con : cons){
            System.out.println("con = " + con); // public java.lang.String(java.lang.StringBuffer) ...
        }
            // 2.
        Field[] fields = c.getFields();
        for(Field field : fields){
            System.out.println("field = " + field); // public static final java.util.Comparator java.lang.String.CASE_INSENSITIVE_ORDER
            
        }
            // 3.
        Method[] methods = c.getMethods();
        for(Method method : methods){
            System.out.println("method = " + method); // public int java.lang.String.length() ...
        }

        //-------------------------------------------
        Person person2 = new Person();
        System.out.println("person2 = " + person2);
        Class pClass = Class.forName("example.day06.Person"); // try-catch 또는 클래스 throws Exception 추가
        // Person p3 = (Person)pClass.newInstance(); // java 9부터 deprecated
        // System.out.println("p3 = " + p3);
    }   
}
