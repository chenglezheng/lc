package com.quartz.lcquartz.config;

import org.quartz.SchedulerFactory;
import org.quartz.spi.JobFactory;
import org.quartz.spi.TriggerFiredBundle;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowire;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.scheduling.quartz.SpringBeanJobFactory;

import javax.sql.DataSource;

/**
 * @author shenjiang
 * @Description:
 * @Date: 2019/5/11 11:46
 */
@Configuration
@EnableScheduling
public class QuartzConfiguration {

    public static class AutowiringSpringBeanJobFactory extends SpringBeanJobFactory implements ApplicationContextAware{

        private transient AutowireCapableBeanFactory beanFactory;

        @Override
        public void setApplicationContext(final ApplicationContext applicationContext) throws BeansException {
            beanFactory = applicationContext.getAutowireCapableBeanFactory();
        }
        /**
         * 这里我们覆盖了super的createJobInstance方法，对其创建出来的类再进行autowire。
         */
        @Override
        protected Object createJobInstance(TriggerFiredBundle bundle) throws Exception {
             Object obj = super.createJobInstance(bundle);
             /*job交给IOC托管*/
            beanFactory.autowireBean(obj);
            return obj;

        }

        }

        @Bean
        public JobFactory jobFactory(ApplicationContext applicationContext){

            /**
             * 采用自定义任务工厂 整合spring实例来完成构建任务
             */
            AutowiringSpringBeanJobFactory jobFactory = new AutowiringSpringBeanJobFactory();
            jobFactory.setApplicationContext(applicationContext);
            return jobFactory;

        }

    /**
     * 配置任务调度器
     * 使用项目数据源作为quartz数据源
     * @param jobFactory
     * @param dataSource
     * @return
     */
        @Bean(destroyMethod = "destroy",autowire = Autowire.NO)
        public SchedulerFactoryBean schedulerFactoryBean(JobFactory jobFactory, DataSource dataSource){
            SchedulerFactoryBean schedulerFactoryBean = new SchedulerFactoryBean();
            schedulerFactoryBean.setJobFactory(jobFactory);
            //覆盖已存在的任务
            schedulerFactoryBean.setOverwriteExistingJobs(true);
            schedulerFactoryBean.setStartupDelay(2);
            //调度器自助运行
            schedulerFactoryBean.setAutoStartup(true);
            schedulerFactoryBean.setDataSource(dataSource);
            schedulerFactoryBean.setApplicationContextSchedulerContextKey("applicationContext");
            //设置读取配置文件位置
            schedulerFactoryBean.setConfigLocation(new ClassPathResource("/quartz.properties"));
            return schedulerFactoryBean;

        }


}
