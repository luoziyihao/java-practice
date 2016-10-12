package com.luozi.common.dao;

import com.luozi.common.entity.AbstractEntity;
import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;

/**
 * Created by luoziyihao on 10/11/16.
 */
public abstract class AbstractHibernateDao<T extends AbstractEntity, ID extends Serializable> extends HibernateDaoSupport {

    // 泛型反射类
    private Class<T> entityClass;

    @Resource(name = "sessionFactory")
    private SessionFactory sessionFactory;

    // 通过反射获取子类确定的泛型类
    @SuppressWarnings({ "rawtypes", "unchecked" })
    public AbstractHibernateDao() {
        this.entityClass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }

    @PostConstruct
    public final void init() {
        setSessionFactory(this.sessionFactory);
    }


    @SuppressWarnings("unchecked")
    public ID save(T t){
        return (ID)currentSession().save(t);
    }

    @SuppressWarnings("unchecked")
    public T findOne(ID id) {
        return (T)currentSession().get(this.entityClass, id);
    }
}
