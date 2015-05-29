package com.deepak.maps.seeme.dao;

import static org.junit.Assert.*;

import java.util.Date;
import java.util.Set;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.deepak.maps.seeme.BaseTest;
import com.deepak.maps.seeme.DataMother;
import com.deepak.maps.seeme.model.Device;
import com.deepak.maps.seeme.model.GPSInfo;

public class GPSInfoDaoTest extends BaseTest {

	@Autowired
	GPSInfoDao gpsInfoDao;

	DataMother dataMother = new DataMother();
	
	
	
	@Test
	@Transactional
	public void testFindByTimeRange() {
		Long fromTime = new Date().getTime() - 1000;
		Long toTime = new Date().getTime() + 1000;
		
		GPSInfo gpsInfo1 = dataMother.createGpsInfo(new Date());

		
		GPSInfo gpsInfo2 = dataMother.createGpsInfo(new Date());
		
		
		gpsInfoDao.save(gpsInfo1);
		gpsInfoDao.save(gpsInfo2);

		Device deviceFromRequest1 = new Device();
		deviceFromRequest1.setId(gpsInfo1.getDevice().getId());
		
		Set<GPSInfo> gpsFound1 = gpsInfoDao.findByDeviceAndTimeBetween(deviceFromRequest1, new Date(fromTime), new Date(toTime));
		assertEquals(1, gpsFound1.size());
		assertEquals(gpsInfo1, gpsFound1.iterator().next());

		Device deviceFromRequest2 = new Device();
		deviceFromRequest2.setId(gpsInfo2.getDevice().getId());
		
		Set<GPSInfo> gpsFound2 = gpsInfoDao.findByDeviceAndTimeBetween(deviceFromRequest2, new Date(fromTime), new Date(toTime));
		assertEquals(1, gpsFound2.size());
		assertEquals(gpsInfo2, gpsFound2.iterator().next());
	}

	@Test
	@Transactional
	public void testFindLatestByDevice() {
		Device device1 = new Device();
		Device device2 = new Device();

		GPSInfo old1 = dataMother.createGpsInfo(new Date(new Date().getTime() - 10000), device1);
		GPSInfo old2 = dataMother.createGpsInfo(new Date(new Date().getTime() - 5000), device1);
		GPSInfo latestForDevice1 = dataMother.createGpsInfo(new Date(new Date().getTime()), device1);
		GPSInfo old1ForDevice2 = dataMother.createGpsInfo(new Date(new Date().getTime() - 10000), device2);
		GPSInfo old2ForDevice2 = dataMother.createGpsInfo(new Date(new Date().getTime() - 5000), device2);
		GPSInfo latestForDevice2 = dataMother.createGpsInfo(new Date(new Date().getTime()), device2);
		
		gpsInfoDao.save(old1);
		gpsInfoDao.save(old2);
		gpsInfoDao.save(latestForDevice1);
		gpsInfoDao.save(old1ForDevice2);
		gpsInfoDao.save(old2ForDevice2);
		gpsInfoDao.save(latestForDevice2);
		
		
		GPSInfo latestGpsInfoForDevice1 = gpsInfoDao.findLatestGpsInfoByDevice(device1);
		GPSInfo latestGpsInfoForDevice2 = gpsInfoDao.findLatestGpsInfoByDevice(device2);
		
		assertEquals(latestForDevice1.getLat(), latestGpsInfoForDevice1.getLat());
		assertEquals(latestForDevice2.getLat(), latestGpsInfoForDevice2.getLat());
		
		
	}
}
