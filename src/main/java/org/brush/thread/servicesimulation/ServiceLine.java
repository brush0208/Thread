package org.brush.thread.servicesimulation;

import java.util.concurrent.ArrayBlockingQueue;

public class ServiceLine extends ArrayBlockingQueue<Service> {
    private static final int DEFAULT_SIZE=10;

    public ServiceLine() {
        super(DEFAULT_SIZE);
    }

    public ServiceLine(int capacity) {
        super(capacity);
    }

    @Override
    public String toString() {
        if(this.size()==0)
            return "[Empity]";
        StringBuffer line=new StringBuffer();
        for(Service service:this)
        {
            line.append(service);
        }
        return line.toString();
    }
}
