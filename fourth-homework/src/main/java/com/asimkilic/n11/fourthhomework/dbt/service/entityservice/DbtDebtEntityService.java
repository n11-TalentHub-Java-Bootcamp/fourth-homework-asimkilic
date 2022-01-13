package com.asimkilic.n11.fourthhomework.dbt.service.entityservice;

import com.asimkilic.n11.fourthhomework.dbt.dao.DbtDebtDao;
import com.asimkilic.n11.fourthhomework.dbt.entity.DbtDebt;
import com.asimkilic.n11.fourthhomework.gen.service.BaseEntityService;
import org.springframework.stereotype.Service;

@Service
public class DbtDebtEntityService extends BaseEntityService<DbtDebt, DbtDebtDao> {

    public DbtDebtEntityService(DbtDebtDao dao) {
        super(dao);
    }

}
