package web.model.dao;

import org.springframework.stereotype.Component;
import web.model.dto.BoardDto;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class BoardDao extends Dao{

    // 1. 글 전체 출력
    public ArrayList<BoardDto> bAllPrint(int bcno, int startRow, int pageSize, String searchKey, String searchKeyword){
        ArrayList<BoardDto> list = new ArrayList<>();
        try{
            String sql = "select * from board " +                               // 1. 조회
                    "inner join member inner join bcategory " +                 // 2. 조인 테이블
                    "on board.no = member.no and board.bcno = bcategory.bcno "; // 3. on 조인조건
                    if (bcno > 0){sql += "where board.bcno="+bcno+" ";}         // 전체보기면 where 생략
                    if (!searchKeyword.isEmpty()){
                        if (bcno > 0){
                            sql += " and ";
                        } else {
                            sql += " where ";
                        }
                        sql += searchKey + " like '%" + searchKeyword + "%' ";
                    }
                    sql += "order by board.bno desc limit ?,?;";                // 레코드 제한

            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, startRow); ps.setInt(2,pageSize);
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

    // 3-2. 총 페이지 수 불러오기
    public int getTotalBoardSize(int bcno, String searchKey, String searchKeyword){
        try {
            String sql = "select count(*) from board inner join member on board.no = member.no";
            if (bcno > 0){sql += " where bcno =" + bcno + " ";} // bcno 0 이상 : 카테고리 선택됨
            if (!searchKeyword.isEmpty()){
                if (bcno > 0){ // 전체 검색 여부
                    sql += " and ";
                } else {
                    sql += " where ";
                }
                sql += searchKey + " like '%" + searchKeyword +"%' "; // 검색 SQL
            }
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (Exception e){
            System.out.println(e);
        }
        return 0;
    }
    // 카테고리 불러오기
    public ArrayList<BoardDto> getBoardCategory() {
        try{
            ArrayList<BoardDto> list = new ArrayList<>();
            String sql = "select bcno, bcname from bcategory order by bcno;";
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

    public boolean authorize(int bno, int loginMno) {
        try{
            String sql = "select bno from board where bno=? and no=?;";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, bno); ps.setInt(2, loginMno);
            rs = ps.executeQuery();
            return rs.next();
        } catch (Exception e){
            System.out.println(e);
        }
        return false;
    }
    // 글 수정시 기존 첨부파일 주소 얻기
    public String getFileName(long bno) {
        try{
            String sql = "select bfile from board where bno=?;";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setLong(1, bno);
            rs = ps.executeQuery();
            rs.next();
            return rs.getString(1);
        } catch (Exception e){
            System.out.println(e);
        }
        return null;
    }

    public boolean bReplyWrite(Map<String, String> map) {
        System.out.println("map = " + map);
        try{
            // brindex : 댓글 Z-index, brcontent : 댓글내용, no : 회원번호, bno : 게시물번호(댓글 위치한 게시물)
            String sql = "insert into breply(brindex,brcontent,no,bno) values(?,?,?,?);";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, Integer.parseInt(map.get("brindex"))); // value도 String이므로 Integer.parseInt()로 int화 필요
            ps.setString(2, map.get("brcontent"));
            ps.setInt(3, Integer.parseInt(map.get("no")));
            ps.setInt(4, Integer.parseInt(map.get("bno")));

            int count = ps.executeUpdate();
            return count==1;
        }catch(Exception e){
            System.out.println(e);
        }
        return false; // true/false booleaan값 반환
    }

    public List<Map<String, String>> bReplyFindBno(int bno) {
        try{
            List<Map<String, String>> list = new ArrayList<>();
            String sql = "select breply.*, member.name from breply inner join member on breply.no = member.no where bno=? order by brno desc;";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setLong(1, bno);
            rs = ps.executeQuery();
            while (rs.next()){
                Map<String,String> map = new HashMap<>();
                map.put("brno", String.valueOf(rs.getInt("brno")));
                map.put("brindex", String.valueOf(rs.getInt("brindex")));
                map.put("brcontent", rs.getString("brcontent"));
                map.put("brdate", rs.getString("brdate"));
                map.put("name", rs.getString("name"));
                list.add(map);
            }
            System.out.println("list = "+list);
            return list;
        } catch (Exception e){
            System.out.println(e);
        }
        return null;
    }
}   // class end
