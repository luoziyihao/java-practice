package com.luozi.shd.controller;

import com.luozi.common.controller.AbstractEntityController;
import com.luozi.shd.entity.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by luoziyihao on 10/12/16.
 */
@RestController
@RequestMapping(value = "/user")
public class UserController extends AbstractEntityController<User, Long> {
}
