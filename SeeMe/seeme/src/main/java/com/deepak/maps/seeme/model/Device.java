package com.deepak.maps.seeme.model;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Device {

	private Integer id;
	private String androidId;
	private String serialNo;

	private Set<GPSInfo> gpsInfos;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}

	public String getAndroidId() {
		return androidId;
	}
	
	public void setAndroidId(String androidId) {
		this.androidId = androidId;
	}
	
	public String getSerialNo() {
		return serialNo;
	}
	
	public void setSerialNo(String serialNo) {
		this.serialNo = serialNo;
	}
	
	@OneToMany(mappedBy = "device")
	public Set<GPSInfo> getGpsInfos() {
		return gpsInfos;
	}

	public void setGpsInfos(Set<GPSInfo> gpsInfos) {
		this.gpsInfos = gpsInfos;
	}
}
