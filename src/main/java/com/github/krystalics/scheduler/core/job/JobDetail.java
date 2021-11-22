package com.github.krystalics.scheduler.core.job;

import lombok.Data;

import java.util.Map;

/**
 * @author linjiabao001
 * @date 2021/11/22
 * @description
 */
@Data
public class JobDetail {
    private String scheduleName;
    private String jobName;
    private String jobGroup;
    private String description;
    private Class<?> jobClass;
    private Boolean isDurable;
    private Boolean isNonConcurrent;
    private Boolean isUpdateData;
    private Boolean requestsRecovery;
    private Map<String, Object> jobData;
}
