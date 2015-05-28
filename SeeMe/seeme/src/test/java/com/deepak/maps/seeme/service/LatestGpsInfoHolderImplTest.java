package com.deepak.maps.seeme.service;

import java.util.Date;

import org.junit.Assert;
import org.junit.Test;

import com.deepak.maps.seeme.domain.GPSLog;

public class LatestGpsInfoHolderImplTest {

	LatestGpsInfoHolder gpsInfoHolder = new LatestGpsInfoHolderImpl();
	@Test
	public void shouldUpdateTheLatestGpsInfo() {
		GPSLog gpsLog  =  new GPSLog();
		gpsLog.setTimeFromDate(new Date(new Date().getTime() - 1000));
		Double initialAltitude = Math.random();
		gpsLog.setAlt(initialAltitude);
		gpsInfoHolder.updateIfNew(gpsLog, 1l);
		GPSLog latest = gpsInfoHolder.fetchLatest(1l);
		Assert.assertEquals(initialAltitude, latest.getAlt());
		GPSLog gpsLog2  =  new GPSLog();
		gpsLog2.setTimeFromDate(new Date(new Date().getTime() + 1000));
		Double finallAltitude = Math.random();
		gpsLog2.setAlt(finallAltitude);
	}
	

}
