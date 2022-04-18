package com.github.rholder.retry.Strategy;

/**
 * Function: 阻塞策略
 *
 * @author liujianlong[liujl24@yusys.com.cn]
 * @description:
 * @date 2022/4/18 11:40:15
 */
public interface BlockStrategy {

    void block(long sleepTime) throws InterruptedException;

}
