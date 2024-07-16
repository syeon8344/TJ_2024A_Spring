package example.day09.todo;

import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.ArrayList;

@Component // 스프링 컨테이너에 빈/객체 등록
public class TodoDao {

    // JDBC 인터페이스
    Connection conn;
    PreparedStatement ps;
    ResultSet rs;

    private TodoDao(){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/springexample", "root", "1234"
            );
        }catch (Exception e){
            System.out.println(">>MessageDAO.getInstance() 오류 : " +e);
        }
    };

    // 1. 할 일 등록
    public boolean todoCreate(String tContent){
        try{
            String sql = "insert into todolist(tContent) values(?);";
            ps = conn.prepareStatement(sql);
            ps.setString(1,tContent);
            int count = ps.executeUpdate();
            if (count == 1)
                return true;
        }catch (SQLException e) {
            System.out.println(e);
        }
        return false;
    }
    // 2. 할일 전체출력
    public ArrayList<TodoDto> todoReadAll() {
        ArrayList<TodoDto> list = new ArrayList<>();
        try {
            String sql = "select * from todolist;";
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()){
                TodoDto dto = new TodoDto();
                dto.setTno(rs.getInt(1));
                dto.setTcontent(rs.getString(2));
                dto.setTstate(rs.getInt(3));
                list.add(dto);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }
    // 3. 할일 상태 수정
    public boolean todoUpdate(int tno) {
        try{
            String sql = "select tState from todolist where tno = ?;";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, tno);
            rs = ps.executeQuery();
            if(rs.next()){
                int stateValue = rs.getInt(1) == 0 ? 1 : 0;
                sql = "update todolist set tState = ? where tno = ?;";
                ps = conn.prepareStatement(sql);
                ps.setInt(1, stateValue);
                ps.setInt(2, tno);
                int count = ps.executeUpdate();
                return count == 1;
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return false;
    }
    // 4. 할일 삭제
    public boolean todoDelete(int tno){
        try{
            String sql = "delete from todolist where tno = ?;";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, tno);
            int count = ps.executeUpdate();
            return count==1;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
