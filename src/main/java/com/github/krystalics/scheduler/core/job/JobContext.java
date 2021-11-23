package com.github.krystalics.scheduler.core.job;

import com.github.krystalics.scheduler.core.trigger.Trigger;
import lombok.Data;

import java.util.Objects;

/**
 * @author linjiabao001
 * @date 2021/11/22
 * @description
 */
@Data
public class JobContext {
    private JobDetail jobDetail;
    private Trigger trigger;

}