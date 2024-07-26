

package com.demo.creditlimit.scheduler;

import com.demo.creditlimit.service.CreditLimitService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class CommonScheduler {

    private final CreditLimitService creditLimitService;
    private final ThreadPoolTaskExecutor multiUserExecutor;

    public CommonScheduler(
            final CreditLimitService creditLimitService,
            @Qualifier("multiUserExecutor") final ThreadPoolTaskExecutor multiUserExecutor) {
        this.creditLimitService = creditLimitService;
        this.multiUserExecutor = multiUserExecutor;
    }

    /**
     * 每天 1 点定时对额度操作
     */
    @Scheduled(cron = "0 0 1 * * ?")
    public void limitOperateTask() {
        final Map<Long, Integer> operateMap = getOperateMap();
        operateMap.forEach((userId, type) -> {
            operate(userId, type);
        });
    }

    /**
     * 预留方法，获取需要操作的用户和额度类型
     */
    public Map<Long, Integer> getOperateMap() {
        return new HashMap<>();
    }

    /**
     * 操作的预留方法
     */
    public void operate(Long userId, Integer type) {

    }


    /**
     * 每天 2 点定时模拟多用户操作
     */
    @Scheduled(cron = "0 0 2 * * ?")
    public void multiUserSimulatedTask () {
        final Map<Long, Integer> operateMap = getOperateMap();
        operateMap.forEach((userId, type) -> {
            multiUserExecutor.submit(() -> operate(userId, type));
        });
    }
}
