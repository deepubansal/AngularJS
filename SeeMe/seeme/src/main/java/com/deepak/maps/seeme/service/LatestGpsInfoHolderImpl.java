package com.deepak.maps.seeme.service;

import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Service;

import com.deepak.maps.seeme.domain.GPSLog;

@Service
public class LatestGpsInfoHolderImpl implements LatestGpsInfoHolder {

	ConcurrentHashMap<Long, GPSLog> latestGpsInfos = new ConcurrentHashMap<Long, GPSLog>();
	
	@Override
	public void updateIfNew(GPSLog gpsLog, Long deviceId) {
		GPSLog latest = fetchLatest(deviceId);
		if (latest ==  null || latest.getTimeAsDate().before(gpsLog.getTimeAsDate()))
			latestGpsInfos.put(deviceId, gpsLog);
	}

	@Override
	public GPSLog fetchLatest(Long deviceId) {
		return latestGpsInfos.get(deviceId);
	}

}
