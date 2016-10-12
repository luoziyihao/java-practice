package com.luozi.dao;

import com.luozi.entity.User;
import org.springframework.stereotype.Repository;

/**
 * Created by luoziyihao on 10/11/16.
 */
@Repository
public class UserDao extends AbstractHibernateDao<User, Long> {
}
