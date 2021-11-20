package com.github.krystalics.scheduler.core.executors;

import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author linjiabao001
 * @date 2021/11/20
 * @description
 */
public class TimeSchedulerExecutors {
    private static ThreadPoolExecutor EXECUTORS;

    static {
        final int core = getCpuProcessors() + 1;
        int max = core * 2;

        EXECUTORS = new ThreadPoolExecutor(core, max, 30, TimeUnit.SECONDS, new LinkedBlockingDeque<>(), new ThreadFactory() {
            @Override
            public Thread newThread(Runnable r) {
                return new Thread(r,"POLLING-WORKER-");
            }
        });
    }

    public static int getCpuProcessors() {
        return Runtime.getRuntime() != null && Runtime.getRuntime().availableProcessors() > 0 ?
                Runtime.getRuntime().availableProcessors() : 8;
    }

    public static Future<?> submit(Runnable runnable) {
        return EXECUTORS.submit(runnable);
    }

    public static void shutdown() {
        EXECUTORS.shutdown();
    }

    public static void shutdownNow() {
        EXECUTORS.shutdownNow();
    }

}
