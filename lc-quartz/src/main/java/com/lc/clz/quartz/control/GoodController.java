package com.lc.clz.quartz.control;

import com.lc.clz.entities.user.BaseGoodInfo;
import com.lc.clz.quartz.service.GoodInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author shenjiang
 * @Description:
 * @Date: 2019/5/11 13:07
 */
@RestController
@RequestMapping(value = "/good")
public class GoodController {

    @Autowired
    private GoodInfoService goodInfoService;


    @RequestMapping(value = "/save")
    private Long saveBaseGoodInfo(BaseGoodInfo baseGoodInfo)throws Exception{
        return goodInfoService.save(baseGoodInfo);
    }

}
