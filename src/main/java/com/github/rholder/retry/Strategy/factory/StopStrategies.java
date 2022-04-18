package com.github.rholder.retry.Strategy.factory;

import com.github.rholder.retry.Attempt;
import com.github.rholder.retry.Strategy.StopStrategy;
import com.google.common.base.Preconditions;
import javax.annotation.Nonnull;
import javax.annotation.concurrent.Immutable;
import java.util.concurrent.TimeUnit;

/**
 * Function: factory for  {@link StopStrategy}实例的工厂类
 *
 * @author liujianlong[liujl24@yusys.com.cn]
 * @description:
 * @date 2022/4/18 14:05:42
 * @study final 修饰类表类不可被继承，用于不可拓展的类上
 */
public final class StopStrategies {

    private static final StopStrategy NEVER_STOP = new NeverStopStrategy();;

    /**
     * @study 工厂方法私有构造，统一对象创建入口
     */
    private StopStrategies(){}

    /**
     * 返回重不停止策略
     * @return
     * @study 不可变的策略直接声明static返回
     */
    public static StopStrategy neverStop(){
        return NEVER_STOP;
    }

    /**
     * 返回一个失败N次后停止策略
     * @param attemptNumber
     * @return
     */
    public static StopStrategy stopAfterAttempt(int attemptNumber) {
        return new StopAfterAttemptStrategy(attemptNumber);
    }

    /**
     * 返回一个给定延迟后停止策略
     * @param delayInMillis
     * @return
     * @deprecated Use {@link #stopAfterDelay(long, TimeUnit)} instead.
     */
    @Deprecated
    public static StopStrategy stopAfterDelay(long delayInMillis){
        return stopAfterDelay(delayInMillis,TimeUnit.MILLISECONDS);
    }

    /**
     * 返回一个给定延迟后停止策略
     * @param duration
     * @param timeUnit
     * @return
     * @Study todo
     */
    public static StopStrategy stopAfterDelay(long duration, @Nonnull TimeUnit timeUnit) {
        Preconditions.checkNotNull(timeUnit, "The time unit may not be null");
        return new StopAfterDelayStrategy(timeUnit.toMillis(duration));
    }

    /**
     * 从不停止
     */
    @Immutable
    private static final class NeverStopStrategy implements StopStrategy{
        public boolean shouldStop(Attempt failedAttempt) {
            return false;
        }
    }

    /**
     * 尝试次数超过最大次数停止
     */
    @Immutable
    private static final class StopAfterAttemptStrategy implements StopStrategy{
        private final int maxAttemptNumber;

        public StopAfterAttemptStrategy(int maxAttemptNumber) {
            Preconditions.checkArgument(maxAttemptNumber >= 1, "maxAttemptNumber must be >= 1 but is %d", maxAttemptNumber);
            this.maxAttemptNumber = maxAttemptNumber;
        }

        public boolean shouldStop(Attempt failedAttempt) {
            return failedAttempt.getAttemptNumber() >= maxAttemptNumber;
        }
    }

    /**
     * 流程超时停止
     */
    @Immutable
    private static final class StopAfterDelayStrategy implements StopStrategy{

        private final long maxDelay;

        public StopAfterDelayStrategy(long maxDelay) {
            Preconditions.checkArgument(maxDelay >= 0L, "maxDelay must be >= 0 but is %d", maxDelay);
            this.maxDelay = maxDelay;
        }

        public boolean shouldStop(Attempt failedAttempt) {
            return failedAttempt.getDelaySinceFirstAttempt() >= maxDelay;
        }
    }
}
