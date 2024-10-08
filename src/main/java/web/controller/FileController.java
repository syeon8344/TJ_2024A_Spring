package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import web.service.FileService;

@RestController
public class FileController {

    @Autowired private FileService fileService;
    // 1. 파일 다운로드 요청 처리, 매개변수 : 다운로드 받을 파일명
    @GetMapping("/file/download")
    public void fileDownload(String filename){
        fileService.fileDownload(filename);
    }
}
