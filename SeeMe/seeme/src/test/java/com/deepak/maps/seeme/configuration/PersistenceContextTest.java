package com.deepak.maps.seeme.configuration;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.junit.Assert;
import org.junit.Test;

import com.deepak.maps.seeme.BaseTest;

public class PersistenceContextTest extends BaseTest {

	@PersistenceContext
	EntityManager entityManager;

	@SuppressWarnings("rawtypes")
	@Test
	public void testEntityManagerFactory() {
		Query query = entityManager.createNativeQuery("select curtime() from dual");
		List list = query.getResultList();
		String currentTimeAsStringActual = list.get(0).toString();
		DateFormat df = new SimpleDateFormat("HH:mm:ss");
		String currentTimeAsStringExpected = df.format(new Date());
		Assert.assertEquals(currentTimeAsStringExpected, currentTimeAsStringActual);
	}
	
}
