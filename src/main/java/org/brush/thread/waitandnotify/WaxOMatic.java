package org.brush.thread.waitandnotify;

import com.sun.javafx.runtime.SystemProperties;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

class Car{
    private boolean waxOn=false;
    public synchronized void waxed(){
        waxOn=true;
        notifyAll();
    }
    public synchronized void buffed(){
        waxOn=false;
        notifyAll();
    }
    public synchronized void waitForWaxing()throws InterruptedException
    {
        while (waxOn==false)
            wait();
    }
    public synchronized void waitForBuffing() throws InterruptedException
    {
        while (waxOn==true)
            wait();
    }
}

class WaxOn implements Runnable{
    private Car car;
    public WaxOn(Car car){this.car=car;}
    public void run(){
        try{
            while (!Thread.interrupted())
            {
                System.out.println("Wax on");
                TimeUnit.MILLISECONDS.toSeconds(100);
                car.waxed();
                car.waitForBuffing();
            }
        } catch (InterruptedException e) {
           // e.printStackTrace();
            System.out.println("Exiting via interrupt");
        }
        System.out.println("Ending Wax on task");
    }
}

class WaxOff implements Runnable{
    private Car car;

    public WaxOff(Car car) {
        this.car = car;
    }

    public void run() {
        try {
            while(!Thread.interrupted())
            {
                car.waitForWaxing();
                System.out.println("Wax Off");
                TimeUnit.MILLISECONDS.sleep(800);
                car.buffed();
            }
        } catch (InterruptedException e) {
           // e.printStackTrace();
            System.out.println("Exiting via Interrupted");
        }
        System.out.println("Ending Wax Off task");
    }
}
public class WaxOMatic {

    public static void main(String[] args){
        Car car =new Car();
        ExecutorService exe= Executors.newCachedThreadPool();
        exe.execute(new WaxOn(car));
        exe.execute(new WaxOff(car));
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        exe.shutdownNow();
    }


}
