package com.luozi.controller;

import com.luozi.entity.User;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by luoziyihao on 10/12/16.
 */
@RestController(value = "/user")
public class UserController extends AbstractEntityController<User, Long>{
}
