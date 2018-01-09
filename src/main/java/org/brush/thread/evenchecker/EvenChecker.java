package org.brush.thread.evenchecker;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class EvenChecker implements Runnable{

    private BaseIntGenerator generator;
    private final int id;

    public EvenChecker(BaseIntGenerator generator, int id) {
        this.generator = generator;
        this.id = id;
    }


    public void run() {
        while (!generator.isCanceled())
        {
            int val=generator.next();
            if(val%2!=0) {
                System.out.println(val+" not even!");
                generator.cancel();
            }

        }
    }
    public static void test(BaseIntGenerator gp,int count)
    {
        System.out.println("Press Control-C to exit");
        ExecutorService exec= Executors.newCachedThreadPool();
        for(int i=0;i<count;i++)
        {
            exec.execute(new EvenChecker(gp,i));
        }
        exec.shutdown();
        System.out.println("wait for test stop");
    }
    public static void test(BaseIntGenerator gp)
    {
        test(gp,10);
    }
}
