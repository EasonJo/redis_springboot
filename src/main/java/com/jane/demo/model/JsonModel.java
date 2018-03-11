package com.jane.demo.model;

import lombok.Data;

/**
 * 前端数据数据返回
 * @author jane
 * @version 2018/03/09
 */
@Data
public class JsonModel {

    /*返回码*/
    private int code;

    /*返回数据对象*/
    private Object object;

    /*返回信息*/
    private String msg;

    public JsonModel(int code, Object object, String msg) {
        this.code = code;
        this.object = object;
        this.msg = msg;
    }

    public JsonModel(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

}
