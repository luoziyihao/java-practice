package com.luozi.springdatajpa.repository;

import com.luozi.springdatajpa.domain.User;
import com.sishuok.es.common.repository.BaseRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by luoziyihao on 8/7/16.
 */
public interface UserRepository extends JpaRepository<User, Long> {
}
