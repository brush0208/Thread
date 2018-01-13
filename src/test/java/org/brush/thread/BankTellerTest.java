package org.brush.thread;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.brush.thread.banksimulation.CustomerGenerator;
import org.brush.thread.banksimulation.CustomerLine;
import org.brush.thread.banksimulation.TellerManager;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class BankTellerTest {
    static final int MAX_LINE_SIZE=50;
    static final int ADJUSTMENT_PERIOD=100;
    Logger log = LogManager.getLogger("Test");
    @Before
    public void setUp(){
        log.info("Start Test");
    }
    @After
    public void shutDown(){
        log.info("End Test");
    }
    @Test
    public void testBankTeller() throws InterruptedException {

        ExecutorService exe= Executors.newCachedThreadPool();
        CustomerLine customers=new CustomerLine(MAX_LINE_SIZE);
        exe.execute(new CustomerGenerator(customers));
        exe.execute(new TellerManager(exe,customers,ADJUSTMENT_PERIOD));
        TimeUnit.SECONDS.sleep(100);
        exe.shutdownNow();
    }
}
