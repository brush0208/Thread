package org.brush.thread.banksimulation;

public class Customer {
    private final int serviceTime;
    private static int count=0;
    private final int id;
    public Customer(int serviceTime) {
        this.serviceTime = serviceTime;
        id=count++;
    }
    public int getServiceTime(){return serviceTime;}
    @Override
    public String toString()
    {
        return "["+"id:"+id+"-"+serviceTime+"]";
    }
}
