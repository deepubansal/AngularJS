package com.deepak.maps.seeme.domain;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

import com.deepak.maps.seeme.model.GPSInfo;


public class GPSLog {

	private Double lat;
	private Double lon;
	private Integer sat;
	private String desc;
	private Double alt;
	private String acc;
	private String dir;
	private String prov;
	private String spd;
	private String time;
	private String battery;
	private String androidId;
	private String serial;
	
	public GPSLog(GPSInfo gpsInfo) {
		this.lat = gpsInfo.getLat();
		this.lon = gpsInfo.getLon();
		this.sat = gpsInfo.getSat();
		this.desc = gpsInfo.getDescription();
		this.alt = gpsInfo.getAlt();
		this.acc = gpsInfo.getAcc();
		this.dir = gpsInfo.getDir();
		this.prov = gpsInfo.getProv();
		this.spd = gpsInfo.getSpd();
		setTimeFromDate(gpsInfo.getTime());
		this.battery = gpsInfo.getBattery();		

	}

	public GPSLog() {
	}

	@Override
	public String toString() {
		return "GPSInfo [lat=" + lat + ", lon=" + lon + ", sat=" + sat
				+ ", desc=" + desc + ", alt=" + alt + ", acc=" + acc + ", dir="
				+ dir + ", prov=" + prov + ", spd=" + spd + ", time=" + time
				+ ", battery=" + battery + ", androidId=" + androidId
				+ ", serial=" + serial + "]";
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
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
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
	public Date getTimeAsDate() throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'",
				Locale.US);
		sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
		return sdf.parse(time);
		
	}
	
	public String getTime() {
		return time;
	}
	
	public void setTime (String time) {
		this.time = time;
	}

	public String getBattery() {
		return battery;
	}
	public void setBattery(String battery) {
		this.battery = battery;
	}
	public String getAndroidId() {
		return androidId;
	}
	public void setAndroidId(String androidId) {
		this.androidId = androidId;
	}
	public String getSerial() {
		return serial;
	}
	public void setSerial(String serial) {
		this.serial = serial;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((acc == null) ? 0 : acc.hashCode());
		result = prime * result + ((alt == null) ? 0 : alt.hashCode());
		result = prime * result
				+ ((androidId == null) ? 0 : androidId.hashCode());
		result = prime * result + ((battery == null) ? 0 : battery.hashCode());
		result = prime * result + ((desc == null) ? 0 : desc.hashCode());
		result = prime * result + ((dir == null) ? 0 : dir.hashCode());
		result = prime * result + ((lat == null) ? 0 : lat.hashCode());
		result = prime * result + ((lon == null) ? 0 : lon.hashCode());
		result = prime * result + ((prov == null) ? 0 : prov.hashCode());
		result = prime * result + ((sat == null) ? 0 : sat.hashCode());
		result = prime * result + ((serial == null) ? 0 : serial.hashCode());
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
		GPSLog other = (GPSLog) obj;
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
		if (androidId == null) {
			if (other.androidId != null)
				return false;
		} else if (!androidId.equals(other.androidId))
			return false;
		if (battery == null) {
			if (other.battery != null)
				return false;
		} else if (!battery.equals(other.battery))
			return false;
		if (desc == null) {
			if (other.desc != null)
				return false;
		} else if (!desc.equals(other.desc))
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
		if (serial == null) {
			if (other.serial != null)
				return false;
		} else if (!serial.equals(other.serial))
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

	public void setTimeFromDate(Date d) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'",
				Locale.US);
		sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
		this.time = sdf.format(d);
	}
}
