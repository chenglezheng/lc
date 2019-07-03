package com.lc.clz.quartz.dao;

import com.lc.clz.entities.user.BaseGoodInfo;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author shenjiang
 * @Description:
 * @Date: 2019/5/11 13:12
 */
@Mapper
public interface BaseGoodInfoDao {

    public int saveBaseGoodInfo(BaseGoodInfo baseGoodInfo);

}
