package org.brush.thread.banksimulation;

import java.util.concurrent.ArrayBlockingQueue;

public class CustomerLine extends ArrayBlockingQueue<Customer> {
    public CustomerLine(int capacity) {
        super(capacity);
    }

    @Override
    public String toString() {
        if(this.size()==0)
            return "[Empty]";
        StringBuffer line=new StringBuffer();
        for(Customer customer:this)
        {
            line.append(customer);
        }
        return line.toString();
    }
}
