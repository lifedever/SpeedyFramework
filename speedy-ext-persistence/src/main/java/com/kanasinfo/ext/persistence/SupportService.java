package com.kanasinfo.ext.persistence;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;

/**
 * 核心 Service 类
 * Created by gefangshuai on 2015/11/16.
 */
@Transactional(readOnly = true)
public abstract class SupportService<T, ID extends Serializable> {
    protected abstract SupportRepository<T, ID> getRepository();

    public T findOne(ID id) {
        return getRepository().findOne(id);
    }

    @Transactional
    public T save(T t) {
        return getRepository().save(t);
    }

    public List<T> findAll() {
        return getRepository().findAll();
    }

    public List<T> findAll(Sort sort) {
        return getRepository().findAll(sort);
    }

    public Page<T> findAll(Pageable pageable){
        return getRepository().findAll(pageable);
    }

    @Transactional
    public void delete(ID id) {
        getRepository().delete(id);
    }

    public long count(){
        return getRepository().count();
    }

}
