/*
 * MultiUserExecutor.java
 * Copyright 2024 Qunhe Tech, all rights reserved.
 * Qunhe PROPRIETARY/CONFIDENTIAL, any form of usage is subject to approval.
 */

package com.demo.creditlimit.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@Configuration
public class ExecutorConfig {

    /**
     * 模拟多用户发起操作的线程池
     */
    @Bean(name = "multiUserExecutor")
    public ThreadPoolTaskExecutor multiUserExecutor() {
        ThreadPoolTaskExecutor multiUserExecutor = new ThreadPoolTaskExecutor();
        multiUserExecutor.setCorePoolSize(50);
        multiUserExecutor.setMaxPoolSize(100);
        multiUserExecutor.setQueueCapacity(1000);
        multiUserExecutor.setThreadNamePrefix("multi-user-");
        multiUserExecutor.setWaitForTasksToCompleteOnShutdown(true);
        multiUserExecutor.initialize();
        return multiUserExecutor;

    }

}
