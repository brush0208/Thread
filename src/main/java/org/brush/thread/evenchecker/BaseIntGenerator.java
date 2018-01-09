package org.brush.thread.evenchecker;

/**
 * @author brush
 */
public abstract class BaseIntGenerator {
    private volatile boolean canceled=false;
    public abstract int next();

    public void cancel(){canceled=true;}
    public boolean isCanceled(){return canceled;}
}
