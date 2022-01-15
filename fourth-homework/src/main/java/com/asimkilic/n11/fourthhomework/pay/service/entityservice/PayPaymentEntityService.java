package com.asimkilic.n11.fourthhomework.pay.service.entityservice;

import com.asimkilic.n11.fourthhomework.gen.service.BaseEntityService;
import com.asimkilic.n11.fourthhomework.pay.dao.PayPaymentDao;
import com.asimkilic.n11.fourthhomework.pay.dto.PayPaymentDto;
import com.asimkilic.n11.fourthhomework.pay.entity.PayPayment;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class PayPaymentEntityService  extends BaseEntityService<PayPayment, PayPaymentDao> {

    public PayPaymentEntityService(PayPaymentDao dao) {
        super(dao);
    }

    public List<PayPayment> findAllPaymentsBetweenDates(LocalDateTime startDate, LocalDateTime endDate) {
        return getDao().findAllByCreationDateGreaterThanEqualAndCreationDateLessThanEqual(startDate,endDate);
    }

    public List<PayPayment> findAllByUserId(String usrUserId) {

        return getDao().findAllByUsrUser_Id(usrUserId);
    }
}
