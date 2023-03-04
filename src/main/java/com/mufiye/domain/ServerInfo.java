package com.mufiye.domain;

import lombok.Data;

@Data
public class ServerInfo {
    //系统时间
    private String time;
    //用户上次登录时间
    private String lastLogin;
    //实体总数
    private int entityNum;
    //关系总数
    private int relationshipNum;
    //单位 MB 已占用内存
    private int occupiedMem;
    //单位 MB 空闲内存
    private int freeMem;
    //服务器CPU占用率
    private int occupiedCpu;
}
