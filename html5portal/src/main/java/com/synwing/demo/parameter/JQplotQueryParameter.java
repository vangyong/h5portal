package com.synwing.demo.parameter;

import org.joda.time.LocalDateTime;

public class JQplotQueryParameter {
	
	private LocalDateTime startDateTime;

	private LocalDateTime endDateTime;
	
	private String startDateTimeStr;
	
	private String endDateTimeStr;

	private String statisticsType;

	public LocalDateTime getStartDateTime() {
		return startDateTime;
	}

	public void setStartDateTime(LocalDateTime startDateTime) {
		this.startDateTime = startDateTime;
	}

	public LocalDateTime getEndDateTime() {
		return endDateTime;
	}

	public void setEndDateTime(LocalDateTime endDateTime) {
		this.endDateTime = endDateTime;
	}

	public String getStatisticsType() {
		return statisticsType;
	}

	public void setStatisticsType(String statisticsType) {
		this.statisticsType = statisticsType;
	}

	public String getStartDateTimeStr() {
		return startDateTimeStr;
	}

	public void setStartDateTimeStr(String startDateTimeStr) {
		this.startDateTimeStr = startDateTimeStr;
	}

	public String getEndDateTimeStr() {
		return endDateTimeStr;
	}

	public void setEndDateTimeStr(String endDateTimeStr) {
		this.endDateTimeStr = endDateTimeStr;
	}
	
	

}
