package com.deepak.maps.seeme.service;

import java.text.ParseException;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.deepak.maps.seeme.dao.DeviceDao;
import com.deepak.maps.seeme.dao.GPSInfoDao;
import com.deepak.maps.seeme.domain.GPSLog;
import com.deepak.maps.seeme.model.Device;
import com.deepak.maps.seeme.model.GPSInfo;

public class GpsLogServiceTest {

	@InjectMocks
	GpsLogServiceImpl gpsLogService;

	@Mock
	DeviceDao deviceDao;
	
	@Mock
	GPSInfoDao  gpsInfoDao;
	
	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void testStoreGPSLogTest() throws ParseException {
		GPSLog gpsLog = new GPSLog();
		gpsLog.setTimeFromDate(new Date());
		Device device = new Device();
		Mockito.when(deviceDao.findOneByAndroidId(null)).thenReturn(device);
		gpsLogService.storeGPSLog(gpsLog);
		GPSInfo gpsInfo = new GPSInfo();
		gpsInfo.setTime(gpsLog.getTimeAsDate());
		gpsInfo.setDevice(device);
		Mockito.verify(gpsInfoDao, Mockito.times(1)).save(Mockito.eq(gpsInfo));
	}
}
