package example.day07.todo;

import java.sql.*;
import java.util.ArrayList;

public class TodoDao {

    //listcode int auto_increment,
    //    listname varchar(40),
    //    listdate datetime default now(),
    //    isdone
    private static TodoDao todoDao = new TodoDao();
    private TodoDao(){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/springday07todo", "root", "1234"
            );
        }catch (Exception e){
            System.out.println(">>MessageDAO.getInstance() 오류 : " +e);
        }
    };
    public static TodoDao getInstance(){
        return todoDao;
    }
    // JDBC 인터페이스
    Connection conn;
    PreparedStatement ps;
    ResultSet rs;

    //GET 으로 띄울 todolist DB 가져오기
    public ArrayList<TodoDto> getTodoList(){
        ArrayList<TodoDto> todoList = new ArrayList<>();
        try{
            String sql = "select * from todo;";
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()){
                TodoDto todoDto = new TodoDto();
                todoDto.setListCode(rs.getInt(1));
                todoDto.setListName(rs.getString(2));
                todoDto.setListDate(rs.getString(3));
                todoDto.setDone(rs.getBoolean(4));
                todoList.add(todoDto);
            }
        }catch (Exception e){
            System.out.println("getTodoList() "+e);
        }
        return todoList;
    }

    //POST 로 할 일 이름을 받아와서 DB에 저장하기
    public boolean todoAdd(String listName) {
        try {
            String sql = "insert into todo(listName) values(?);";
            ps = conn.prepareStatement(sql);
            ps.setString(1, listName);
            int count = ps.executeUpdate();
            if (count == 1) {
                return true;
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return false;
    }


    //해당 리스트의 목록코드를 받아 상태 변경하기 (true/false)
    public boolean todoModify(int listCode) {
        try {
            String sql = "select isdone from todo where listcode = "+listCode+";";
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            rs.next();
            if (rs.getBoolean("isdone")){
                sql = "update todo set isdone = 0 where listcode = "+listCode+";";
            } else {
                sql = "update todo set isdone = 1 where listcode = "+listCode+";";
            }
            ps = conn.prepareStatement(sql);
            int count = ps.executeUpdate();
            return count==1;
        } catch (Exception e) {
            System.out.println(e);
        }
        return false;
    }


    // 해당 리스트의 목록코드를 받아 삭제하기
    public boolean todoDelete(int listCode){
        try{
            String sql = "delete from todo where listCode = "+listCode+";";
            ps = conn.prepareStatement(sql);
            int count = ps.executeUpdate();
            return count==1;
        }
        catch (Exception e ) {
            System.out.println("todoDelete() " + e);
        }
        return false;
    }

}
