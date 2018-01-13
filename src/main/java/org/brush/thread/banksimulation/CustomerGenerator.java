package org.brush.thread.banksimulation;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class CustomerGenerator implements Runnable {
    private CustomerLine customers;
    private static Random rand=new Random(47);
    private  Logger log = LogManager.getLogger("CustomerGenerator");
    public CustomerGenerator(CustomerLine customers) {
        this.customers = customers;
    }
    public void run() {
       try {
           while (!Thread.interrupted())
           {
               TimeUnit.MILLISECONDS.sleep(rand.nextInt(300));
               customers.put(new Customer(rand.nextInt(1000)));
           }
       }catch (InterruptedException e)
       {
            log.warn("Interrupted");
       }
       log.info("CustomerGenerator terminating");

    }
}
