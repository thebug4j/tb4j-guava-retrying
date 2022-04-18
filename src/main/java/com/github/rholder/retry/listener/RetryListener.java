package com.github.rholder.retry.listener;

import com.github.rholder.retry.Attempt;
import com.google.common.annotations.Beta;

/**
 * Function: 提供回调
 *
 * @author liujianlong[liujl24@yusys.com.cn]
 * @description:
 * @date 2022/4/18 16:13:03
 * @Study google beta注解，测试版本，后期移除
 */
@Beta
public interface RetryListener {

    <V> void onRetry(Attempt<V> attempt);
}
