package com.github.krystalics.scheduler.core.trigger;

import lombok.Data;

import java.util.Date;
import java.util.Map;

/**
 * @author linjiabao001
 * @date 2021/11/20
 * @description
 */
@Data
public class SimpleTrigger implements Trigger {
    private String scheduleName;
    private String jobName;
    private String jobGroup;
    private String triggerName;
    private String triggerGroup;
    private Date startTime;
    private Date endTime;
    private Date nextFireTime;
    private Date previousFireTime;
    private int repeatCount;
    private long repeatInterval;
    private int timesTriggered;
    private boolean complete;
    private Map<String, Object> jobData;


    @Override
    public Date getNextFireTime() {
        if (nextFireTime == null) {
            nextFireTime = startTime;
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
}
