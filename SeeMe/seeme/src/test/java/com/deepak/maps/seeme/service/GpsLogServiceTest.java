package com.deepak.maps.seeme.service;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Assert;
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
	@Test
	public void testGetGPSLogs() throws ParseException {
		Long fromTime = new Date().getTime() - 1000;
		Long toTime = new Date().getTime() + 1000;
		GPSLog gpsLog1 = new GPSLog();
		gpsLog1.setAcc("2.3");
		gpsLog1.setTimeFromDate(new Date(fromTime));
		GPSLog gpsLog2 = new GPSLog();
		gpsLog2.setAcc("1.3");
		gpsLog2.setTimeFromDate(new Date(toTime));
		List<GPSLog> gpsLogs = new ArrayList<GPSLog>(2);
		gpsLogs.add(gpsLog1);
		gpsLogs.add(gpsLog2);
		Set<GPSInfo> gpsInfos = new HashSet<GPSInfo>();
		gpsInfos.add(new GPSInfo(gpsLog2));
		gpsInfos.add(new GPSInfo(gpsLog1));
		Mockito.when(gpsInfoDao.findByTimeBetween(Mockito.eq(new Date(fromTime)), Mockito.eq(new Date(toTime)))).thenReturn(gpsInfos);
		List<GPSLog> logsByInterval = gpsLogService.getLogsByInterval(fromTime, toTime);
		Mockito.verify(gpsInfoDao, Mockito.times(1)).findByTimeBetween(Mockito.eq(new Date(fromTime)), Mockito.eq(new Date(toTime)));
		Assert.assertEquals(gpsLogs, logsByInterval);
	}
}
