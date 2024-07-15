package example.day08.todo;

import java.sql.*;
import java.util.ArrayList;

public class TodoDao {
    // [1] 싱글톤
    private static TodoDao todoDao = new TodoDao();
    public static TodoDao getInstance(){
        return todoDao;
    }
    // [2] JDBC 인터페이스
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
    public boolean todoCreate(String tcontent){
        try{
            String sql = "insert into todolist(tcontent) values(?);";
            ps = conn.prepareStatement(sql);
            ps.setString(1,tcontent);
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
            String sql = "select tstate from todolist where tno = ?;";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, tno);
            rs = ps.executeQuery();
            if(rs.next()){
                int stateValue = rs.getInt(1) == 0 ? 1 : 0;
                sql = "update todolist set tstate = ? where tno = ?;";
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
