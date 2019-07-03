package com.lc.clz.quartz.serviceimpl;

import com.lc.clz.entities.user.BaseGoodInfo;
import com.lc.clz.quartz.dao.BaseGoodInfoDao;
import com.lc.clz.quartz.service.GoodInfoService;
import com.lc.clz.quartz.timer.GoodAddTimer;
import com.lc.clz.quartz.timer.GoodSeckillRemindTimer;
import com.lc.clz.quartz.timer.GoodStockCheckTimer;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.UUID;

/**
 * @author shenjiang
 * @Description:
 * @Date: 2019/5/11 13:11
 */
@Service("goodInfoService")
public class GoodInfoServiceImpl implements GoodInfoService {

    @Autowired
    private Scheduler scheduler;

    @Autowired
    private BaseGoodInfoDao baseGoodInfoDao;

    @Override
    public Long save(BaseGoodInfo baseGoodInfo) throws Exception {
        baseGoodInfoDao.saveBaseGoodInfo(baseGoodInfo);
        //构建创建商品定时任务
        buildCreateGoodTimer();
        //构建商品库存定时任务
        buildGoodStockTimer();
        //构建商品描述提醒定时任务
        buildGoodSeckillRemindTimer(Long.valueOf(baseGoodInfo.getBgiId()));
        return Long.valueOf(baseGoodInfo.getBgiId());

    }

    @Override
    public void buildGoodStockTimer() {
        //任务名
        String name = UUID.randomUUID().toString();
        //任务所属分组
        String group = GoodStockCheckTimer.class.getName();

        CronScheduleBuilder ScheduleBuilder = CronScheduleBuilder.cronSchedule("0/30 * * * * ?");//可定时循环
        //创建任务
        JobDetail jobDetail = JobBuilder.newJob(GoodStockCheckTimer.class).withIdentity(name,group).build();
        //创建任务触发器
        Trigger trigger = TriggerBuilder.newTrigger().withIdentity(name,group).withSchedule(ScheduleBuilder).build();

        //将触发器与任务绑定到调度器内
        try {
            scheduler.scheduleJob(jobDetail,trigger);
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }

    public void buildCreateGoodTimer(){
        //设置开始时间为一分钟后
        long startTime = System.currentTimeMillis()+1000 * 60;
        //任务名称
        String name = UUID.randomUUID().toString();
        //任务分组
        String groupName = GoodAddTimer.class.getName();
        //创建任务
        JobDetail jobDetail = JobBuilder.newJob(GoodAddTimer.class).withIdentity(name, groupName).build();
        //创建任务触发器
        Trigger trigger = TriggerBuilder.newTrigger().withIdentity(name, groupName).startAt(new Date(startTime)).build();

        try {
            scheduler.scheduleJob(jobDetail,trigger);
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }

    public void buildGoodSeckillRemindTimer(Long goodId) throws Exception{
        //任务名称
        String name = UUID.randomUUID().toString();
        //任务分组
        String group = GoodSeckillRemindTimer.class.getName();
        long startTime = System.currentTimeMillis()+ 1000*5*60;
        //创建任务
        JobDetail jobDetail = JobBuilder.newJob(GoodSeckillRemindTimer.class).withIdentity(name,group).build();
        //创建任务触发器
        Trigger trigger = TriggerBuilder.newTrigger().withIdentity(name, group).startAt(new Date(startTime)).build();

        //绑定任务调度器
        scheduler.scheduleJob(jobDetail,trigger);

    }


}
