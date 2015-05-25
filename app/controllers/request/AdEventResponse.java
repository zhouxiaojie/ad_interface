package controllers.request;

public class AdEventResponse {
	
	/**
	 * 错误码
	 * 200 成功
	 * 其他 失败
	 */
	private int code;
	
	public AdEventResponse(){
		
	}
	
	public AdEventResponse(int code){
		setCode(code);
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}
}
