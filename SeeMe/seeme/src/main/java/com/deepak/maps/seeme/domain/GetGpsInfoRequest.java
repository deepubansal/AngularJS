package com.deepak.maps.seeme.domain;

import javax.validation.constraints.NotNull;

public class GetGpsInfoRequest {
	
	private Long fromTime;
	private Long toTime;
	
	@NotNull
	private Long deviceId;

	
	public GetGpsInfoRequest() {
	}

	public GetGpsInfoRequest(Long deviceId) {
		this.deviceId = deviceId;
	}
	
	public GetGpsInfoRequest(Long fromTime, Long toTime, Long deviceId) {
		super();
		this.fromTime = fromTime;
		this.toTime = toTime;
		this.deviceId = deviceId;
	}
	public Long getFromTime() {
		return fromTime;
	}
	public void setFromTime(Long fromTime) {
		this.fromTime = fromTime;
	}
	public Long getToTime() {
		return toTime;
	}
	public void setToTime(Long toTime) {
		this.toTime = toTime;
	}
	public Long getDeviceId() {
		return deviceId;
	}
	public void setDeviceId(Long deviceId) {
		this.deviceId = deviceId;
	}
}
