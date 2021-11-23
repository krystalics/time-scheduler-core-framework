package com.github.krystalics.scheduler;

import com.github.krystalics.scheduler.core.job.Job;
import com.github.krystalics.scheduler.core.job.JobContext;
import com.github.krystalics.scheduler.core.job.JobDetail;
import com.github.krystalics.scheduler.core.trigger.SimpleTrigger;
import com.github.krystalics.scheduler.lock.JDBCLock;
import com.github.krystalics.scheduler.storage.RamJobStorage;


import java.util.Date;

/**
 * @author linjiabao001
 * @date 2021/11/20
 * @description
 */
public class TimeSchedulerTest {
    static class MyJob implements Job {
        @Override
        public void execute(JobContext context) {
            System.out.println(context.getTrigger().getNextFireTime());
        }
    }

    /**
     * 额外的线程在test中不起作用、于是写在了main里
     */
    public static void main(String[] args) {
        JobDetail jobDetail = new JobDetail();
        jobDetail.setJobClass(MyJob.class);
        SimpleTrigger trigger = new SimpleTrigger();
        trigger.setStartTime(new Date());
        //设置3min的重复频率
        trigger.setRepeatInterval(1000 * 60 * 3);

        final TimeScheduler timeScheduler = new TimeScheduler();
        timeScheduler.setStorageType(new RamJobStorage());
        timeScheduler.setLock(new JDBCLock());
        timeScheduler.start(jobDetail, trigger);
    }
}
