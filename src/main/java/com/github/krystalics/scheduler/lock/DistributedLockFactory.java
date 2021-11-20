package com.github.krystalics.scheduler.lock;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ServiceLoader;

/**
 * @author linjiabao001
 * @date 2021/11/20
 * @description
 */
public class DistributedLockFactory {
    private final static List<DistributedLock> LOCKS = new ArrayList<>();

    static {
        ServiceLoader.load(DistributedLock.class).forEach(LOCKS::add);
    }

    public static Optional<DistributedLock> findDistributedLock() {
        return LOCKS.stream().findFirst();
    }
}
