package com.github.krystalics.scheduler.cluster.registry;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.ServiceLoader;

/**
 * @author linjiabao001
 * @date 2021/11/20
 * @description 根据传入的类型，选择注册中心
 */
public class RegistryFactory {

    private static final List<Registry> REGISTRIES = new LinkedList<>();


    static {
        ServiceLoader.load(Registry.class).forEach(REGISTRIES::add);
    }

    public static Optional<Registry> findRegistry(){
        return REGISTRIES.stream()
                .findFirst();
    }
}
