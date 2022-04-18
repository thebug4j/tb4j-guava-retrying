package com.github.rholder.retry.Strategy;

import com.github.rholder.retry.Attempt;

/**
 * Function: 决定是否停止失败后的重试
 *
 * @author liujianlong[liujl24@yusys.com.cn]
 * @description:
 * @date 2022/4/18 14:01:57
 */
public interface StopStrategy {

    /**
     *
     * @param failedAttempt
     * @return <code>true</code> 停止重试
     *         <code>false</code> otherwise
     */
    boolean shouldStop(Attempt failedAttempt);
}
