package com.asimkilic.n11.fourthhomework.usr.dao;

import com.asimkilic.n11.fourthhomework.usr.entity.UsrUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsrUserDao extends JpaRepository<UsrUser,String> {


    Optional<UsrUser> findUsrUserByTckn(Long tckn);

    Optional<UsrUser> findUsrUserByUsername(String username);


    Long countByCellPhone(String cellPhone);
    Long countByTckn(Long tckn);

}
