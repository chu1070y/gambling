package service;

import java.util.HashMap;
import java.util.Map;

public enum WaitSave {
    INSTANCE;

    private Map<Integer,String> waitMap;

    private WaitSave() {
        waitMap = new HashMap<>();
    }

    public void first(String ip) {
        if (waitMap.get(1) == null) {
            waitMap.put(1, ip);
        }
        else if (waitMap.get(2) == null && waitMap.get(1)!=ip) {
            waitMap.put(2, ip);
        }
    }

    public String getip(int num){
        return waitMap.get(num);
    }

}//end enum
