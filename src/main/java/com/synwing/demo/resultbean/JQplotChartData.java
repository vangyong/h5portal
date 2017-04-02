package com.synwing.demo.resultbean;

import org.joda.time.LocalDateTime;

public class JQplotChartData {
	
	int number;
	private LocalDateTime dateTime;

	private String dateTimeStr;

	private Double cgmValue;

	public LocalDateTime getDateTime() {
		return dateTime;
	}

	public void setDateTime(LocalDateTime dateTime) {
		this.dateTime = dateTime;
	}

	public Double getCgmValue() {
		return cgmValue;
	}

	public void setCgmValue(Double cgmValue) {
		this.cgmValue = cgmValue;
	}

	public String getDateTimeStr() {
		return dateTimeStr;
	}

	public void setDateTimeStr(String dateTimeStr) {
		this.dateTimeStr = dateTimeStr;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

}
