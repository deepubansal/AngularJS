package com.deepak.maps.seeme.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.deepak.maps.seeme.domain.GPSLog;
import com.deepak.maps.seeme.service.GpsLogService;

@RestController
@RequestMapping("/gpslog")
public class GPSLogController {
	
	@Autowired
	GpsLogService gpsLogService;
	
	@RequestMapping(consumes="application/x-www-form-urlencoded", produces="application/json", value="/store")
	public Boolean  storeGPS(GPSLog gpsLog) {
		gpsLogService.storeGPSLog(gpsLog);
		return true;
	}
	
	@RequestMapping(consumes="application/json", produces="application/json", value="/get")
	public ArrayList<GPSLog> getGPS(Long fromTime, Long toTime) {
		return gpsLogService.getLogsByInterval(fromTime, toTime);
	}
	
}
