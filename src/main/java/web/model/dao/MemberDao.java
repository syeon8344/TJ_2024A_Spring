package web.model.dao;

import org.springframework.stereotype.Component;
import web.model.dto.MemberDto;

import java.sql.SQLException;
import java.util.ArrayList;

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

    public boolean idCheck(String id) {
        try{
            String sql = "select id from member where id = ?;";
            ps = conn.prepareStatement(sql);
            ps.setString(1, id);
            rs = ps.executeQuery();
            return rs.next();
        }catch(Exception e){
            System.out.println("idCheck() : " + e);
        }
        return false;
    }

    public boolean updateInfo(MemberDto dto, int no) {
        try {
            ArrayList<String>argList = new ArrayList<>();
            String sql = "update member set ";
            if (dto == null) {return false;}
            if (dto.getName()!=null){
                sql += "name = ?, ";
                argList.add(dto.getName());
            }
            if (dto.getPhone()!=null){
                sql += "phone = ?, ";
                argList.add(dto.getPhone());
            }
            if (dto.getEmail()!=null){
                sql += "email = ?, ";
                argList.add(dto.getEmail());
            }
            sql += "no = "+no+" where no = " +no+";";
            ps = conn.prepareStatement(sql);
            for(int i = 0; i < argList.size(); i++){
                ps.setString(i+1, argList.get(i));
            }
            int count = ps.executeUpdate();
            return count==1;
        } catch (Exception e) {
            System.out.println(e);
        }
        return false;
    }

    public boolean delAccount(MemberDto dto, int no) {
        try{
            String sql = "delete from member where no=? and pw=?";
            ps = conn.prepareStatement(sql);
            ps.setString(2,dto.getPw()); ps.setInt(1,no);
            int count = ps.executeUpdate();
            return count==1;
        } catch(Exception e){
            System.out.println(e);
        }
        return false;
    }
}
