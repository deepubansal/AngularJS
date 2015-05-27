package com.deepak.maps.seeme.service;

import java.util.List;

import com.deepak.maps.seeme.domain.GPSLog;

public interface GpsLogService {

	public abstract void storeGPSLog(GPSLog gpsLog);

	public abstract List<GPSLog> getLogsByInterval(Long fromTime, Long toTime);

}