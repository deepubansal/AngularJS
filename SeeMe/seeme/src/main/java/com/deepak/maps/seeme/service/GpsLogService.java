package com.deepak.maps.seeme.service;

import java.util.List;

import com.deepak.maps.seeme.domain.GPSLog;
import com.deepak.maps.seeme.domain.GetGpsInfoRequest;

public interface GpsLogService {

	public abstract void storeGPSLog(GPSLog gpsLog);

	public abstract List<GPSLog> getLogs(GetGpsInfoRequest getGpsInfoRequest);
}