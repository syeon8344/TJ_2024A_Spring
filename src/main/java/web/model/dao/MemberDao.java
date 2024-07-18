package web.model.dao;

import org.springframework.stereotype.Component;
import web.model.dto.MemberDto;

import java.sql.SQLException;

@Component
public class MemberDao extends Dao{
    public boolean doSignup(MemberDto dto) {
        try {
            String sql = "insert into member(id, pw, name, email, phone) values(?,?,?,?,?);";
            ps = conn.prepareStatement(sql);
            ps.setString(1, dto.getId());
            ps.setString(2, dto.getPw());
            ps.setString(3, dto.getName());
            ps.setString(4, dto.getEmail());
            ps.setString(5, dto.getPhone());
            int count = ps.executeUpdate();
            return count==1;
        } catch (SQLException e) {
            System.out.println("doSignup() : " + e);
        }
        return false;
    }

    public int login(MemberDto dto) {
        try {
            String sql = "select no from member where id = ? and pw = ?;";
            ps = conn.prepareStatement(sql);
            ps.setString(1, dto.getId());
            ps.setString(2, dto.getPw());
            rs = ps.executeQuery();
            if(rs.next()){
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            System.out.println("doSignup() : " + e);
        }
        return 0;
    }

    public String findId(MemberDto dto) {
        try{
            String sql = "select id from member where name = ? and phone = ?;";
            ps = conn.prepareStatement(sql);
            ps.setString(1, dto.getName());
            ps.setString(2, dto.getPhone());
            rs = ps.executeQuery();
            if(rs.next()){
                return rs.getString(1);
            }
        }catch (Exception e){
            System.out.println("findId() : " + e);
        }
        return "";
    }

    public String findPw(MemberDto dto) {
        try{
            String sql = "select pw from member where id = ? and phone = ?;";
            ps = conn.prepareStatement(sql);
            ps.setString(1, dto.getId());
            ps.setString(2, dto.getPhone());
            rs = ps.executeQuery();
            if(rs.next()){
                return rs.getString(1);
            }
        }catch (Exception e){
            System.out.println("findPw() : " + e);
        }
        return "";
    }

    public MemberDto mInfo(int no) {
        try{
            String sql = "select * from member where no = ?;";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, no);
            rs = ps.executeQuery();
            if(rs.next()){
                return MemberDto.builder()
                        .no(rs.getInt("no"))
                        .id(rs.getString("id"))
                        .name(rs.getString("name"))
                        .phone(rs.getString("phone"))
                        .email(rs.getString("email"))
                        .build();
            }
        }catch (Exception e){
            System.out.println("mInfo() : " + e);
        }
        return null;
    }
}
