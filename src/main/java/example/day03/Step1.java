package example.day03;

class Book{ // 자동으로 extends Object
    // 멤버변수
    int bookNumber;
    String bookTitle;

    // 생성자
    public Book(int bookNumber, String bookTitle) {
        this.bookNumber = bookNumber;
        this.bookTitle = bookTitle;
    } // /생성자

    // 메서드
    @Override
    public String toString(){
        return "Object info : " + bookTitle + ", " + bookNumber;
    }

}// /클래스


public class Step1 {
    public static void main(String[] args) {
        // 1. 객체 생성
        Book book1 = new Book( 200, "개미");    // 스택메모리 : book1 = 힙메모리 : "302번지" 객체 생성
        // 2. Book 클래스의 메소드가 아닌 Object 클래스의 메소드 호출
        System.out.println(book1);              // 참조변수를 출력하면 자동으로 toString() 함수가 호출된다
                                                // example.day03.Book@5ca881b5
        System.out.println(book1.toString());   // example.day03.Book@5ca881b5

        // 3. 객체2 생성
        Book book2 = new Book( 300, "호랑이");   // 스택메모리 : book2 = 힙메모리 : "402번지" 객체 생성
        // 4. 객체3 생성
        Book book3 = new Book( 200, "개미");     // 스택메모리 : book3 = 힙메모리 : "502번지" 객체 생성
        // 5. 객체 생성 대신 주소값 대입
        Book book4 = book1;                     // 스택메모리 : book4 = book1 (302번지 참조)

        System.out.println(book1 == book2);      // false   302 == 402번지
        System.out.println(book1 == book3);      // false   302 == 502번지
        System.out.println(book1 == book4);      // true    302 == 302번지

        System.out.println(book1.equals(book2)); // false   302번지.equals(402번지)
        System.out.println(book1.equals(book3)); // false   302번지.equals(502번지)
        System.out.println(book1.equals(book4)); // true    302번지.equals(302번지)
    }
}
