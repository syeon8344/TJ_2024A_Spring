package web.model.dto;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Builder
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ProductDto {
    // 첨부파일 여러개 파일
    private List<MultipartFile> files;
    // 첨부파일 이름들
    private List<String> fileNames;
    // 제품명
    private String pname;
    // 제품설명
    private String pcontent;
    // 제품가격
    private int pprice;
    // 등록날짜
    private String pdate;
    // 제품조회수
    private int pview;
}
