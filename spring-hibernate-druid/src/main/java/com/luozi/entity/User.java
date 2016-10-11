package com.luozi.entity;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;

/**
 * Created by luoziyihao on 8/7/16.
 */
@Getter
@Setter
@Entity
public class User extends BaseEntity<Long>{
    private String name;
    private Integer age;
}
