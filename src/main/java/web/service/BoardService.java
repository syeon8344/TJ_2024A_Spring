package web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import web.model.dao.BoardDao;
import web.model.dto.BoardDto;

import java.util.ArrayList;

@Service
public class BoardService {
    @Autowired
    BoardDao boardDao;

    // 1. 글 전체 출력
    public ArrayList<BoardDto> bAllPrint(){
        return boardDao.bAllPrint();
    }   // bAllPrint() end

    // 2. 글 쓰기 카테고리 불러오기
    public ArrayList<BoardDto> getBoardCategory() {
        return boardDao.getBoardCategory();
    }
}   // class end
