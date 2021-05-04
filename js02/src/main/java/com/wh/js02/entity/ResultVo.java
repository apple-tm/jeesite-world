package com.wh.js02.entity;

public class ResultVo<T>{

    private String respCode;
    private String respMsg;
    private T body;

    public void success(){
        this.respCode = "0";
        this.respMsg = "请求成功";
    }

    public void fail(String msg){
        this.respCode = "-1";
        this.respMsg = msg;
    }

    public void needLogin(){
        this.respCode = "99";
        this.respMsg = "需要登录";
    }

    public String getRespCode() {
        return respCode;
    }

    public void setRespCode(String respCode) {
        this.respCode = respCode;
    }

    public String getRespMsg() {
        return respMsg;
    }

    public void setRespMsg(String respMsg) {
        this.respMsg = respMsg;
    }

    public T getBody() {
        return body;
    }

    public void setBody(T body) {
        this.body = body;
    }
}
