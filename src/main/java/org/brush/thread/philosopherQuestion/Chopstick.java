package org.brush.thread.philosopherQuestion;

public class Chopstick {
    private boolean tabken=false;
    public synchronized void take()throws InterruptedException{
        while (tabken)
            wait();
        tabken=true;
    }
    public synchronized void drop() throws InterruptedException
    {
        tabken=false;
        notifyAll();
    }
}
