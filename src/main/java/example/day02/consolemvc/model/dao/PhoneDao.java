package example.day02.consolemvc.model.dao;

import example.day02.consolemvc.model.dto.PhoneDto;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class PhoneDao {

    static Connection conn;
    PreparedStatement ps;
    ResultSet rs;

    // [1] 싱글톤 패턴
    private static PhoneDao phoneDao = new PhoneDao();
    private PhoneDao(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/springexample", "root","1234"  );
        }catch (Exception e){
            System.out.println(">>연동 실패 : "+e);
        }
    };

    public static PhoneDao getInstance(){
        return phoneDao;
    }

    public boolean postPhone(PhoneDto phoneDto) {
        try {
            String sql = "insert into phonebook(name, phone) values (?,?);";
            ps = conn.prepareStatement(sql);
            ps.setString(1, phoneDto.getName()); ps.setString(2, phoneDto.getPhone());
            int result = ps.executeUpdate();
            if (result == 1) {
                return true;
            }
        }catch (Exception e) {
            System.out.println(">>postPhone : " + e);
        }
        return false;
    }

    public ArrayList<PhoneDto> getPhone() {
        ArrayList<PhoneDto> phoneBook = new ArrayList<>();
        try {
            String sql = "select * from phonebook;";
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                PhoneDto phoneDto = new PhoneDto();
                phoneDto.setId(rs.getInt(1)); phoneDto.setName(rs.getString(2)); phoneDto.setPhone(rs.getString(3));
                phoneBook.add(phoneDto);
            }
        }catch (Exception e) {
            System.out.println(">>postPhone : " + e);
        }
        return phoneBook;
    }
}
