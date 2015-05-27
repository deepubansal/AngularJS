package com.deepak.maps.seeme.model;

import java.text.ParseException;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.deepak.maps.seeme.domain.GPSLog;

@Entity
public class GPSInfo {
	
    private Long id;
	
	private Double lat;
	private Double lon;
	private Integer sat;
	private String description;
	private Double alt;
	private String acc;
	private String dir;
	private String prov;
	private String spd;
	private Date time;
	private String battery;

	private Device device;
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public GPSInfo() {
		super();
	}

	public GPSInfo(GPSLog gpsLog) throws ParseException {
		this.lat = gpsLog.getLat();
		this.lon = gpsLog.getLon();
		this.sat = gpsLog.getSat();
		this.description = gpsLog.getDesc();
		this.alt = gpsLog.getAlt();
		this.acc = gpsLog.getAcc();
		this.dir = gpsLog.getDir();
		this.prov = gpsLog.getProv();
		this.spd = gpsLog.getSpd();
		this.time = gpsLog.getTimeAsDate();
		this.battery = gpsLog.getBattery();		
	}

	@Override
	public String toString() {
		return "GPSInfo [lat=" + lat + ", lon=" + lon + ", sat=" + sat
				+ ", desc=" + description + ", alt=" + alt + ", acc=" + acc + ", dir="
				+ dir + ", prov=" + prov + ", spd=" + spd + ", time=" + time
				+ ", battery=" + battery + "]";
	}

	public Double getLat() {
		return lat;
	}

	public void setLat(Double lat) {
		this.lat = lat;
	}

	public Double getLon() {
		return lon;
	}

	public void setLon(Double lon) {
		this.lon = lon;
	}

	public Integer getSat() {
		return sat;
	}

	public void setSat(Integer sat) {
		this.sat = sat;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Double getAlt() {
		return alt;
	}

	public void setAlt(Double alt) {
		this.alt = alt;
	}

	public String getAcc() {
		return acc;
	}

	public void setAcc(String acc) {
		this.acc = acc;
	}

	public String getDir() {
		return dir;
	}

	public void setDir(String dir) {
		this.dir = dir;
	}

	public String getProv() {
		return prov;
	}

	public void setProv(String prov) {
		this.prov = prov;
	}

	public String getSpd() {
		return spd;
	}

	public void setSpd(String spd) {
		this.spd = spd;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date l) {
		this.time = l;
	}

	public String getBattery() {
		return battery;
	}

	public void setBattery(String battery) {
		this.battery = battery;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((acc == null) ? 0 : acc.hashCode());
		result = prime * result + ((alt == null) ? 0 : alt.hashCode());
		result = prime * result + ((battery == null) ? 0 : battery.hashCode());
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((dir == null) ? 0 : dir.hashCode());
		result = prime * result + ((lat == null) ? 0 : lat.hashCode());
		result = prime * result + ((lon == null) ? 0 : lon.hashCode());
		result = prime * result + ((prov == null) ? 0 : prov.hashCode());
		result = prime * result + ((sat == null) ? 0 : sat.hashCode());
		result = prime * result + ((spd == null) ? 0 : spd.hashCode());
		result = prime * result + ((time == null) ? 0 : time.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		GPSInfo other = (GPSInfo) obj;
		if (acc == null) {
			if (other.acc != null)
				return false;
		} else if (!acc.equals(other.acc))
			return false;
		if (alt == null) {
			if (other.alt != null)
				return false;
		} else if (!alt.equals(other.alt))
			return false;
		if (battery == null) {
			if (other.battery != null)
				return false;
		} else if (!battery.equals(other.battery))
			return false;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (dir == null) {
			if (other.dir != null)
				return false;
		} else if (!dir.equals(other.dir))
			return false;
		if (lat == null) {
			if (other.lat != null)
				return false;
		} else if (!lat.equals(other.lat))
			return false;
		if (lon == null) {
			if (other.lon != null)
				return false;
		} else if (!lon.equals(other.lon))
			return false;
		if (prov == null) {
			if (other.prov != null)
				return false;
		} else if (!prov.equals(other.prov))
			return false;
		if (sat == null) {
			if (other.sat != null)
				return false;
		} else if (!sat.equals(other.sat))
			return false;
		if (spd == null) {
			if (other.spd != null)
				return false;
		} else if (!spd.equals(other.spd))
			return false;
		if (time == null) {
			if (other.time != null)
				return false;
		} else if (!time.equals(other.time))
			return false;
		return true;
	}

	@ManyToOne(cascade = CascadeType.ALL)
	public Device getDevice() {
		return device;
	}

	public void setDevice(Device device) {
		this.device = device;
	}



}