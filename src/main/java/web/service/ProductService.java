package web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import web.model.dao.ProductDao;
import web.model.dto.ProductDto;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductDao productDao;
    @Autowired
    private FileService fileService;
    
    // 1. 제품등록
    public boolean prodRegister(ProductDto dto) {

        // 첨부파일 여러개 업로드하기
        // 파일이름 리스트
        List<String> fileNames = new ArrayList<>();
        // 1. 첨부파일 개수만큼 반복문 돌리기
        dto.getFiles().forEach(file ->{
            // 2. 각 첨부파일마다 업로드메서드 대입
            String fileName = fileService.fileUpload(file);
            if(fileName != null){
                // 3. 업로드된 파일명을 리스트에 담기 (DB에 파일명 저장)
                fileNames.add(fileName);
            }

        });
        System.out.println("fileNames = " + fileNames);
        dto.setFileNames(fileNames);
        return productDao.prodRegister(dto);
    }

    // 2. 전체 제품 목록 조회
    public List<ProductDto> getProductList(){
        return productDao.getProductList();
    }
}
