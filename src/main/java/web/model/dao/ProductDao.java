package web.model.dao;

import org.springframework.stereotype.Component;
import web.model.dto.ProductDto;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

@Component
public class ProductDao extends Dao{

    // 제품 등록 : 1.제품 등록, 2. 제품 이미지 등록 ( 순서대로 )
    public boolean prodRegister(ProductDto dto) {
        System.out.println("dto = " + dto);
        // 각 테이블에 DTO 정보를 입력
        try {
            // 1. 제품 등록
            String sql = "insert into product(pname,pcontent,pprice) values(?,?,?)"; // sql 구문 준비하기 (? = 와일드카드, ps.set"데이터타입"() 함수로 등록
                // * JDBC에서 insert한 레코드의 자동번호(auto_increment)가 부여된 pk번호 반환하기
                    // 1. conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS) : 생성된 키를 반환
                    // 2. ResultSet pkRs = ps.getGeneratedKeys(); : 생성된 키가 들어있는 ResultSet
            PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            // sql 와일드카드 채우기
            ps.setString(1, dto.getPname());
            ps.setString(2, dto.getPcontent());
            ps.setInt(3, dto.getPprice());
            int count = ps.executeUpdate(); // executeUpdate가 반환하는 것 : 수정된 레코드 수, 1 == 레코드 잘 등록됨
            if (count == 1){ // 등록된 레코드 1개 = 제품 등록 성공
                // 2. 제품 이미지 등록
                ResultSet psKeys = ps.getGeneratedKeys(); // 저장해둔 등록된 레코드 정보를 ResultSet으로 받아오기
                if (psKeys.next()){ // next()가 참 : 레코드가 있다 -> 방금 등록된 제품 레코드
                    System.out.println("psKeys. = " + psKeys);
                    System.out.println("GENERATED_KEYS pno : " + psKeys.getInt(1));
                    int pno = psKeys.getInt(1); // 제품 등록 후 받아온 pno
                    dto.getFileNames().forEach(name ->{ // 파일 이름 수만큼 for 문 == 파일 개수만큼 insert 문 실행
                        try {
                            String sql2 = "insert into productimage(pimgname, pno) values(?,?)";
                            PreparedStatement ps2 = conn.prepareStatement(sql2);
                            ps2.setString(1, name); ps2.setInt(2, pno);
                            int count2 = ps2.executeUpdate();
                            if (count2 != 1) {throw new Exception("Exception while inserting Images");}
                        } catch (Exception e) {
                            System.out.println("prodRegisterImageForEach : " +e);
                        }

                    });

                    return true; // forEach 모두 성공 후 true 리턴
                }

            }
        } catch (Exception e) {
            System.out.println("prodRegister : " + e);
        }
        return false;
    }

    // 제품 목록 전체 출력 (각 제품 : ProductDto, 제품목록 : List<ProductDto>)
    public List<ProductDto> getProductList(){
        List<ProductDto> dtoList = new ArrayList<>();
        try { // 1. 전체 제품 조회
            String sql = "select * from product;";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){ // 2. 각 제품마다 이미지 목록 조회, select 루프 : pno
                ProductDto dto = ProductDto.builder() // dto builder
                        .pno(rs.getInt(1))
                        .pname(rs.getString(2))
                        .pcontent(rs.getString(3))
                        .pprice(rs.getInt(4))
                        .pdate(rs.getString(5))
                        .pview(rs.getInt(6))
                        .build();
                List<String> nameList = new ArrayList<>(); // 제품마다의 이미지 목록
                try{
                    String sql1 = "select pimgname from productImage where pno = "+rs.getInt(1)+";"; // 제품 이미지 파일명 조회
                    PreparedStatement ps1 = conn.prepareStatement(sql1);
                    ResultSet rs1 = ps1.executeQuery(); // rs1 : 검색된 이미지 파일명 목록
                    while (rs1.next()){ // 제품 각각의 이미지별 파일명을 목록에 추가
                        nameList.add(rs1.getString(1));
                    }
                }catch(Exception e){
                    System.out.println(e);
                }
                dto.setFileNames(nameList); // 완성된 이미지 파일명 목록을 dto에 추가
                dtoList.add(dto); // 완성된 dto를 dtoList에 추가
            }
        } catch (Exception e) {
            System.out.println("getProductList() : " + e);;
        }
        return dtoList;
    }
}
