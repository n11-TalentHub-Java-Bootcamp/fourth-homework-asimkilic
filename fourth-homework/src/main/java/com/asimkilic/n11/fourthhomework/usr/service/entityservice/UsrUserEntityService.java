package com.asimkilic.n11.fourthhomework.usr.service.entityservice;

import com.asimkilic.n11.fourthhomework.gen.service.BaseEntityService;
import com.asimkilic.n11.fourthhomework.usr.dao.UsrUserDao;
import com.asimkilic.n11.fourthhomework.usr.entity.UsrUser;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UsrUserEntityService extends BaseEntityService<UsrUser, UsrUserDao> {

    public UsrUserEntityService(UsrUserDao dao) {
        super(dao);
    }

    public Optional<UsrUser> findByTckn(Long tckn) {
        return getDao().findUsrUserByTckn(tckn);
    }

    public Optional<UsrUser>  findByUsername(String username) {
        return getDao().findUsrUserByUsername(username);
    }

    public Long countUsrUserByCellPhone(String cellPhone) {
        return getDao().countByCellPhone(cellPhone);
    }
    public Long countUsrUserByTckn(Long tckn){
        return getDao().countByTckn(tckn);
    }
}
