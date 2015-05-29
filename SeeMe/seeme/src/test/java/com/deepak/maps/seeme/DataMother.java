package com.deepak.maps.seeme;

import java.util.Date;

import com.deepak.maps.seeme.domain.GPSLog;
import com.deepak.maps.seeme.domain.GetGpsInfoRequest;
import com.deepak.maps.seeme.model.Device;
import com.deepak.maps.seeme.model.GPSInfo;

public class DataMother {
	
	public GPSLog createGpsLog(Long time) {
		GPSLog gpsLog1 = new GPSLog();
		gpsLog1.setAcc("2.3");
		gpsLog1.setSerial("s1");
		gpsLog1.setTimeFromDate(new Date(time));
		return gpsLog1;
	}

	public GetGpsInfoRequest createGetGpsInfoReqeust(long fromTime, long toTime) {
		Long deviceId = (long) (Math.random()*10000);
		GetGpsInfoRequest gpsInfoRequest= new GetGpsInfoRequest(fromTime, toTime, deviceId);
		return gpsInfoRequest;
	}

	
	public GPSInfo createGpsInfo(Date time) {
		Device device1 = new Device();
		return createGpsInfo(time, device1);
	}

	public GPSInfo createGpsInfo(Date time, Device device) {
		GPSInfo gpsInfo1 = new GPSInfo();
		gpsInfo1.setLat(Math.random() * 1000);
		gpsInfo1.setTime(time);
		gpsInfo1.setDevice(device);
		return gpsInfo1;
	}

}
