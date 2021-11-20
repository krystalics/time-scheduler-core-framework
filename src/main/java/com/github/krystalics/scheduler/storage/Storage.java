package com.github.krystalics.scheduler.storage;

import com.github.krystalics.scheduler.core.Job;
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
     * @param job
     * @return
     */
    boolean storeJob(Job job);

    /**
     * 存储Trigger
     * @param trigger
     * @return
     */
    boolean storeTrigger(Trigger trigger);


    List<Job> getJobs();

    List<Trigger> getTriggers();
}
