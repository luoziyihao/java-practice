package com.luozi.service;

import com.luozi.dao.AbstractHibernateDao;
import com.luozi.entity.AbstractEntity;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by luoziyihao on 10/11/16.
 */
public abstract class AbstractEntityService <T extends AbstractEntity, ID extends java.io.Serializable> {

    @Autowired
    private AbstractHibernateDao<T, ID> hibernateDao;

    public AbstractHibernateDao<T, ID> getHibernateDao() {
        return hibernateDao;
    }

    public T save(T t) {
        this.hibernateDao.save(t);
        return t;
    }

    public T get(ID id) {
        return this.hibernateDao.get(id);
    }
}
