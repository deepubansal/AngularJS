package com.deepak.maps.seeme.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.deepak.maps.seeme.model.Device;

public interface DeviceDao extends JpaRepository<Device, Integer>{

	public Device findOneByAndroidId(String androidId);
}
