package org.brush.thread.servicesimulation;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class ClientGenerator implements Runnable {
    private final Random rand=new Random(47);
    private ClientLine clients;

    public ClientGenerator(ClientLine clients) {
        this.clients = clients;
    }

    static private Logger logger= LogManager.getLogger("ClientGenerator");
    public void run() {
        try {
            while (!Thread.interrupted())
            {
                logger.info("create one new Client");
                TimeUnit.MILLISECONDS.sleep(rand.nextInt(30));
                clients.put(new Client(rand.nextInt(300)));
               // System.out.println(clients);
            }

        } catch (InterruptedException e) {
            logger.info("interrupted");
        }
            logger.info("end");

    }
}
