package org.brush.thread.banksimulation;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;


public class TellerManager implements Runnable {
    private ExecutorService exec;
    private CustomerLine customers;
    private PriorityQueue<Teller> workingTellers=new PriorityQueue<Teller>();
    private Queue<Teller> tellersDoingOtherThing=new LinkedList<Teller>();
    private int adjustmentPeriod;
    private static Random rand=new Random(47);
    private  Logger log= LogManager.getLogger("TellerManager");
    public TellerManager(ExecutorService exec, CustomerLine customers, int adjustmentPeriod) {
        this.exec = exec;
        this.customers = customers;
        this.adjustmentPeriod = adjustmentPeriod;
        Teller teller=new Teller(customers);
        workingTellers.add(teller);
        exec.execute(teller);
    }
    public void adjustTellerNumber()
    {
        if(customers.size()/workingTellers.size()>2)
        {
            if(tellersDoingOtherThing.size()>0)
            {
                Teller teller = tellersDoingOtherThing.remove();
                teller.serveCustomerLine();
                workingTellers.offer(teller);
                return ;
            }
            Teller teller = new Teller(customers);
            exec.execute(teller);
            workingTellers.add(teller);
            return;
        }
        if(workingTellers.size()>1&&customers.size()/workingTellers.size()<2)
        {
            reassignOneTeller();
        }
        if(customers.size()==0)
        {
            while (workingTellers.size()>1)
                reassignOneTeller();
        }

    }

    public void reassignOneTeller(){
        Teller teller=workingTellers.poll();
        teller.doSomethingElse();
        tellersDoingOtherThing.offer(teller);
    }
    public void run() {

        try {
            while (!Thread.interrupted())
            {
                TimeUnit.MILLISECONDS.sleep(adjustmentPeriod);
                adjustTellerNumber();
                synchronized (customers){
                    System.out.println(customers+"{");
                    for (Teller teller:workingTellers)
                    {
                        System.out.println(teller.shortString()+"");
                    }
                    System.out.println("}");
                }

            }
        } catch (InterruptedException e) {
            log.warn("Interrupt");
        }
        log.info("terminating");
    }

    @Override
    public String toString() {
        return "TellerManager";
    }
}
