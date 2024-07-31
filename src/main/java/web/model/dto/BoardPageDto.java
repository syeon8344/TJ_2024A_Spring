package web.model.dto;

import lombok.*;

import java.util.List;

@Builder
@Getter @Setter @ToString
@AllArgsConstructor
@NoArgsConstructor
public class BoardPageDto {
    // - 검색, 카테고리별 페이징처리에 다양한 매개변수가 필요하므로 묶음처리
        // (지역/매개)변수와 필드 차이 : 초기값
    private int page;
    private int totalBoardSize; // 전체 게시물 수
    private int totalPage; // 전체 페이지수
    private List<BoardDto> data; // 조회된 게시물 정보 목록
    private int startBtn; // 페이지별 시작버튼 번호
    private int endBtn; // 페이지별 끝버튼 번호
    private int bcno; // 현재 카테고리 번호
    // 검색 필드
    private String searchKey; // 검색 조회시 사용되는 필드명
    private String searchKeyword; // 검색 조회시 사용되는 필드값

}
