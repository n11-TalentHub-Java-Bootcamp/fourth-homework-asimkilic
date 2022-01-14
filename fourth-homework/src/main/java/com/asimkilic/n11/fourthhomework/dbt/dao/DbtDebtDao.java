package com.asimkilic.n11.fourthhomework.dbt.dao;

import com.asimkilic.n11.fourthhomework.dbt.entity.DbtDebt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface DbtDebtDao extends JpaRepository<DbtDebt, String> {

    List<DbtDebt> findAllByCreationDateLessThanEqualAndCreationDateGreaterThanEqual(LocalDateTime endDate, LocalDateTime startDate);

    List<DbtDebt> findAllByUsrUser_IdAndRemainingDebtGreaterThan(String userId, BigDecimal debtGreaterThan);
    List<DbtDebt> findAllByUsrUser_IdAndFallDueOnBeforeAndRemainingDebtGreaterThan(String userId,LocalDateTime onBefore,BigDecimal remainingDebtGreaterThan);

    @Query(
            value = "select count(*) from usr_user u where u.id= :userId",
            nativeQuery = true)
    Long findIsUserRegisteredNative(String userId);
}
