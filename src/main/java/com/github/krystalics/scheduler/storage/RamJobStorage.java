package com.github.krystalics.scheduler.storage;

import com.github.krystalics.scheduler.core.Job;
import com.github.krystalics.scheduler.core.trigger.Trigger;

import java.util.ArrayList;
import java.util.List;

/**
 * @author linjiabao001
 * @date 2021/11/20
 * @description 最简单的模型
 */
public class RamJobStorage implements Storage {

    private static final List<Job> JOBS = new ArrayList<>();
    private static final List<Trigger> TRIGGERS = new ArrayList<>();

    @Override
    public boolean storeJob(Job job) {
        return JOBS.add(job);
    }

    @Override
    public boolean storeTrigger(Trigger trigger) {
        return TRIGGERS.add(trigger);
    }

    @Override
    public List<Job> getJobs() {
        return JOBS;
    }

    @Override
    public List<Trigger> getTriggers() {
        return TRIGGERS;
    }
}
