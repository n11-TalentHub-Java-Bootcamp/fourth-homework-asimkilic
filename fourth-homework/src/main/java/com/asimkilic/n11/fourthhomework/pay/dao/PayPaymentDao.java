package com.asimkilic.n11.fourthhomework.pay.dao;

import com.asimkilic.n11.fourthhomework.pay.entity.PayPayment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PayPaymentDao extends JpaRepository<PayPayment,String> {

}
