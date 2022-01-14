package com.asimkilic.n11.fourthhomework.dbt.service.entityservice;

import com.asimkilic.n11.fourthhomework.dbt.dao.DbtDebtDao;
import com.asimkilic.n11.fourthhomework.dbt.entity.DbtDebt;
import com.asimkilic.n11.fourthhomework.gen.service.BaseEntityService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class DbtDebtEntityService extends BaseEntityService<DbtDebt, DbtDebtDao> {

    public DbtDebtEntityService(DbtDebtDao dao) {
        super(dao);
    }

    public List<DbtDebt> findAllDbtDebtBetweenDates(LocalDateTime startDate, LocalDateTime endDate){
        return getDao().findAllByCreationDateLessThanEqualAndCreationDateGreaterThanEqual(endDate,startDate);
    }
    public Long findIsUserRegisteredNative(String userId){
        return getDao().findIsUserRegisteredNative(userId);
    }
    public List<DbtDebt> findAllUnpaidDbtDebtByUserId(String usrUserId){
        return getDao().findAllByUsrUser_IdAndRemainingDebtGreaterThan(usrUserId, BigDecimal.ZERO);
    }
    public List<DbtDebt> findAllUnpaidOverdueDbtDebtByUserId(String usrUserId, LocalDateTime localDateTimeNow) {
         return getDao().findAllByUsrUser_IdAndFallDueOnBeforeAndRemainingDebtGreaterThan(usrUserId, localDateTimeNow,BigDecimal.ZERO);
    }

    public DbtDebt findUnpaidDbtDebtByDebtIdForPaymentService(String dbtDebtId){
        return getDao().findDbtDebtByIdAndRemainingDebtGreaterThan(dbtDebtId,BigDecimal.ZERO);
    }

}
