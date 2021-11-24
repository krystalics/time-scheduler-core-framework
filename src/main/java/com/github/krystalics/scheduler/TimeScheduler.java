package com.github.krystalics.scheduler;

import com.github.krystalics.scheduler.core.executors.TimeSchedulerExecutors;
import com.github.krystalics.scheduler.core.job.Job;
import com.github.krystalics.scheduler.core.job.JobContext;
import com.github.krystalics.scheduler.core.job.JobDetail;
import com.github.krystalics.scheduler.core.trigger.Trigger;
import com.github.krystalics.scheduler.lock.DistributedLock;
import com.github.krystalics.scheduler.storage.Storage;
import lombok.SneakyThrows;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;
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
    protected DistributedLock lock;

    public TimeScheduler(Storage storage, DistributedLock lock) {
        this.storage = storage;
        this.lock = lock;
    }

    TimeSchedulerThread scheduler;

    public void start() {
        scheduler = new TimeSchedulerThread();
        scheduler.start();
    }

    public void storeJobAndTrigger(JobDetail job, Trigger trigger) {
        storage.storeJob(job);
        storage.storeTrigger(trigger);
    }


    public void storeJobs(JobDetail... jobs) {
        for (JobDetail job : jobs) {
            storage.storeJob(job);
        }
    }

    public void storeTriggers(Trigger... triggers) {
        for (Trigger trigger : triggers) {
            storage.storeTrigger(trigger);
        }
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


        @SneakyThrows
        @Override
        public void run() {
            logger.info("time scheduler start!!");

            while (!halted.get()) {
                lock.lock();
                logger.info("scheduler get lock");

                Date pollTime = new Date();
                final List<JobContext> jobContexts = storage.getFiredJobContexts(pollTime);

                for (JobContext jobContext : jobContexts) {
                    if (!halted.get()) {
                        final Class<?> jobClass = jobContext.getJobDetail().getJobClass();
                        //todo 将外界传入的job field参数通过反射传入
                        final Job job = (Job) jobClass.newInstance();

                        TimeSchedulerExecutors.submit(() -> job.execute(jobContext));

                        //执行完成后，更新trigger的nextFiredTime
                        final Trigger trigger = jobContext.getTrigger();
                        trigger.updateNextFireTime(trigger.getNextFireTime());
                        storage.updateTrigger(jobContext.getJobDetail(), trigger);
                    }
                }


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

    public void setLock(DistributedLock lock) {
        this.lock = lock;
    }
}