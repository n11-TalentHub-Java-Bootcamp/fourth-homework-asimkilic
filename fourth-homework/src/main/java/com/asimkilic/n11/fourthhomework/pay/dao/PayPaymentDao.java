package com.asimkilic.n11.fourthhomework.pay.dao;

import com.asimkilic.n11.fourthhomework.pay.entity.PayPayment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface PayPaymentDao extends JpaRepository<PayPayment,String> {

    List<PayPayment> findAllByCreationDateGreaterThanEqualAndCreationDateLessThanEqual(LocalDateTime startDate,LocalDateTime endDate);
    List<PayPayment> findAllByUsrUser_Id(String usrUserid);
}
