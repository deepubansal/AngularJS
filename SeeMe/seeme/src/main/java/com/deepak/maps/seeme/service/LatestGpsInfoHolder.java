package com.deepak.maps.seeme.service;

import com.deepak.maps.seeme.domain.GPSLog;
/**
 * Interface to maintain the latest gpsinfo about all the devices.
 * 
 * @author deepak
 *
 */
public interface LatestGpsInfoHolder {
	
	public void updateIfNew(GPSLog gpsLog, Long deviceId);

	public GPSLog fetchLatest(Long deviceId);
}
