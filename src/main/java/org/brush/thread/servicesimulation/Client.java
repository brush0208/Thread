package org.brush.thread.servicesimulation;

public class Client {
    private static int count=0;
    private int id=count++;
    private final int connertTime;

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
}
