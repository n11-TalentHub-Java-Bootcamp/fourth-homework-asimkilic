package com.asimkilic.n11.fourthhomework.usr.service.entityservice;

import com.asimkilic.n11.fourthhomework.gen.service.BaseEntityService;
import com.asimkilic.n11.fourthhomework.usr.dao.UsrUserDao;
import com.asimkilic.n11.fourthhomework.usr.entity.UsrUser;
import org.springframework.stereotype.Service;

@Service
public class UsrUserEntityService extends BaseEntityService<UsrUser, UsrUserDao> {

    public UsrUserEntityService(UsrUserDao dao) {
        super(dao);
    }

}
