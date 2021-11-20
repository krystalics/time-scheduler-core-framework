package com.github.krystalics.scheduler.lock;

/**
 * @author linjiabao001
 * @date 2021/11/20
 * @description 用作分布式锁的接口、这里面对的场景是不需要设置锁获取超时时间的，就一直阻塞获取即可
 */
public interface DistributedLock {
    /**
     * 尝试获得锁
     */
    void lock();

    /**
     * 尝试释放锁
     */
    void unlock();
}
