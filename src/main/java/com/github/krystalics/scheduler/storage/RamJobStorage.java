package com.github.krystalics.scheduler.storage;

import com.github.krystalics.scheduler.core.job.JobContext;
import com.github.krystalics.scheduler.core.job.JobDetail;
import com.github.krystalics.scheduler.core.trigger.Trigger;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author linjiabao001
 * @date 2021/11/20
 * @description 最简单的模型
 */
public class RamJobStorage implements Storage {

    private static final Map<JobDetail, Set<Trigger>> STORE = new ConcurrentHashMap<>();

    @Override
    public void storeJob(JobDetail job) {
        STORE.put(job, new HashSet<>());
    }

    @Override
    public void storeTrigger(Trigger trigger) {
        JobDetail job = new JobDetail.Builder(trigger.getJobGroup(), trigger.getJobName()).build();
        STORE.computeIfPresent(job, (k, v) -> {
            v.add(trigger);
            return v;
        });
    }

    @Override
    public void storeJobAndTrigger(JobDetail job, Trigger trigger) {
        final Set<Trigger> set = STORE.getOrDefault(job, new HashSet<>());
        set.add(trigger);
        STORE.put(job, set);
    }

    @Override
    public void updateTrigger(JobDetail job, Trigger trigger) {
        final Set<Trigger> set = STORE.getOrDefault(job, new HashSet<>());
        set.remove(trigger);
        set.add(trigger);
        STORE.put(job, set);
    }

    @Override
    public List<JobContext> getFiredJobContexts(Date now) {
        List<JobContext> result = new ArrayList<>();

        STORE.forEach((k, v) -> {
            for (Trigger trigger : v) {
                final Date nextFireTime = trigger.getNextFireTime();
                if (nextFireTime == null || now.after(nextFireTime)) {
                    result.add(new JobContext(k, trigger));
                }
            }
        });

        return result;
    }


}
