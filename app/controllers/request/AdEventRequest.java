package controllers.request;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AdEventRequest implements Serializable{
	
	private TerminalInfo terminal;
	
	/**
	 * 事件id
	 */
	private String event;
	
	/**
	 * 事件参数
	 */
	private String args[];

	public TerminalInfo getTerminal() {
		return terminal;
	}

	public void setTerminal(TerminalInfo terminal) {
		this.terminal = terminal;
	}

	public String getEvent() {
		return event;
	}

	public void setEvent(String event) {
		this.event = event;
	}

	public String[] getArgs() {
		return args;
	}
	
	public String getArgs2Str() {
		String str = null;
		if(args!=null){
			for (String arg : args) {
				str+=arg;
			}
		}
		return str;
	}
	
	public void setArgs(String[] args) {
		this.args = args;
	}
	
	
	

}
