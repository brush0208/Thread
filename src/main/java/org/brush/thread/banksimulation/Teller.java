package org.brush.thread.banksimulation;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class Teller implements Runnable ,Comparable<Teller> {
    private static AtomicInteger counter=new AtomicInteger(0);
    private final int id=counter.incrementAndGet();
    private int customersServed=0;
    private CustomerLine customers;
    private boolean servingCustomerLine=true;
    private  Logger logger= LogManager.getLogger("Teller");
    public Teller(CustomerLine customers) {
        this.customers = customers;
    }

    public synchronized int compareTo(Teller o) {
        return customersServed<o.customersServed?-1:(customersServed==o.customersServed?0:1);
    }

    public void run() {

        try {
            while (!Thread.interrupted())
            {
                Customer customer=customers.take();
                TimeUnit.MILLISECONDS.sleep(customer.getServiceTime());
                synchronized (this)
                {
                    customersServed++;
                    while (!servingCustomerLine)
                        wait();
                }
            }
        }catch (InterruptedException it)
        {
            logger.warn("interrupted");
        }
        logger.info("terminating");
    }
    public synchronized void doSomethingElse()
    {
        customersServed=0;
        servingCustomerLine=false;
    }
    public synchronized void serveCustomerLine()
    {
        assert !servingCustomerLine :"already Serving:"+this;
        servingCustomerLine=true;
        notifyAll();
    }

    @Override
    public String toString() {
        return "Teller "+id;
    }
    public String shortString(){return "T"+id;}
}
