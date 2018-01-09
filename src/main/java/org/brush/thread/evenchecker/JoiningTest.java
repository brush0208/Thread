package org.brush.thread.evenchecker;

 class Sleeper extends Thread{
    private int duratioon;

    public Sleeper(String name, int duratioon) {
        super(name);
        this.duratioon = duratioon;
        start();
    }

    @Override
    public void run() {
        try {
            sleep(duratioon);
        } catch (InterruptedException e) {
            System.out.println(getName()+"was interrupted."+"isInterrupted():"+isInterrupted());
            return;
        }
        System.out.println(getName()+"has awakened");
    }
}
class Joiner extends Thread{
    private Sleeper joiningTest;

    public Joiner(String name, Sleeper joiningTest) {
        super(name);
        this.joiningTest = joiningTest;
        start();
    }

    @Override
    public void run() {
        try {
            joiningTest.join();

        } catch (InterruptedException e) {
            //e.printStackTrace();
            System.out.println("interrupted");
        }
        System.out.println(getName()+"join completed;");
        super.run();
    }
}
public class JoiningTest{
     public static void main(String[] args){
        Sleeper sleep=new Sleeper("Sleepy",1500);
        Sleeper grumpy=new Sleeper("grumpy",1500);
        Joiner dopey=new Joiner("Dopey",sleep);
        Joiner doc=new Joiner("Doc",grumpy);
        grumpy.interrupt();
     }
}
