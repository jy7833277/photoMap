package com.tmall.PhotoMap.o;

public class DialogDto {

	private String message ;
	
	private String title ;
	
	private String confireButtonStr = "确定";
	
	private String cancleButtonStr = "取消" ;

	public DialogDto(String m ,String t ){
		message = m ;
		title = t ;
	}
	
	public DialogDto(String m ,String t ,String conStr ,String canStr){
		message = m ;
		title = t ;
		confireButtonStr = conStr ;
		cancleButtonStr = canStr ;
	}
	
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getConfireButtonStr() {
		return confireButtonStr;
	}

	public void setConfireButtonStr(String confireButtonStr) {
		this.confireButtonStr = confireButtonStr;
	}

	public String getCancleButtonStr() {
		return cancleButtonStr;
	}

	public void setCancleButtonStr(String cancleButtonStr) {
		this.cancleButtonStr = cancleButtonStr;
	}
	
	
}
