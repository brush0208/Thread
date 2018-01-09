package org.brush.thread.evenchecker;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class MuxtIntGenerator extends BaseIntGenerator {
    private int currentVal=0;
    private Lock lock=new ReentrantLock();
    public int next() {
        lock.lock();
        try {
            ++currentVal;
            ++currentVal;
            return currentVal;
        }finally {
            lock.unlock();
        }
    }
}
