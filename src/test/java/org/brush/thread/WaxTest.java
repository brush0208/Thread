package org.brush.thread;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.brush.thread.waitandnotify.Car;
import org.brush.thread.waitandnotify.WaxOMatic;
import org.junit.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class WaxTest  {
    Logger log = LogManager.getLogger("");

    @Test
    public void testWax()
    {


        log.info("开始");
        Car car =new Car();
        ExecutorService exe= Executors.newCachedThreadPool();
        exe.execute(new WaxOMatic.WaxOn(car));
        exe.execute(new WaxOMatic.WaxOff(car));
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        exe.shutdownNow();
        log.info("StopTest");
    }
}
