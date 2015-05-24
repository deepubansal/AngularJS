package com.deepak.maps.seeme.service;

import java.util.ArrayList;

import javax.transaction.Transactional;

import com.deepak.maps.seeme.domain.GPSLog;

public interface GpsLogService {

	public abstract void storeGPSLog(GPSLog gpsLog);

	public abstract ArrayList<GPSLog> getLogsByInterval(Long fromTime, Long toTime);

}