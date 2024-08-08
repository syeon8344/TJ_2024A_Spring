package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import web.model.dto.ProductDto;
import web.service.ProductService;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    // @RequestParam List<MultipartFile> files : 첨부파일만 전송시 MultipartFile List로 받는 방법
    // 첨부파일 여러 개 포함한 제품 등록
    @PostMapping("/register")
    public boolean prodRegister(ProductDto dto){
        System.out.println("dto = " + dto);
        return productService.prodRegister(dto);
    }

    // 전체 제품 목록 조회
    @GetMapping("/getProductList")
    public List<ProductDto> getProductList(){
        return productService.getProductList();
    }
}
