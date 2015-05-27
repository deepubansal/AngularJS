package com.deepak.maps.seeme.dao;

import java.util.Date;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;

import com.deepak.maps.seeme.model.GPSInfo;

public interface GPSInfoDao extends JpaRepository<GPSInfo, Long>{

	public Set<GPSInfo> findByTimeBetween(Date fromDate, Date toDate);

}
