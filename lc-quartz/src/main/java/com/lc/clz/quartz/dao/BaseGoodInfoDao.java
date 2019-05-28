package com.lc.clz.quartz.dao;

import com.fasterxml.jackson.databind.ser.Serializers;
import com.lc.clz.entities.BaseGoodInfo;
import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.annotation.MapperScan;

/**
 * @author shenjiang
 * @Description:
 * @Date: 2019/5/11 13:12
 */
@Mapper
public interface BaseGoodInfoDao {

    public int saveBaseGoodInfo(BaseGoodInfo baseGoodInfo);

}
