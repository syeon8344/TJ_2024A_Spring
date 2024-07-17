package web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import web.model.dao.MemberDao;
import web.model.dto.MemberDto;

@Service
public class MemberService {
    @Autowired
    MemberDao memberDao;

    public boolean doSignup(MemberDto dto) {
        if (dto.getId().isBlank() || dto.getPw().isBlank() || dto.getName().isBlank() || dto.getPhone().isBlank() || dto.getEmail().isBlank()){
            return false;
        } else {
            return memberDao.doSignup(dto);
        }
    }

    public boolean login(MemberDto dto) {
        if (dto.getId().isBlank() || dto.getPw().isBlank()){
            return false;
        } else {
            return memberDao.login(dto);
        }
    }
}
