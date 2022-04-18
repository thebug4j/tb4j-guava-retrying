package com.github.rholder.retry;

import java.util.concurrent.ExecutionException;

/**
 * Function:
 *
 * @author liujianlong[liujl24@yusys.com.cn]
 * @description:
 * @date 2022/4/18 11:07:53
 */
public interface Attempt<V> {

    /**
     * 返回调用结果或者Attempt
     * @return
     * @throws ExecutionException
     */
    public V get() throws ExecutionException;

    /**
     * 调用是否有结果
     * @return  <code>true</code> 有返回结果
     *          <code>false</code> 调用抛出异常
     */
    public boolean hasResult();

    /**
     * 调用是否抛出异常
     * @return  <code>true</code> 抛出异常
     *          <code>false</code> 有返回结果
     */
    public boolean hasException();

    /**
     * 返回调用结果
     * @return 调用结果
     * @throws IllegalStateException 当响应抛出异常时，as indicated by {@link #hasResult()}
     */
    public V getResult() throws IllegalStateException;

    /**
     * @return 返回错误原因
     * @throws IllegalStateException 当无异常时，as indicated by {@link #hasException()}
     */
    public Throwable getExceptionCause() throws IllegalStateException;

    /**
     * 本次attempt序号,从1开始
     * @return
     */
    public long getAttemptNumber();

    /**
     * 第一尝试至今延迟，毫秒为单位 todo?
     * The delay since the start of the first attempt, in milliseconds.
     * @return
     */
    public long getDelaySinceFirstAttempt();
}
