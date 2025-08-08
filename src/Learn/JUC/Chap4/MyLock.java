package Learn.JUC.Chap4;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

public class MyLock implements Lock {
    // 独占锁
    class MySync extends AbstractQueuedSynchronizer {
        @Override // 不可重入，arg用于可重入时计数
        protected boolean tryAcquire(int arg) {
            if (compareAndSetState(0, 1)) {
                // 加锁成功，设置owner为当前线程
                setExclusiveOwnerThread(Thread.currentThread());
                return true;
            } else {
                // 加锁失败
                return false;
            }
        }

        @Override
        protected boolean tryRelease(int arg) {
            // 只有持有锁的线程可以释放锁，因此这里直接调用方法即可
            // 释放持有线程
            setExclusiveOwnerThread(null);
            // state有volatile修饰，因此需要放在后面
            setState(0);
            return true;
        }

        @Override // 是否持有独占锁
        protected boolean isHeldExclusively() {
            return getExclusiveOwnerThread().equals(Thread.currentThread());
        }

        public Condition newCondition() {
            return new ConditionObject();
        }
    }

    private MySync sync = new MySync();

    @Override // 加锁，不成功加入等待队列等待
    public void lock() {
        sync.acquire(1);
    }

    @Override// 加锁可打断
    public void lockInterruptibly() throws InterruptedException {
        sync.acquireInterruptibly(1);
    }

    @Override // 尝试加锁，不会重试
    public boolean tryLock() {

        return sync.tryAcquire(1);
    }

    @Override // 尝试加锁，带超时时间
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        return sync.tryAcquireNanos(1, unit.toNanos(time));
    }

    @Override // 解锁
    public void unlock() {
        sync.release(1);
    }

    @Override // 创建条件变量
    public Condition newCondition() {
        return sync.newCondition();
    }
}
