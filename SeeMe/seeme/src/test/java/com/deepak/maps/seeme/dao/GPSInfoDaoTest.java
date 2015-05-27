package com.deepak.maps.seeme.dao;

import static org.junit.Assert.assertEquals;

import java.util.Date;
import java.util.Set;

import javax.transaction.Transactional;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.deepak.maps.seeme.BaseTest;
import com.deepak.maps.seeme.model.GPSInfo;

public class GPSInfoDaoTest extends BaseTest {

	@Autowired
	GPSInfoDao gpsInfoDao;

	@Test
	@Transactional
	public void testFindByTimeRange() {
		Long fromTime = new Date().getTime() - 1000;
		Long toTime = new Date().getTime() + 1000;
		GPSInfo gpsInfo = new GPSInfo();
		gpsInfo.setLat(new Double((int) Math.random() * 1000));
		gpsInfo.setTime(new Date());
		gpsInfoDao.save(gpsInfo);
		Set<GPSInfo> gpsFound = gpsInfoDao.findByTimeBetween(new Date(fromTime), new Date(toTime));
		assertEquals(1, gpsFound.size());
		assertEquals(gpsInfo, gpsFound.iterator().next());
	}

}
