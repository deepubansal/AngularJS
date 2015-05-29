package com.deepak.maps.seeme.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.Date;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.deepak.maps.seeme.DataMother;
import com.deepak.maps.seeme.domain.GPSLog;
import com.deepak.maps.seeme.domain.GetGpsInfoRequest;
import com.deepak.maps.seeme.service.GpsLogService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:seeme-test-servlet.xml", "classpath:seeme-servlet.xml" })
@WebAppConfiguration
public class GPSLogControllerTest {

	protected MockMvc mockMvc;

	@Autowired
	private WebApplicationContext webApplicationContext;
	
	@Autowired
	private GpsLogService gpsLogService;

	private DataMother dataMother;
	
	@Before
	public void setUp() {
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
		Mockito.reset(gpsLogService);
		dataMother = new DataMother();
	}
	
	@Test
	public void shouldStoreLatLongCorrectly() throws Exception {
		GPSLog gpsLog = new GPSLog();
		gpsLog.setLat(12.193);
		gpsLog.setLon(19.111);
		gpsLog.setSat(41);
		gpsLog.setDesc("Description");
		gpsLog.setAlt(9001d);
		gpsLog.setAcc("55");
		gpsLog.setDir("na");
		gpsLog.setProv("na");
		gpsLog.setSpd("10");
		gpsLog.setTimeFromDate(new Date());
		gpsLog.setBattery("44");
		gpsLog.setAndroidId("androidid");
		gpsLog.setSerial("serial"); 
		mockMvc.perform(
				get("/gpslog/store?lat={LAT}&lon={LON}&sat={SAT}&desc={DESC}&alt={ALT}&acc={ACC}&"
						+ "dir={DIR}&prov={PROV}&spd={SPD}&time={TIME}&"
						+ "battery={BATT}&androidId={AID}&serial={SER}",
					    gpsLog.getLat(),
					    gpsLog.getLon(),
					    gpsLog.getSat(),
					    gpsLog.getDesc(),
					    gpsLog.getAlt(),
					    gpsLog.getAcc(),
					    gpsLog.getDir(),
					    gpsLog.getProv(),
					    gpsLog.getSpd(),
					    "2015-05-26T10:42:26Z",
					    gpsLog.getBattery(),
					    gpsLog.getAndroidId(),
					    gpsLog.getSerial()))
				.andDo(MockMvcResultHandlers.print())
				.andExpect(status().isOk())
				.andExpect(content().string("true"));
	}

	@Test
	public void shouldReturnLatLongCorrectly() throws Exception {
		GPSLog gpsLog = new GPSLog();
		gpsLog.setTimeFromDate(new Date());
		Long fromTime = new Date().getTime();
		Long toTime = new Date().getTime();
		ArrayList<GPSLog> gpsLogs = new ArrayList<GPSLog>();
		gpsLogs.add(gpsLog);
		GetGpsInfoRequest getGpsInfoRequest = dataMother.createGetGpsInfoReqeust(fromTime, toTime);
		Mockito.when(gpsLogService.getLogs(Mockito.eq(getGpsInfoRequest))).thenReturn(gpsLogs);
		mockMvc.perform(
				get("/gpslog/get?fromTime={TIME}&"
						+ "toTime={BATT}&deviceId={DEVID}",
						fromTime,
						toTime,
						getGpsInfoRequest.getDeviceId()).contentType(MediaType.APPLICATION_JSON))
						.andExpect(status().isOk())
						.andDo(MockMvcResultHandlers.print());
		ArgumentCaptor<GetGpsInfoRequest>  argument = ArgumentCaptor.forClass(GetGpsInfoRequest.class);
		
		Mockito.verify(gpsLogService, Mockito.times(1)).getLogs(argument.capture());
		Assert.assertEquals(getGpsInfoRequest.getDeviceId(),argument.getValue().getDeviceId());
	}
	
	@After
	public void validate() {
	    Mockito.validateMockitoUsage();
	}

}
