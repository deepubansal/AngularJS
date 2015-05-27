package com.deepak.maps.seeme.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.deepak.maps.seeme.domain.GPSLog;
import com.deepak.maps.seeme.service.GpsLogService;

@RestController
@RequestMapping("/gpslog")
public class GPSLogController {
	
	private static Logger logger = LoggerFactory.getLogger(GPSLogController.class);
	@Autowired
	private GpsLogService gpsLogService;
	
	@RequestMapping(produces="application/json", value="/store")
	public Boolean  storeGPS(GPSLog gpsLog) {
		logger.debug("Received GPSLog: " + gpsLog);
		gpsLogService.storeGPSLog(gpsLog);
		return true;
	}
	
	@RequestMapping(produces="application/json", value="/get")
	public List<GPSLog> getGPS(Long fromTime, Long toTime) {
		return gpsLogService.getLogsByInterval(fromTime, toTime);
	}
	
}
