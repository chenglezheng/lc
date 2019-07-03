package com.lc.clz.quartz.service;

import com.lc.clz.entities.user.BaseGoodInfo;

/**
 * @author shenjiang
 * @Description:
 * @Date: 2019/5/11 13:09
 */
public interface GoodInfoService {

    public Long save(BaseGoodInfo baseGoodInfo) throws Exception;

    public void buildGoodStockTimer();

}
