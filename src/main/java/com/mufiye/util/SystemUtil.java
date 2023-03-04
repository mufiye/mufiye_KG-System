package com.mufiye.util;


import com.sun.management.OperatingSystemMXBean;

import java.lang.management.ManagementFactory;

public class SystemUtil {

    private static final OperatingSystemMXBean osmxb = (OperatingSystemMXBean) ManagementFactory.getOperatingSystemMXBean();


    public static long getSystemFreeMem(){
        return osmxb.getFreePhysicalMemorySize()/1024/1024;
    }


    public static long getSystemOccupiedMem(){
        return (osmxb.getTotalPhysicalMemorySize()/1024/1024 - osmxb.getFreePhysicalMemorySize()/1024/1024);
    }

    public static int getOccupiedCpu(){
        double cpuLoad = osmxb.getSystemCpuLoad();
        return (int)(cpuLoad*100);
    }
}
