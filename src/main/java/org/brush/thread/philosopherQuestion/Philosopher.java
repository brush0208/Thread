package org.brush.thread.philosopherQuestion;

import jdk.nashorn.internal.ir.CatchNode;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Philosopher  implements Runnable{
    private Chopstick left;
    private Chopstick right;
    private final int id;
    private final int ponderFactor;
    private Random rand=new Random(47);

    public Philosopher(Chopstick left, Chopstick right, int id, int ponderFactor) {
        this.left = left;
        this.right = right;
        this.id = id;
        this.ponderFactor = ponderFactor;
    }

    private void pause() throws InterruptedException
    {
        if(ponderFactor==0)return;;
        TimeUnit.MILLISECONDS.sleep(rand.nextInt(ponderFactor*250));
    }

    public void run() {
        try{
            while (!Thread.interrupted())
            {
                System.out.println(this+" "+"thinking");
                pause();
                System.out.println(this+" "+"grabbing right");
                right.take();
                System.out.println(this+" "+"grapping left" );
                left.take();
                System.out.println(this+" "+"Eating");
                pause();
                right.drop();
                left.drop();
            }

        }catch (InterruptedException ie)
        {
           System.out.println(this+" "+"interrupt");
        }
    }

    @Override
    public String toString() {
        return "Philosopher{"+id+"}";
    }
}
