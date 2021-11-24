package com.github.krystalics.scheduler.core.trigger;

import java.util.Date;

/**
 * @author linjiabao001
 * @date 2021/11/24
 * @description
 */
public abstract class Trigger {
    protected String jobName;
    protected String jobGroup;
    protected String triggerName;
    protected String triggerGroup;
    protected Date startTime;
    protected Date endTime;
    protected Date nextFireTime;
    protected Date previousFireTime;
    protected boolean complete;

    public Trigger(TriggerDetail.Builder builder) {
        this.jobName = builder.jobName;
        this.jobGroup = builder.jobGroup;
        this.triggerName = builder.triggerName;
        this.triggerGroup = builder.triggerGroup;
        this.startTime = builder.startTime;
        this.endTime = builder.endTime;
    }

    public abstract Date getNextFireTime();

    public abstract void updateNextFireTime(Date nextFireTime);

    public String getJobName() {
        return jobName;
    }

    public String getJobGroup() {
        return jobGroup;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof SimpleTrigger)) {
            return false;
        }

        SimpleTrigger that = (SimpleTrigger) o;

        if (!jobName.equals(that.jobName)) {
            return false;
        }
        if (!jobGroup.equals(that.jobGroup)) {
            return false;
        }
        if (!triggerName.equals(that.triggerName)) {
            return false;
        }
        return triggerGroup.equals(that.triggerGroup);
    }

    @Override
    public int hashCode() {
        int result = jobName.hashCode();
        result = 31 * result + jobGroup.hashCode();
        result = 31 * result + triggerGroup.hashCode();
        result = 31 * result + triggerName.hashCode();
        return result;
    }
}
