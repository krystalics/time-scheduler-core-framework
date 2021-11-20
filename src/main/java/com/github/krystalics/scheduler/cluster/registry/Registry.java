package com.github.krystalics.scheduler.cluster.registry;

import com.github.krystalics.scheduler.cluster.SchedulerNode;

/**
 * @author linjiabao001
 * @date 2021/11/20
 * @description 节点注册，作为集群管理的基础
 */
public interface Registry {
    /**
     * 节点注册
     * @param schedulerNode
     */
    void register(SchedulerNode schedulerNode);
}
