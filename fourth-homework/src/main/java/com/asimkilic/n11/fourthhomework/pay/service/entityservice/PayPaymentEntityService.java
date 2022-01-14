package com.asimkilic.n11.fourthhomework.pay.service.entityservice;

import com.asimkilic.n11.fourthhomework.gen.service.BaseEntityService;
import com.asimkilic.n11.fourthhomework.pay.dao.PayPaymentDao;
import com.asimkilic.n11.fourthhomework.pay.entity.PayPayment;
import org.springframework.stereotype.Service;

@Service
public class PayPaymentEntityService  extends BaseEntityService<PayPayment, PayPaymentDao> {

    public PayPaymentEntityService(PayPaymentDao dao) {
        super(dao);
    }

}
