package org.brush.thread.servicesimulation;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.util.Date;
import java.util.concurrent.TimeUnit;

public class Service implements Runnable,Comparable<Service> {
    //private
    private static int count=0;
    private final int id=count++;
    private ClientLine clients;
    private ServiceLine services;
    private volatile boolean serviceIng=true;
    private  Logger logger= LogManager.getLogger("Service:"+id);
    public Service(ClientLine clients,ServiceLine services) {
        this.clients = clients;
        this.services = services;

    }

    public int compareTo(Service o) {
        return 0;
    }

    public void run() {
        try
        {
            while (!Thread.interrupted())
            {
                Client client = clients.take();
                logger.info(this+"-service for"+client+"Waited Time:"+(new Date().getTime()-client.getStartTime()));
                //logger.info(this+"-service for"+client);
                TimeUnit.MILLISECONDS.sleep(client.getConnertTime());
                endWork();

                synchronized (this)
                {
                    while (!serviceIng)
                        wait();
                }
            }
        } catch (InterruptedException e) {
                logger.warn("interrupted");
        }
        logger.warn("end");


    }
    public synchronized void startWork()
    {
        this.serviceIng=true;
        this.notifyAll();
    }
    public synchronized void endWork() throws InterruptedException {
        this.serviceIng=false;
        services.put(this);
    }

    @Override
    public String toString() {
        return "[id:"+id+"]";
    }
}
