package com.github.krystalics.scheduler.core.trigger;

import java.util.Date;

/**
 * @author linjiabao001
 * @date 2021/11/20
 * @description
 */

public class SimpleTrigger extends Trigger {

    private int repeatCount;
    private long repeatInterval;

    SimpleTrigger(TriggerDetail.Builder builder) {
        super(builder);

        repeatCount = builder.repeatCount;
        repeatInterval = builder.repeatInterval;
    }

    @Override
    public Date getNextFireTime() {
        if (nextFireTime == null) {
            if (startTime == null) {

            } else {
                nextFireTime = startTime;
            }
        } else {
            return nextFireTime;
        }
        return null;
    }

    @Override
    public void updateNextFireTime(Date prev) {
        if (prev == null) {
            prev = startTime;
        }
        final long nextTime = prev.getTime() + repeatInterval;
        this.nextFireTime = new Date(nextTime);
    }


    @Override
    public String toString() {
        return "SimpleTrigger{" +
                "repeatCount=" + repeatCount +
                ", repeatInterval=" + repeatInterval +
                ", jobName='" + jobName + '\'' +
                ", jobGroup='" + jobGroup + '\'' +
                ", triggerName='" + triggerName + '\'' +
                ", triggerGroup='" + triggerGroup + '\'' +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", nextFireTime=" + nextFireTime +
                ", previousFireTime=" + previousFireTime +
                ", complete=" + complete +
                '}';
    }
}
