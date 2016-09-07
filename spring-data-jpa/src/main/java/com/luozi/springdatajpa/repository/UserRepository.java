package com.luozi.springdatajpa.repository;

import com.luozi.springdatajpa.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by luoziyihao on 8/7/16.
 */
public interface UserRepository extends JpaRepository<User, Long> {
}
