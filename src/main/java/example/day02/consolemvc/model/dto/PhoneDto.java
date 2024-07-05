package example.day02.consolemvc.model.dto;

public class PhoneDto {

    // [1] 멤버변수
    private int id;
    private String name;
    private String phone;
        // + DB TABLE에 없는 매개변수 필요에 따라 추가

    // [2] 생성자
    public PhoneDto (){};
    public PhoneDto (String name, String phone){
        this.name = name;
        this.phone = phone;
    }

    // [3] 메소드
    @Override
    public String toString() {
        return "PhoneDto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
