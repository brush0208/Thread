package org.brush.thread.servicesimulation;

import java.util.concurrent.ArrayBlockingQueue;

public class ClientLine extends ArrayBlockingQueue<Client> {
    public ClientLine(int capacity) {
        super(capacity);
    }

    @Override
    public String toString() {
        if(this.size()==0)
            return "[Empty]";
        StringBuffer line=new StringBuffer();
        for(Client client:this)
        {
            line.append(client);
        }
        return line.toString();
    }
}
