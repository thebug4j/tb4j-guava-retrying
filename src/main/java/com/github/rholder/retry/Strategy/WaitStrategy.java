package com.github.rholder.retry.Strategy;

import com.github.rholder.retry.Attempt;

/**
 * Function: 失败重试的等待策略
 *
 * @author liujianlong[liujl24@yusys.com.cn]
 * @description:
 * @date 2022/4/18 11:04:51
 */
public interface WaitStrategy {

    long computeSleepTime(Attempt failedAttempt);
}
