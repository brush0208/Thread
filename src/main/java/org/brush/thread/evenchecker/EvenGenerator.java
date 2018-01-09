package org.brush.thread.evenchecker;

public class EvenGenerator extends BaseIntGenerator {
    private int currentEvenValue=0;
    public int next() {
        ++currentEvenValue;
        ++currentEvenValue;
        return currentEvenValue;
    }
    public static void main(String[] args){
        EvenChecker.test(new MuxtIntGenerator());
        try {
            Thread.sleep(100000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
