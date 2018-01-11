package org.brush.thread.philosopherQuestion;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class DeadlockingDiningPhilosophers  {
    public static void main(String[] args) throws InterruptedException {
        int ponder=0;
        int size=5;
        ExecutorService exe=Executors.newCachedThreadPool();
        Chopstick[] chopsticks=new Chopstick[size];
        for(int i=0;i<size;i++)
            chopsticks[i]=new Chopstick();
        for (int i=0;i<size;i++)
        {
            exe.execute(new Philosopher(chopsticks[i],chopsticks[(i+1)%size],i,ponder));
        }
        TimeUnit.SECONDS.sleep(30);
        exe.shutdownNow();
    }
}
