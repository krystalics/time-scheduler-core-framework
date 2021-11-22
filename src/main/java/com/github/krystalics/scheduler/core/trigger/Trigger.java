package com.github.krystalics.scheduler.core.trigger;

import java.util.Date;

/**
 * @author linjiabao001
 * @date 2021/11/20
 * @description
 */
public interface Trigger {
    Date getNextFireTime();

    void updateNextFireTime(Date nextFireTime);

}
