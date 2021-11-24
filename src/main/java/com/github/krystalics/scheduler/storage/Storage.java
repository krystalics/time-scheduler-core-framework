package com.github.krystalics.scheduler.storage;

import com.github.krystalics.scheduler.core.job.JobContext;
import com.github.krystalics.scheduler.core.job.JobDetail;
import com.github.krystalics.scheduler.core.trigger.Trigger;

import java.util.Date;
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
     */
    void storeJob(JobDetail job);

    /**
     * 存储Trigger
     *
     * @param trigger
     */
    void storeTrigger(Trigger trigger);

    void storeJobAndTrigger(JobDetail job, Trigger trigger);


    void updateTrigger(JobDetail job, Trigger trigger);

    List<JobContext> getFiredJobContexts(Date now);
}
