package com.github.krystalics.scheduler.core.trigger;

import java.util.Date;

/**
 * @author linjiabao001
 * @date 2021/11/24
 * @description
 */
public class TriggerDetail {

    /**
     * trigger基础配置
     */
    public String jobName;
    public String jobGroup;
    public String triggerName;
    public String triggerGroup;
    public Date startTime;
    public Date endTime;

    /**
     * simpleTrigger额外配置
     */
    public int repeatCount;
    public long repeatInterval;

    private TriggerDetail(Builder builder) {
        jobGroup = builder.jobGroup;
        jobName = builder.jobName;
        triggerGroup = builder.triggerGroup;
        triggerName = builder.triggerName;
        startTime = builder.startTime;
        endTime = builder.endTime;

        repeatCount = builder.repeatCount;
        repeatInterval = builder.repeatInterval;
    }


    public static class Builder {
        public String jobName;
        public String jobGroup;
        public String triggerName;
        public String triggerGroup;
        public Date startTime;
        public Date endTime;
        public int repeatCount;
        public long repeatInterval;

        public Builder(String jobName, String jobGroup, String triggerName, String triggerGroup) {
            this.jobName = jobName;
            this.jobGroup = jobGroup;
            this.triggerName = triggerName;
            this.triggerGroup = triggerGroup;
        }

        public Builder startTime(Date startTime) {
            this.startTime = startTime;
            return this;
        }

        public Builder endTime(Date endTime) {
            this.endTime = endTime;
            return this;
        }

        public Builder repeatCount(int repeatCount) {
            this.repeatCount = repeatCount;
            return this;
        }

        public Builder repeatInterval(int repeatInterval) {
            this.repeatInterval = repeatInterval;
            return this;
        }


        public TriggerDetail build() {
            return new TriggerDetail(this);
        }

        public SimpleTrigger simpleBuild() {
            return new SimpleTrigger(this);
        }
    }
}
