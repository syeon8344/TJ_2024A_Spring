package example.day10.printerexample;

public abstract class Material {
    // abstract : 추상
        // - 선언부만 존재하고 구현부는 존재하지 않는다
        // 상속 또는 구현시 해당 추상메소드를 재정의해야 한다
    // abstract class 클래스명{} : 추상클래스; 추상메소드를 가질 수 있는 클래스
    public abstract void doPrinting();
}
