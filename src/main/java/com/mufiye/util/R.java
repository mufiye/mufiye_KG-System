package com.mufiye.util;

import lombok.Data;

@Data
public class R {
    //操作是否执行成功
    private boolean flag;
    //操作携带的数据
    private Object data;
    //操作信息描述
    private String message;

    public R(){};

    public R(boolean flag) {
        this.flag = flag;
    }

    public R(boolean flag, Object data, String message) {
        this.flag = flag;
        this.data = data;
        this.message = message;
    }

    public R(boolean flag, Object data) {
        this(flag,data,null);
    }
}
