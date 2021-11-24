package com.github.krystalics.scheduler;

import com.github.krystalics.scheduler.core.job.Job;
import com.github.krystalics.scheduler.core.job.JobContext;
import com.github.krystalics.scheduler.core.job.JobDetail;
import com.github.krystalics.scheduler.core.trigger.SimpleTrigger;
import com.github.krystalics.scheduler.core.trigger.TriggerDetail;
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
        String jobGroup = "j-group1";
        String jobName = "job1";
        String triggerGroup = "t-group1";
        String triggerName = "trigger1";

        JobDetail jobDetail = new JobDetail.Builder(jobGroup, jobName)
                .concurrent(true)
                .jobClass(MyJob.class)
                .build();

        final SimpleTrigger trigger = new TriggerDetail.Builder(jobGroup, jobName, triggerGroup, triggerName)
                .repeatInterval(1000 * 60)
                .startTime(new Date())
                .simpleBuild();


        final TimeScheduler timeScheduler = new TimeScheduler(new RamJobStorage(), new JDBCLock());

        timeScheduler.start(jobDetail, trigger);
    }
}
