package com.luozi.common.controller;

import com.luozi.common.entity.AbstractEntity;
import com.luozi.common.service.AbstractEntityService;
import com.luozi.common.vo.Response;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import java.io.Serializable;

/**
 * Created by luoziyihao on 10/12/16.
 */

public abstract class AbstractEntityController <M extends AbstractEntity, ID extends Serializable>{

    @Inject
    private AbstractEntityService<M, ID> abstractEntityService;

    public AbstractEntityService<M, ID> getAbstractEntityService() {
        return abstractEntityService;
    }


    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Response findOne(@PathVariable(value = "id") ID id){
        return new Response().success(getAbstractEntityService().findOne(id));
    }

    /* 请求不采用 requestBody, 方便调试 */
    @RequestMapping(method = RequestMethod.POST)
    public Response save(M m){
        return new Response().success(getAbstractEntityService().save(m));
    }
}
