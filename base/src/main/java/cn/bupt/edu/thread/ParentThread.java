package cn.bupt.edu.thread;

import java.util.UUID;

public class ParentThread extends Thread {
    private  String[] chains ;
    private  String uuid ;
    private  int version;
    private  Runnable tr;

    public ParentThread(Runnable r) {
        chains = new String[3];
        uuid = UUID.randomUUID().toString();
        tr = r;
    }

    @Override
    public void run() {
        tr.run();
    }

    public String[] getChains() {
        return chains;
    }

    public void setChains(String[] chains) {
        this.chains = chains;
    }

    public  String getUuid() {
        return uuid;
    }

    public  void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }
}
