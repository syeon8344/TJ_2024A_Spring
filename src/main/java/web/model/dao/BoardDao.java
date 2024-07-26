package web.model.dao;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import web.model.dto.BoardDto;

import java.sql.Blob;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

@Component
public class BoardDao extends Dao{

    // 1. 글 전체 출력
    public ArrayList<BoardDto> bAllPrint(){
        ArrayList<BoardDto> list = new ArrayList<>();
        try{
            String sql = "select * from board inner join member inner join bcategory on board.no = member.no and board.bcno = bcategory.bcno;";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                BoardDto boardDto = BoardDto.builder()
                        .bcname(rs.getString("bcname"))
                        .bno(rs.getInt("bno"))
                        .btitle(rs.getString("btitle"))
                        .id(rs.getString("id"))
                        .bdate(rs.getString("bdate"))
                        .bview(rs.getInt("bview"))
                        .build();
                list.add(boardDto);
            }
        }catch (Exception e){
            System.out.println(e);
        }
        return list;
    }   // bAllPrint() end

    // 카테고리 불러오기
    public ArrayList<BoardDto> getBoardCategory() {
        try{
            ArrayList<BoardDto> list = new ArrayList<>();
            String sql = "select bcno, bcname from bcategory;";
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while(rs.next()){
                BoardDto dto = BoardDto.builder()
                        .bcno(rs.getInt(1))
                        .bcname(rs.getString(2))
                        .build();
                list.add(dto);
            }
            return list;
        }catch (Exception e){
            System.out.println("getBoardCategory()" + e);
        }
        return null;
    }

    // 글 쓰기
    public boolean bWrite(BoardDto boardDto,int loginMno){
        try {
            String sql="insert into board(btitle,bcontent,no,bcno,bfile) values(?,?,?,?,?);";
            PreparedStatement ps=conn.prepareStatement(sql);
            ps.setString(1,boardDto.getBtitle());
            ps.setString(2,boardDto.getBcontent());
            ps.setInt(3,loginMno);
            ps.setLong(4,boardDto.getBcno());
            ps.setString(5, boardDto.getBfile());
            int count = ps.executeUpdate();
            if(count==1){
                return true;
            }
            return true;
        }catch (Exception e){System.out.println("e = " + e);}
        return false;
    }

    // 4. 상세페이지
    public BoardDto bRead(int bno){
        try{
            //select * from board b /inner join member m /inner join bcategory bc /on b.no = m.no /and b.bcno = bc.bcno...
            String sql = "select * from board inner join member on board.no = member.no inner join bcategory on board.bcno = bcategory.bcno where bno = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1,bno);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                BoardDto boardDto = BoardDto.builder()
                        .bcname(rs.getString("bcname"))
                        .bcno(rs.getInt("bcno"))
                        .bno(rs.getInt("bno"))
                        .btitle(rs.getString("btitle"))
                        .bcontent(rs.getString("bcontent"))
                        .id(rs.getString("id"))
                        .bdate(rs.getString("bdate"))
                        .bview(rs.getInt("bview"))
                        .bfile(rs.getString("bfile"))
                        .build();
                return boardDto;
            }
        }catch (Exception e){
            System.out.println(e);
        }
        return null;
    }
    
    // 글 삭제
    public boolean bDelete(int loginMno, int bno) {
        try{
            String sql = "delete from board where bno=? and no=?;";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1,bno); ps.setInt(2, loginMno);
            int count = ps.executeUpdate();
            return count==1;
        }catch (Exception e){
            System.out.println(e);
        }
        return false;
    }
    
    // 글 수정
    public boolean bEdit(int loginMno, BoardDto dto) {
        try{
            String sql = "update board set btitle=?, bcontent=?, bcno=? where bno=? and no=?;";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, dto.getBtitle()); ps.setString(2, dto.getBcontent());
            ps.setLong(3, dto.getBcno()); ps.setLong(4, dto.getBno()); ps.setInt(5, loginMno);
            int count = ps.executeUpdate();
            return count==1;
        } catch (Exception e){
            System.out.println(e);
        }
        return false;
    }
    // 5-1. 글 수정 권한 확인
    public boolean bEditCheck(int loginMno, int bno) {
        try{
            String sql = "select bno from board where bno = ? and no = ?;";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, bno); ps.setInt(2, loginMno);
            rs = ps.executeQuery();
            return rs.next();
        } catch (Exception e){
            System.out.println(e);
        }
        return false;
    }
    // 4-1. 상세페이지 진입시 조회수 증가
    public void bView(int bno) {
        try{
            String sql = "update board set bview = bview + 1 where bno=?;";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, bno);
            ps.executeUpdate();
        } catch (Exception e){
            System.out.println(e);
        }
    }
}   // class end
