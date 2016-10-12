package com.luozi.controller;

import com.luozi.entity.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by luoziyihao on 10/12/16.
 */
@Controller
@RequestMapping(value = "/user")
public class UserController extends AbstractEntityController<User, Long>{
}
