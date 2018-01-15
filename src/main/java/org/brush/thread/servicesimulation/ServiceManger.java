package org.brush.thread.servicesimulation;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ServiceManger implements Runnable {
    private static final int DEFAULT_SIZE=10;
    private static final Logger logger=LogManager.getLogger("ServiceManager");
    private int size=0;
    private ClientLine clients;
    private ServiceLine services;
    private ExecutorService execute;
    public ServiceManger(int size) {
        this.size = size;
    }

    public ServiceManger(int size, ClientLine clients, ServiceLine services) {
        this.size = size;
        this.clients = clients;
        this.services = services;
        startWork();
    }

    private void startWork()
    {
        execute=Executors.newFixedThreadPool(size);
        for(int i=0;i<size;i++)
        {
            Service service = new Service(clients, services);
            execute.execute(service);
            try {
                services.put(service);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    public void run() {

        try {
            while (!Thread.interrupted())
            {
                Service service = services.take();
                service.startWork();
            }
        } catch (InterruptedException e) {
            logger.warn("Interrupted");
        }
        logger.info("end");
    }
}
