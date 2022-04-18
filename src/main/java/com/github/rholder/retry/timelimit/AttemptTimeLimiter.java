package com.github.rholder.retry.timelimit;

import java.util.concurrent.Callable;

/**
 * Function: 重试事件限制
 *
 * @author liujianlong[liujl24@yusys.com.cn]
 * @description:
 * @date 2022/4/18 16:20:24
 */
public interface AttemptTimeLimiter<V> {
    V call(Callable<V> callable) throws Exception;
}
