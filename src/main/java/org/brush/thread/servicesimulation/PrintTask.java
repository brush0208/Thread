package org.brush.thread.servicesimulation;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.util.concurrent.TimeUnit;

public class PrintTask implements Runnable{
    private ClientLine clients;
    private ServiceLine services;

    private static Logger logger= LogManager.getLogger("PrintTask");
    public PrintTask(ClientLine clients, ServiceLine services) {
        this.clients = clients;
        this.services = services;
    }

    public void run() {

        try {
            while (!Thread.interrupted())
            {
                TimeUnit.MILLISECONDS.sleep(300);
                synchronized (clients){
                    logger.info("clients"+clients.size());
                   /* for(Client client:clients)
                    {
                        System.out.print(client);
                    }
                    System.out.println("}");*/
                }
                synchronized (services){
                    logger.info("services"+services.size());
                    /*for(Service service:services)
                    {
                        System.out.print(service);
                    }
                    System.out.println("}");*/
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
