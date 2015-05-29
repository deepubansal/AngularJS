package com.deepak.maps.seeme.dao;

import java.util.Date;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.deepak.maps.seeme.model.Device;
import com.deepak.maps.seeme.model.GPSInfo;

public interface GPSInfoDao extends JpaRepository<GPSInfo, Long>{

	public Set<GPSInfo> findByDeviceAndTimeBetween(Device device, Date fromDate, Date toDate);
	
	@Query("SELECT gpsInfo FROM GPSInfo gpsInfo WHERE gpsInfo.time = (SELECT MAX(gpsInfoInner.time) FROM GPSInfo gpsInfoInner WHERE gpsInfoInner.device = :device) and gpsInfo.device = :device")
	public GPSInfo findLatestGpsInfoByDevice(@Param("device") Device device);

}
