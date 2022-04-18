package com.github.rholder.retry.timelimit;

import com.github.rholder.retry.timelimit.AttemptTimeLimiter;
import com.google.common.base.Preconditions;
import com.google.common.util.concurrent.SimpleTimeLimiter;
import com.google.common.util.concurrent.TimeLimiter;
import javax.annotation.Nonnull;
import javax.annotation.concurrent.Immutable;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Function:
 *
 * @author liujianlong[liujl24@yusys.com.cn]
 * @description:
 * @date 2022/4/18 16:21:29
 */
public class AttemptTimeLimiters {
    private AttemptTimeLimiters() {
    }

    /**
     * 无时间限制
     * @param <V>
     * @return
     */
    public static <V> AttemptTimeLimiter<V> noTimeLimit() {
        return new NoAttemptTimeLimit<V>();
    }

    /**
     * 执行时间限制
     * @param duration 实现限制
     * @param timeUnit 单位
     * @param executorService 控制线程管理
     * @param <V>
     * @return
     */
    public static <V> AttemptTimeLimiter<V> fixedTimeLimit(long duration, @Nonnull TimeUnit timeUnit, @Nonnull ExecutorService executorService) {
        Preconditions.checkNotNull(timeUnit);
        return new FixedAttemptTimeLimit<V>(duration, timeUnit, executorService);
    }


    /**
     * 无时间限制
     * @param <V>
     */
    @Immutable
    private static final class NoAttemptTimeLimit<V> implements AttemptTimeLimiter<V> {
        public V call(Callable<V> callable) throws Exception {
            return callable.call();
        }
    }


    /**
     * 执行时间限制
     * @param <V>
     */
    @Immutable
    private static final class FixedAttemptTimeLimit<V> implements AttemptTimeLimiter<V> {

        private final TimeLimiter timeLimiter;
        private final long duration; //持续时间
        private final TimeUnit timeUnit; // 单位

        public FixedAttemptTimeLimit(long duration, @Nonnull TimeUnit timeUnit, @Nonnull ExecutorService executorService) {
            this(SimpleTimeLimiter.create(executorService), duration, timeUnit);
        }

        private FixedAttemptTimeLimit(@Nonnull TimeLimiter timeLimiter, long duration, @Nonnull TimeUnit timeUnit) {
            Preconditions.checkNotNull(timeLimiter);
            Preconditions.checkNotNull(timeUnit);
            this.timeLimiter = timeLimiter;
            this.duration = duration;
            this.timeUnit = timeUnit;
        }

        public V call(Callable<V> callable) throws Exception {
            return timeLimiter.callWithTimeout(callable, duration, timeUnit);
        }
    }
}
