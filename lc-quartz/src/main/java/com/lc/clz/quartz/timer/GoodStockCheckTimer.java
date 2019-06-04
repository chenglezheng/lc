package com.lc.clz.quartz.timer;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.quartz.QuartzJobBean;

import java.util.Date;

/**
 * @author shenjiang
 * @Description:
 * @Date: 2019/5/23 23:16
 */
public class GoodStockCheckTimer extends QuartzJobBean {

    @Value("${server.port}")
    private String port;

    static Logger log = LoggerFactory.getLogger(GoodStockCheckTimer.class);

    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        log.info(port+"定义库存检查任务，执行时间:{}",new Date());
    }
}
