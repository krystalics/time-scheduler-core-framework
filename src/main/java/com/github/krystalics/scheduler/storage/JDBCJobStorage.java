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
public class JDBCJobStorage implements Storage{
    @Override
    public boolean storeJob(JobDetail job) {
        return false;
    }

    @Override
    public boolean storeTrigger(Trigger trigger) {
        return false;
    }

    @Override
    public boolean storeJobAndTrigger(JobDetail job, Trigger trigger) {
        return false;
    }

    @Override
    public List<JobDetail> getJobs() {
        return null;
    }

    @Override
    public List<Trigger> getTriggers() {
        return null;
    }

    @Override
    public List<JobContext> getJobContexts() {
        return null;
    }

    @Override
    public boolean setJobContexts(List<JobContext> contexts) {
        return false;
    }
}
