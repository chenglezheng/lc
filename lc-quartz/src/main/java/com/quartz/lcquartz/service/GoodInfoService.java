package com.quartz.lcquartz.service;

import com.lc.clz.entities.BaseGoodInfo;

/**
 * @author shenjiang
 * @Description:
 * @Date: 2019/5/11 13:09
 */
public interface GoodInfoService {

    public Long save(BaseGoodInfo baseGoodInfo);

    public void buildGoodStockTimer();

}
