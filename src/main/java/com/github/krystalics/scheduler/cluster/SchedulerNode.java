package com.github.krystalics.scheduler.cluster;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

/**
 * @author linjiabao001
 * @date 2021/11/20
 * @description 集群管理中 节点信息
 */
@AllArgsConstructor
@Data
public class SchedulerNode {
    String ip;
    int port;
}
