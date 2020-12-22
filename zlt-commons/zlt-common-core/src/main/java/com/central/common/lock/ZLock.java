package com.central.common.lock;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 锁对象抽象
 *
 * @author zlt
 * @date 2020/7/28
 * <p>
 * Blog: https://zlt2000.gitee.io
 * Github:
 */
@AllArgsConstructor
public class ZLock implements AutoCloseable {
    @Getter
    private final Object lock;

    private final DistributedLock locker;

    @Override
    public void close() throws Exception {
        locker.unlock(lock);
    }
}
