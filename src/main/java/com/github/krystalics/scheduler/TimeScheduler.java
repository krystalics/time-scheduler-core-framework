package com.github.krystalics.scheduler;


import com.github.krystalics.scheduler.core.Job;
import com.github.krystalics.scheduler.core.trigger.Trigger;
import com.github.krystalics.scheduler.lock.DistributedLock;
import com.github.krystalics.scheduler.lock.DistributedLockFactory;
import com.github.krystalics.scheduler.storage.Storage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @author linjiabao001
 * @date 2021/11/20
 * @description
 */
public class TimeScheduler {

    private static final Logger logger = LoggerFactory.getLogger(TimeScheduler.class);

    protected Storage storage;

    TimeSchedulerThread scheduler;

    public void setStorageType(Storage storage) {
        this.storage = storage;
    }

    public void start(Job job, Trigger trigger) {
        storeJobAndTrigger(job, trigger);

        scheduler = new TimeSchedulerThread();
        scheduler.start();
    }

    private void storeJobAndTrigger(Job job, Trigger trigger) {
        storage.storeJob(job);
        storage.storeTrigger(trigger);
    }


    /**
     * keypoint
     * - 1。尝试获得锁
     * - 2。获得锁后，开始从db获取可以执行的trigger信息，开始执行
     */
    class TimeSchedulerThread extends Thread {

        AtomicBoolean halted = new AtomicBoolean(false);

        private final Object sigLock = new Object();

        private final Random random = new Random(System.currentTimeMillis());

        private long idleWaitTime = 30L * 1000L;

        private int idleWaitVariablness = 7 * 1000;


        @Override
        public void run() {
            logger.info("scheduler start");

            while (!halted.get()) {
                final DistributedLock distributedLock = DistributedLockFactory.findDistributedLock().get();
                distributedLock.lock();
                logger.info("scheduler get lock");
                final List<Job> jobs = storage.getJobs();
                final List<Trigger> triggers = storage.getTriggers();

                logger.info("jobs: " + jobs);
                logger.info("triggers: " + triggers);
                //todo

                long now = System.currentTimeMillis();
                long waitTime = now + getRandomizedIdleWaitTime();
                long timeUntilContinue = waitTime - now;
                synchronized (sigLock) {
                    try {
                        if (!halted.get()) {
                            sigLock.wait(timeUntilContinue);
                        }
                    } catch (InterruptedException ignore) {
                    }
                }

            }

        }

        public void setIdleWaitTime(long idleWaitTime) {
            this.idleWaitTime = idleWaitTime;
            idleWaitVariablness = (int) (idleWaitTime * 0.2);
        }

        private long getRandomizedIdleWaitTime() {
            return idleWaitTime - random.nextInt(idleWaitVariablness);
        }

        public void shutdown() {
            halted.set(true);
        }
    }


    public void shutdown() {
        scheduler.shutdown();
    }
}