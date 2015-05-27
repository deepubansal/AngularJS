package com.deepak.maps.seeme.service;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.deepak.maps.seeme.dao.DeviceDao;
import com.deepak.maps.seeme.dao.GPSInfoDao;
import com.deepak.maps.seeme.domain.GPSLog;
import com.deepak.maps.seeme.model.Device;
import com.deepak.maps.seeme.model.GPSInfo;

@Component
public class GpsLogServiceImpl implements GpsLogService {

	private static Logger logger = LoggerFactory.getLogger(GpsLogServiceImpl.class);

	@Autowired
	private DeviceDao deviceDao;

	@Autowired
	private GPSInfoDao gpsInfoDao;

	@Override
	@Transactional
	public void storeGPSLog(GPSLog gpsLog) {
		logger.info("Inside GpsLogServiceImpl");
		GPSInfo gpsInfo;
		try {
			gpsInfo = new GPSInfo(gpsLog);
		} catch (ParseException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		Device device = deviceDao.findOneByAndroidId(gpsLog.getAndroidId());
		if (device == null) {
			device = new Device();
			device.setAndroidId(gpsLog.getAndroidId());
			device.setSerialNo(gpsLog.getSerial());
			device = deviceDao.save(device);
		}
		gpsInfo.setDevice(device);
		gpsInfoDao.save(gpsInfo);
	}

	@Override
	public List<GPSLog> getLogsByInterval(Long fromTime, Long toTime) {
		Set<GPSInfo> gpsInfos = gpsInfoDao.findByTimeBetween(new Date(fromTime), new Date(toTime));
		
		List<GPSLog> gpsLogs = new ArrayList<GPSLog>(gpsInfos.size());
		for (Iterator<GPSInfo> itr = gpsInfos.iterator(); itr.hasNext();) {
			GPSInfo gpsInfo = itr.next();
			gpsLogs.add(new GPSLog(gpsInfo));
		}
		Collections.sort(gpsLogs, new Comparator<GPSLog>() {
			@Override
			public int compare(GPSLog o1, GPSLog o2) {
				try {
					return o1.getTimeAsDate().compareTo(o2.getTimeAsDate());
				} catch (ParseException e) {
					e.printStackTrace();
					return 0;
				}
			}
		});
		return gpsLogs;
	}

}
