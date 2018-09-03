package service;

import java.util.HashMap;
import java.util.Map;

public enum GamblerSave {
    INSTANCE;

    private Map<String, int[]> gamblerMap;
    private int chip = 20;
    private int count = -1;

    private GamblerSave() {
        System.out.println("1----------------------------");
        gamblerMap = new HashMap<>();
    }

    public void first(String ip) {
        System.out.println("2----------------------------");
        if (gamblerMap.get(ip) == null) {
            System.out.println("2.2---------------------"+gamblerMap.get(ip));
            gamblerMap.put(ip, new int[]{chip, -1,count});
        }
        System.out.println("2.1---------------------"+gamblerMap.get(ip)[1]);
    }

    public int getChip(String ip){
        System.out.println("3----------------------------" + gamblerMap) ;
        return gamblerMap.get(ip)[0];}

    public void putNum(String ip){
        System.out.println("4----------------------------" + "chip" + ":" + gamblerMap.get(ip)[0]);
        int num = (int) (Math.random() * 11);
        gamblerMap.put(ip,new int[]{chip,num,0});
        System.out.println("4.1----------------------------" + "chip" +":" + gamblerMap.get(ip)[0]);
    }

    public int getNum(String ip){
        System.out.println("5----------------------------" + gamblerMap);
        return gamblerMap.get(ip)[1];
    }


    public void win(String ip,int num){
        chip += 5;
        gamblerMap.put(ip,new int[]{chip , num, -1});
        }

    public void lose(String ip,int num){
        chip -= 5;
        gamblerMap.put(ip,new int[]{chip , num, -1});
    }

    public int[] getGamblerMap(String ip) {
        return gamblerMap.get(ip);
    }
}
