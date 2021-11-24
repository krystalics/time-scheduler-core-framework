package com.github.krystalics.scheduler.core.trigger;

import lombok.Data;

import java.util.Date;

/**
 * @author linjiabao001
 * @date 2021/11/24
 * @description
 */
@Data
public abstract class AbstractTrigger implements Trigger {
    protected String jobName;
    protected String jobGroup;
    protected String triggerName;
    protected String triggerGroup;
    protected Date startTime;
    protected Date endTime;
    protected Date nextFireTime;
    protected Date previousFireTime;
    protected boolean complete;

    public AbstractTrigger(TriggerDetail.Builder builder) {
        this.jobName = builder.jobName;
        this.jobGroup = builder.jobGroup;
        this.triggerName = builder.triggerName;
        this.triggerGroup = builder.triggerGroup;
        this.startTime = builder.startTime;
        this.endTime = builder.endTime;
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

        if (!getJobName().equals(that.getJobName())) {
            return false;
        }
        if (!getJobGroup().equals(that.getJobGroup())) {
            return false;
        }
        if (!getTriggerName().equals(that.getTriggerName())) {
            return false;
        }
        return getTriggerGroup().equals(that.getTriggerGroup());
    }

    @Override
    public int hashCode() {
        int result = getJobName().hashCode();
        result = 31 * result + getJobGroup().hashCode();
        result = 31 * result + getTriggerName().hashCode();
        result = 31 * result + getTriggerGroup().hashCode();
        return result;
    }
}
