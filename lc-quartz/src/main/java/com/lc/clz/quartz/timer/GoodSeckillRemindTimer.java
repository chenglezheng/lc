package com.lc.clz.quartz.timer;

import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.quartz.QuartzJobBean;

/**
 * @author shenjiang
 * @Description:
 * @Date: 2019/5/28 16:14
 */
public class GoodSeckillRemindTimer extends QuartzJobBean {

    private Logger logger = LoggerFactory.getLogger(GoodSeckillRemindTimer.class);

    @Value("${server.port}")
    private String port;


    /**
     * 秒杀 任务逻辑
     * @param jobExecutionContext
     * @throws JobExecutionException
     */
    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        //获取任务详情内的数据集合
        JobDataMap jobDataMap = jobExecutionContext.getJobDetail().getJobDataMap();
        //获取商品编号


        logger.info(port+"节点，开始处理秒杀商品:{},关注用户推送消息",12333);
    }
}
