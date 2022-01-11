package com.asimkilic.n11.fourthhomework.gen.service;

import com.asimkilic.n11.fourthhomework.gen.entity.BaseEntity;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
@AllArgsConstructor
public abstract class BaseEntityService<E extends BaseEntity,D extends JpaRepository> {
    private D dao;

    public List<E> findAll(){
        return dao.findAll();
    }

    public Optional<E> findById(String id){
        return dao.findById(id);
    }

    public E save(E e){
        return (E) dao.save(e);
    }

    public void delete(E e){
        dao.delete(e);
    }

    public D getDao() {
        return dao;
    }
}
