package com.github.rholder.retry.Strategy.factory;

import com.github.rholder.retry.Strategy.BlockStrategy;

/**
 * Function:   {@link BlockStrategy}实例的工厂类
 *
 * @author liujianlong[liujl24@yusys.com.cn]
 * @description:
 * @date 2022/4/18 11:46:02
 */
public final class BlockStrategies {

    private BlockStrategies() {
    }

    private static final BlockStrategy THREAD_SLEEP_STRATEGY = new ThreadSleepStrategy();

    public static BlockStrategy threadSleepStrategy() {
        return THREAD_SLEEP_STRATEGY;
    }

    private static class ThreadSleepStrategy implements BlockStrategy{
        public void block(long sleepTime) throws InterruptedException {
            Thread.sleep(sleepTime);
        }
    }
}
