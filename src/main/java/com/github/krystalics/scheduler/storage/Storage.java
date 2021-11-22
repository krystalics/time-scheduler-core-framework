package com.github.krystalics.scheduler.storage;

import com.github.krystalics.scheduler.core.job.Job;
import com.github.krystalics.scheduler.core.job.JobContext;
import com.github.krystalics.scheduler.core.job.JobDetail;
import com.github.krystalics.scheduler.core.trigger.Trigger;

import java.util.List;

/**
 * @author linjiabao001
 * @date 2021/11/20
 * @description
 */
public interface Storage {
    /**
     * 存储job
     *
     * @param job
     * @return
     */
    boolean storeJob(JobDetail job);

    /**
     * 存储Trigger
     *
     * @param trigger
     * @return
     */
    boolean storeTrigger(Trigger trigger);

    boolean storeJobAndTrigger(JobDetail job, Trigger trigger);


    List<JobDetail> getJobs();

    List<Trigger> getTriggers();

    List<JobContext> getJobContexts();

    boolean setJobContexts(List<JobContext> contexts);

}
