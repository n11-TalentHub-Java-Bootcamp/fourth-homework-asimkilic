package com.asimkilic.n11.fourthhomework.dbt.dao;

import com.asimkilic.n11.fourthhomework.dbt.entity.DbtDebt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DbtDebtDao extends JpaRepository<DbtDebt,String> {
}
