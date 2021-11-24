package com.github.krystalics.scheduler.core.job;

import lombok.Data;

import java.util.Map;

/**
 * @author linjiabao001
 * @date 2021/11/22
 * @description 默认的jobDetail是可以并行运行的，即上一个时间范围内没有运行完成，下一个时间到了 也可以正常触发
 */
@Data
public class JobDetail {

    private String jobGroup;
    private String jobName;
    private String description;
    private Class<?> jobClass;
    private Boolean isNonConcurrent;
    private Map<String, Object> jobData;

    private JobDetail(Builder builder) {
        jobGroup = builder.jobGroup;
        jobName = builder.jobName;
        description = builder.description;
        jobClass = builder.jobClass;
        isNonConcurrent = builder.isNonConcurrent;
        jobData = builder.jobData;
    }

   public static class Builder {
        public Builder(String jobName, String jobGroup) {
            this.jobName = jobName;
            this.jobGroup = jobGroup;
        }

        private String jobName;
        private String jobGroup;
        private String description;
        private Class<?> jobClass;
        private Boolean isNonConcurrent;
        private Map<String, Object> jobData;

        public Builder description(String desc) {
            description = desc;
            return this;
        }

        public Builder jobClass(Class<?> clazz) {
            jobClass = clazz;
            return this;
        }

        public Builder concurrent(boolean concurrent) {
            isNonConcurrent = concurrent;
            return this;
        }

        public Builder jobDataMap(Map<String, Object> jobDataMap) {
            jobData = jobDataMap;
            return this;
        }

        public JobDetail build() {
            return new JobDetail(this);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof JobDetail)) {
            return false;
        }

        JobDetail jobDetail = (JobDetail) o;

        if (!getJobName().equals(jobDetail.getJobName())) {
            return false;
        }
        return getJobGroup().equals(jobDetail.getJobGroup());
    }

    @Override
    public int hashCode() {
        int result = getJobName().hashCode();
        result = 31 * result + getJobGroup().hashCode();
        return result;
    }
}
