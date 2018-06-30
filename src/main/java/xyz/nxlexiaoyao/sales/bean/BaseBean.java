package xyz.nxlexiaoyao.sales.bean;

public class BaseBean<T> {
	private Integer code;
	private String msg;
	private T data;
	public Integer getCode() {
		return code;
	}
	public void setCode(Integer code) {
		this.code = code;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	
	public T getData() {
		return data;
	}
	public void setData(T data) {
		this.data = data;
	}
	public BaseBean(){
		super();
	}
	public BaseBean(Integer code, String msg, T t) {
		super();
		this.code = code;
		this.msg = msg;
		this.data = t;
	}
}
