package org.brush.thread.concurrency;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class OrnamentalGarden {

    public static void main(String[] args) throws InterruptedException {
        ExecutorService exec= Executors.newCachedThreadPool();
        for(int i=0;i<5;i++)
        {
            exec.execute(new Entrance(i));
        }
        try {
            TimeUnit.SECONDS.sleep(4);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Entrance.cancel();
        exec.shutdown();
        if(!exec.awaitTermination(250,TimeUnit.MILLISECONDS))
        {
            System.out.println("some tasks were not terminated!");
        }
        System.out.println("Total:"+Entrance.getTotal());
        System.out.println("sum:"+Entrance.sumCount());


    }
}

class Count{
    private int count =0;
    private Random rand=new Random(47);
    public  int increment(){
        int temp=count;
        if(rand.nextBoolean())
        {
            Thread.yield();
        }
        return (count=++temp);
    }
    public synchronized int value(){return count;}
}

class Entrance implements Runnable{
    private static Count count=new Count();
   // private AtomicInteger
    private static List<Entrance> entrances=new ArrayList<Entrance>();
    private int number;
    private final int id;
    private static  boolean canceld=false;
    public Entrance(int id) {
        this.id = id;
        entrances.add(this);
    }
    public static void cancel(){canceld=true;}


    public void run() {

        while (!Entrance.canceld)
        {
            //synchronized (this)
            {
                number++;
            }
            System.out.println(this+"Total:"+count.increment());
            try {
                TimeUnit.MILLISECONDS.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println(this+"stoped");
    }
    public synchronized int getValue(){return number;}
    @Override
    public String toString()
    {
        return "Entrance:"+id+":"+getValue();
    }
    public static int getTotal()
    {
        return count.value();
    }
    public static int sumCount()
    {
        int count=0;
        for(Entrance entrance:entrances)
        {
            count+=entrance.getValue();
        }
        return count;
    }
}
