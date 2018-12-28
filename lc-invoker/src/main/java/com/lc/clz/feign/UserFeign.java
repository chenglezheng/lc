package com.lc.clz.feign;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by chenglezheng on 2018/12/28.
 */

@FeignClient("lc-basic-service")
public interface UserFeign {

    @RequestMapping(value = "/all/{pageNum}/{pageSize}", produces = {"application/json;charset=UTF-8"})
    Object findAllUser(@PathVariable("pageNum") int pageNum, @PathVariable("pageSize") int pageSize);


}
