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

    public boolean login(MemberDto dto) {
        try {
            String sql = "select no from member where id = ? and pw = ?;";
            ps = conn.prepareStatement(sql);
            ps.setString(1, dto.getId());
            ps.setString(2, dto.getPw());
            rs = ps.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            System.out.println("doSignup() : " + e);
        }
        return false;
    }
}
