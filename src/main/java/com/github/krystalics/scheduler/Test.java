package com.github.krystalics.scheduler;

import com.github.krystalics.scheduler.core.Job;
import com.github.krystalics.scheduler.core.trigger.SimpleTrigger;
import com.github.krystalics.scheduler.storage.RamJobStorage;

/**
 * @author linjiabao001
 * @date 2021/11/20
 * @description
 */
public class Test {
    static class MyJob implements Job {
    }

    /**
     * spi机制在test中不起作用、于是写在了main里
     */
    public static void main(String[] args) {
        MyJob myJob = new MyJob();
        SimpleTrigger trigger = new SimpleTrigger();

        final TimeScheduler timeScheduler = new TimeScheduler();
        timeScheduler.setStorageType(new RamJobStorage());
        timeScheduler.start(myJob, trigger);
    }
}
