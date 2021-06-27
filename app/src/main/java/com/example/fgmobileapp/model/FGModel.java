package com.example.fgmobileapp.model;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.concurrent.Executors;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ThreadPoolExecutor;

public class FGModel {
    private float throttle = 0;
    private float rudder = -1;
    private float aileron = 0;
    private float elevator = 0;
    private ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(1);
    //private ForkJoinPool executor = ForkJoinPool.commonPool();

    //
    private String ip;
    private int port;
    static Socket fgSocket;// = null;
    static PrintWriter out;
    String currIP = "";
    int currPort = -1;
    Boolean stop = false;
    static int test = 0;

    public void Connect(String ip,int port) throws Exception {
        try{
            if (currIP != ip || currPort != port)
            {
                currIP = ip;
                currPort = port;
                if (fgSocket != null)
                {
                    fgSocket.close();
                }
                //fgSocket = new Socket(ip, port);
                //out = new PrintWriter(fgSocket.getOutputStream(),true);
                //executor.submit(()->{
                //     try
                //     {
                //         test = 450;
                //         fgSocket = new Socket(ip, port);
                //         out = new PrintWriter(fgSocket.getOutputStream(),true);
                //     }
                //     catch (Exception e)
                //     {
                //         e.printStackTrace();
                //         System.out.println("err");
                //     }
                // });
                executor.submit(()->{
                    try
                    {
                        fgSocket = new Socket(ip, port);
                        out = new PrintWriter(fgSocket.getOutputStream(),true);
                    }
                    catch (Exception e)
                    {
                        e.printStackTrace();
                        System.out.println("err");
                    }
                });
                //t.start();
//                executor.submit(()->{
//                    try
//                    {
//                        fgSocket = new Socket(ip, port);
//                        out = new PrintWriter(fgSocket.getOutputStream(),true);
//                    }
//                    catch (Exception e)
//                    {
//                        e.printStackTrace();
//                        System.out.println("err");
//                    }
//                });
            }
        }
        catch (Exception e){
            e.printStackTrace();
            System.out.println("err");
        }
    }

    public void FGCommand(String line, float f)
    {
        //final String tempLine = line;
        //final float tempF = f;
        executor.submit(()->{
            out.print(line + f + "\r\n");
            out.flush();
        });
    }

    public float getThrottle() {
        return throttle;
    }

    public void setThrottle(float throttle) {
        this.throttle = throttle;
    }

    public float getRudder() {
        return rudder;
    }

    public void setRudder(float rudder) {
        this.rudder = rudder;
    }

    public float getAileron() {
        return aileron;
    }

    public void setAileron(float aileron) {
        this.aileron = aileron;
    }

    public float getElevator() {
        return elevator;
    }

    public void setElevator(float elevator) {
        this.elevator = elevator;
    }

//    public String getIp() {
//        return ip;
//    }
//
//    public void setIp(String ip) {
//        this.ip = ip;
//    }
//
//    public int getPort() {
//        return port;
//    }
//
//    public void setPort(int port) {
//        this.port = port;
//    }
}
