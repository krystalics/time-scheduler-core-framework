package com.github.krystalics.scheduler.core.job;

import com.github.krystalics.scheduler.core.trigger.Trigger;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author linjiabao001
 * @date 2021/11/22
 * @description
 */
@Data
@AllArgsConstructor
public class JobContext {
    private JobDetail jobDetail;
    private Trigger trigger;

}
