package com.github.rholder.retry.exception;


import com.github.rholder.retry.Attempt;

import javax.annotation.Nonnull;
import javax.annotation.concurrent.Immutable;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Function:
 *
 * @author liujianlong[liujl24@yusys.com.cn]
 * @description:
 * @date 2022/4/18 15:38:02
 */
@Immutable
public final class RetryException extends Exception{

    private final int numberOfFailedAttempts;
    private final Attempt<?> lastFailedAttempt;


    public RetryException(int numberOfFailedAttempts, @Nonnull Attempt<?> lastFailedAttempt) {
        this("Retrying failed to complete successfully after " + numberOfFailedAttempts + " attempts.", numberOfFailedAttempts, lastFailedAttempt);
    }

    public RetryException(String message, int numberOfFailedAttempts, Attempt<?> lastFailedAttempt) {
        super(message, checkNotNull(lastFailedAttempt, "Last attempt was null").hasException() ? lastFailedAttempt.getExceptionCause() : null);
        this.numberOfFailedAttempts = numberOfFailedAttempts;
        this.lastFailedAttempt = lastFailedAttempt;
    }

    public int getNumberOfFailedAttempts() {
        return numberOfFailedAttempts;
    }

    public Attempt<?> getLastFailedAttempt() {
        return lastFailedAttempt;
    }
}

