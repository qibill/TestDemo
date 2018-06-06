package com.biosan.utils;

public class BiosanResult {

    // 响应业务状态   1.发送成功    2.发送失败	 3.发送撤销命令失败
    private Integer status;

    // 响应消息
    private String msg;

    // 响应中的数据
    private Object data;

    
	public BiosanResult() {
		super();
	}

	public BiosanResult(Integer status) {
		super();
		this.status = status;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}
    
    public static BiosanResult isOk() {
    	return new BiosanResult(1);
    } 
}
