package example.day08.board;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class BoardDao {
    // JDBC 인터페이스
    Connection conn;
    PreparedStatement ps;
    ResultSet rs;
    // 싱글톤
    private static BoardDao boardDao = new BoardDao();
    private BoardDao(){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/springday08board", "root", "1234"
            );
        }catch (Exception e){
            System.out.println(">>MessageDAO.getInstance() 오류 : " +e);
        }
    }
    public static BoardDao getInstance(){
        return boardDao;
    }
    // 싱글톤 패턴 끝

    // 1.글추가
    public boolean boardWrite(BoardDto dto){
        try{
            String sql = "insert into board(btitle, bcontent, bpassword) values (?,?,?);";
            ps = conn.prepareStatement(sql);
            ps.setString(1, dto.getBtitle());
            ps.setString(2, dto.getBcontent());
            ps.setString(3, dto.getBpassword());
            int count = ps.executeUpdate();
            return count == 1;
        }catch (Exception e){
            System.out.println("boardWrite : " +e);
        }
        return false;
    }
    // 2.글출력
    public ArrayList<BoardDto> boardReadAll(){
        ArrayList<BoardDto> list = new ArrayList<>();
        try {
            String sql = "select * from board;";
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()){
                BoardDto dto = new BoardDto();
                dto.setBno(rs.getInt(1));
                dto.setBdate(rs.getString(2));
                dto.setBtitle(rs.getString(3));
                dto.setBview(rs.getInt(4));
                dto.setBcontent(rs.getString(5));
                dto.setBpassword(rs.getString(6));
                list.add(dto);
            }
        } catch (Exception e)
        {
            System.out.println("boardReadAll : " + e);
        }
        return list;
    }
    // 2-1.상세글보기
    public BoardDto boardView(int bno) {
        BoardDto dto = new BoardDto();
        try {
            String sql = "select * from board where bno = ?;";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, bno);
            rs = ps.executeQuery();
            while (rs.next()){
                dto.setBno(rs.getInt(1));
                dto.setBdate(rs.getString(2));
                dto.setBtitle(rs.getString(3));
                dto.setBview(rs.getInt(4));
                dto.setBcontent(rs.getString(5));
                dto.setBpassword(rs.getString(6));
            }
        } catch (Exception e)
        {
            System.out.println("boardReadAll : " + e);
        }
        return dto;
    }

    // 3.글수정
    public boolean boardEdit(BoardDto dto){
        try {
            String sql = "update board set bcontent = ? where bno = ?;";
            ps = conn.prepareStatement(sql);
            ps.setString(1, dto.getBcontent());
            ps.setInt(2, dto.getBno());
            return ps.executeUpdate() == 1;
        } catch (Exception e)
        {
            System.out.println("boardEdit : " + e);
        }
        return false;
    }
    // 4.글삭제
    public boolean boardDelete(int bno){
        try {
            String sql = "delete from board where bno = ?;";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, bno);
            return ps.executeUpdate() == 1;
        } catch (Exception e)
        {
            System.out.println("boardDelete : " + e);
        }
        return false;
    }
    // 5. 비밀번호체크
    public boolean boardCheckPassWord(BoardDto dto){
        try {
            String sql = "select btitle from board where bno = ? and bpassword = ?;";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, dto.getBno());
            ps.setString(2, dto.getBpassword());
            rs = ps.executeQuery();
            return rs!=null;
        } catch (Exception e)
        {
            System.out.println("boardDelete : " + e);
        }
        return false;
    }


}
