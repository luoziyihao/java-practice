package com.luozi.springdatajpa.domain;


import com.luozi.entity.BaseEntity;

import javax.persistence.Entity;

/**
 * Created by luoziyihao on 8/7/16.
 */
@Entity
public class User extends BaseEntity<Long> {
    private String name;
    private Integer age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

}
