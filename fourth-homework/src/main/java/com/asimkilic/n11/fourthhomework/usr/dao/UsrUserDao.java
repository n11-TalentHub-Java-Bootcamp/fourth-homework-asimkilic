package com.asimkilic.n11.fourthhomework.usr.dao;

import com.asimkilic.n11.fourthhomework.usr.entity.UsrUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsrUserDao extends JpaRepository<UsrUser,String> {
}
