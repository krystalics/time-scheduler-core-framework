package com.github.krystalics.scheduler.core.job;

/**
 * @author linjiabao001
 * @date 2021/11/20
 * @description 描述job信息
 */
public interface Job {
    /**
     * 传入job的context语境执行
     * @param context job需要的配置语境
     */
    void execute(JobContext context);
}
