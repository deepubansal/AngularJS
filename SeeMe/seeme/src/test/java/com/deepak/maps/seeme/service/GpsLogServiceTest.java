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
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.deepak.maps.seeme.DataMother;
import com.deepak.maps.seeme.dao.DeviceDao;
import com.deepak.maps.seeme.dao.GPSInfoDao;
import com.deepak.maps.seeme.domain.GPSLog;
import com.deepak.maps.seeme.domain.GetGpsInfoRequest;
import com.deepak.maps.seeme.model.Device;
import com.deepak.maps.seeme.model.GPSInfo;

public class GpsLogServiceTest {

	@InjectMocks
	GpsLogServiceImpl gpsLogService;

	@Mock
	LatestGpsInfoHolder latestGpsInfoHolder;
	
	@Mock
	DeviceDao deviceDao;
	
	@Mock
	GPSInfoDao  gpsInfoDao;
	
	
	DataMother dataMother;
	
	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
		dataMother = new DataMother();
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
		Mockito.verify(latestGpsInfoHolder, Mockito.times(1)).updateIfNew(Mockito.eq(gpsLog), Mockito.eq(device.getId()));
	}

	@Test
	public void testGetGPSLogsByInterval() throws ParseException {
		GetGpsInfoRequest gpsInfoRequest = dataMother.createGetGpsInfoReqeust(new Date().getTime() - 1000, new Date().getTime() + 1000);
		Long deviceId = gpsInfoRequest.getDeviceId();
		
		Device deviceFromRequest = new Device();
		deviceFromRequest.setId(deviceId);
		
		GPSLog gpsLog1 = dataMother.createGpsLog(gpsInfoRequest.getFromTime());
		GPSLog gpsLog2 = dataMother.createGpsLog(gpsInfoRequest.getToTime());
		
		List<GPSLog> gpsLogs = new ArrayList<GPSLog>(2);
		gpsLogs.add(gpsLog1);
		gpsLogs.add(gpsLog2);
		
		Set<GPSInfo> gpsInfos = new HashSet<GPSInfo>();
		gpsInfos.add(new GPSInfo(gpsLog2));
		gpsInfos.add(new GPSInfo(gpsLog1));
		
		
		Mockito.when(gpsInfoDao.findByDeviceAndTimeBetween(Mockito.any(Device.class), Mockito.eq(new Date(gpsInfoRequest.getFromTime())), Mockito.eq(new Date(gpsInfoRequest.getToTime())))).thenReturn(gpsInfos);
		
		List<GPSLog> logsByInterval = gpsLogService.getLogs(gpsInfoRequest);
		
		ArgumentCaptor<Device> argument = ArgumentCaptor.forClass(Device.class);
		Mockito.verify(gpsInfoDao, Mockito.times(1)).findByDeviceAndTimeBetween(argument.capture(), Mockito.eq(new Date(gpsInfoRequest.getFromTime())), Mockito.eq(new Date(gpsInfoRequest.getToTime())));
		Assert.assertEquals(deviceFromRequest.getId(), argument.getValue().getId());
		
		Assert.assertEquals(gpsLogs, logsByInterval);
	}

	@Test
	public void testGetGPSLogsLatest() throws ParseException {
		GetGpsInfoRequest gpsInfoRequest = new GetGpsInfoRequest((long) (Math.random()*10000)); 
		Long deviceId = gpsInfoRequest.getDeviceId();
		
		Device deviceFromRequest = new Device();
		deviceFromRequest.setId(deviceId);
		
		
		GPSLog gpsLog1 = dataMother.createGpsLog(new Date().getTime());
		
		Mockito.when(latestGpsInfoHolder.fetchLatest(Mockito.eq(gpsInfoRequest.getDeviceId()))).thenReturn(gpsLog1);
		
		List<GPSLog> logs = gpsLogService.getLogs(gpsInfoRequest);
		List<GPSLog> expectedLogs = new ArrayList<GPSLog>(1);
		expectedLogs.add(gpsLog1);
		
		Mockito.verify(gpsInfoDao, Mockito.times(0)).findByDeviceAndTimeBetween(Mockito.eq(deviceFromRequest), Mockito.any(Date.class), Mockito.any(Date.class));
		Mockito.verify(latestGpsInfoHolder, Mockito.times(1)).fetchLatest(Mockito.eq(gpsInfoRequest.getDeviceId()));
		Assert.assertEquals(expectedLogs, logs);
	}

	
	@Test
	public void testGetGPSLogsLatestWhenNotInCache() throws ParseException {
		GetGpsInfoRequest gpsInfoRequest = new GetGpsInfoRequest((long) (Math.random()*10000)); 
		Long deviceId = gpsInfoRequest.getDeviceId();
		
		Device deviceFromRequest = new Device();
		deviceFromRequest.setId(deviceId);
		GPSInfo gpsInfo = dataMother.createGpsInfo(new Date(), deviceFromRequest);

		List<GPSLog> expectedLogs = new ArrayList<GPSLog>(1);
		GPSLog gpsLog = new GPSLog(gpsInfo);
		expectedLogs.add(gpsLog);
		
		Mockito.when(gpsInfoDao.findLatestGpsInfoByDevice(Mockito.any(Device.class))).thenReturn(gpsInfo);
		Mockito.when(latestGpsInfoHolder.fetchLatest(Mockito.eq(gpsInfoRequest.getDeviceId()))).thenReturn(null);
		
		List<GPSLog> logs = gpsLogService.getLogs(gpsInfoRequest);
		ArgumentCaptor<Device> argument = ArgumentCaptor.forClass(Device.class);
		Mockito.verify(gpsInfoDao, Mockito.times(1)).findLatestGpsInfoByDevice(argument.capture());
		Assert.assertEquals(gpsInfoRequest.getDeviceId(), argument.getValue().getId());
		Mockito.verify(latestGpsInfoHolder, Mockito.times(1)).fetchLatest(Mockito.eq(gpsInfoRequest.getDeviceId()));
		Mockito.verify(latestGpsInfoHolder, Mockito.times(1)).updateIfNew(Mockito.eq(gpsLog), Mockito.eq(deviceId));
		Assert.assertEquals(expectedLogs, logs);
	}
	

}