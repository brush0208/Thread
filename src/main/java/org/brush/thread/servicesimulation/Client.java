package org.brush.thread.servicesimulation;

import sun.rmi.runtime.Log;

import java.util.Date;

public class Client {
    private static int count=0;
    private int id=count++;
    private final int connertTime;

    private final long startTime=new Date().getTime();
    public Client(int connertTime) {
        this.connertTime = connertTime;
    }

    public int getConnertTime() {
        return connertTime;
    }

    @Override
    public String toString() {
        return "[id:"+id+"-"+connertTime+"]";
    }

    public long getStartTime() {
        return startTime;
    }
}
