package com.lc.clz.quartz.timer;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.quartz.QuartzJobBean;

import java.util.Date;

/**
 * 定义商品添加任务
 * @author shenjiang
 * @Description:
 * @Date: 2019/5/23 23:12
 */
public class GoodAddTimer extends QuartzJobBean {

    @Value("${server.port}")
    private String port;

    static Logger log = LoggerFactory.getLogger(GoodAddTimer.class);

    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        log.info(port+"商品添加完成后，任务时间:{}",new Date());
    }
}
