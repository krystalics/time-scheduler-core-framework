package com.github.krystalics.scheduler.storage;

import com.github.krystalics.scheduler.core.job.JobContext;
import com.github.krystalics.scheduler.core.job.JobDetail;
import com.github.krystalics.scheduler.core.trigger.Trigger;

import java.util.ArrayList;
import java.util.List;

/**
 * @author linjiabao001
 * @date 2021/11/20
 * @description 最简单的模型
 */
public class RamJobStorage implements Storage {

    private static final List<JobDetail> JOBS = new ArrayList<>();
    private static final List<Trigger> TRIGGERS = new ArrayList<>();
    private static final List<JobContext> JOB_CONTEXTS = new ArrayList<>();

    @Override
    public boolean storeJob(JobDetail job) {
        return JOBS.add(job);
    }

    @Override
    public boolean storeTrigger(Trigger trigger) {
        return TRIGGERS.add(trigger);
    }

    @Override
    public boolean storeJobAndTrigger(JobDetail job, Trigger trigger) {
        JobContext context = new JobContext();
        context.setTrigger(trigger);
        context.setJobDetail(job);

        return JOB_CONTEXTS.add(context);
    }

    @Override
    public List<JobDetail> getJobs() {
        return JOBS;
    }

    @Override
    public List<Trigger> getTriggers() {
        return TRIGGERS;
    }

    @Override
    public List<JobContext> getJobContexts() {
        return JOB_CONTEXTS;
    }

    @Override
    public boolean setJobContexts(List<JobContext> contexts) {
        JOB_CONTEXTS.clear();
        return JOB_CONTEXTS.addAll(contexts);
    }
}
