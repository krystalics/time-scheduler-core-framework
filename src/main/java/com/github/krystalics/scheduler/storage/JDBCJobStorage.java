package com.github.krystalics.scheduler.storage;

import com.github.krystalics.scheduler.core.Job;
import com.github.krystalics.scheduler.core.trigger.Trigger;

import java.util.List;

/**
 * @author linjiabao001
 * @date 2021/11/20
 * @description
 */
public class JDBCJobStorage implements Storage{
    @Override
    public boolean storeJob(Job job) {
        return false;
    }

    @Override
    public boolean storeTrigger(Trigger trigger) {
        return false;
    }

    @Override
    public List<Job> getJobs() {
        return null;
    }

    @Override
    public List<Trigger> getTriggers() {
        return null;
    }
}
