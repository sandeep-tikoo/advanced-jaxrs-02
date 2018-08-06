package org.arnav.javabrains.rest;

import java.util.Date;

public class TestObject {

	private String link;
	private Date callTime;
	private Integer CallCounter;
	

	public Integer getCallCounter() {
		return CallCounter;
	}

	public void setCallCounter(Integer callCounter) {
		CallCounter = callCounter;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public Date getCallTime() {
		return callTime;
	}

	public void setCallTime(Date callTime) {
		this.callTime = callTime;
	}

	
	public TestObject() {
	}
	
	public TestObject(String link, Integer CallCounter) {
		super();
		this.link = link;
		this.callTime = new Date();
		this.CallCounter = CallCounter + 1;
	}
	
}
