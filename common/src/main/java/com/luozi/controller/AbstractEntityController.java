package com.luozi.controller;

import com.luozi.entity.AbstractEntity;
import com.luozi.service.AbstractEntityService;
import com.luozi.vo.Response;
import org.jsoup.Connection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;
import java.io.Serializable;

/**
 * Created by luoziyihao on 10/12/16.
 */

public class AbstractEntityController <M extends AbstractEntity, ID extends Serializable>{

    @Autowired
    private AbstractEntityService<M, ID> abstractEntityService;

    public AbstractEntityService<M, ID> getAbstractEntityService() {
        return abstractEntityService;
    }

    @RequestMapping(value = "/${id}", method = RequestMethod.GET)
    public Response findOne(@PathVariable(value = "id") ID id){
        return new Response().success(getAbstractEntityService().findOne(id));
    }

    @RequestMapping(method = RequestMethod.POST)
    public Response save(@RequestBody M m){
        return new Response().success(getAbstractEntityService().save(m));
    }
}
