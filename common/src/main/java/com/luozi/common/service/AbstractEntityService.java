package com.luozi.common.service;

import com.luozi.common.dao.AbstractHibernateDao;
import com.luozi.common.entity.AbstractEntity;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by luoziyihao on 10/11/16.
 */
public abstract class AbstractEntityService <M extends AbstractEntity, ID extends java.io.Serializable> {

    @Autowired
    private AbstractHibernateDao<M, ID> hibernateDao;

    public AbstractHibernateDao<M, ID> getHibernateDao() {
        return hibernateDao;
    }

    public M save(M m) {
        this.hibernateDao.save(m);
        return m;
    }

    public M findOne(ID id) {
        return this.hibernateDao.findOne(id);
    }
}
