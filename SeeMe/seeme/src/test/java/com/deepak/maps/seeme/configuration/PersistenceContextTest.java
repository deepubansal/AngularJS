package com.deepak.maps.seeme.configuration;

import static org.junit.Assert.assertEquals;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.junit.Test;

import com.deepak.maps.seeme.BaseTest;

public class PersistenceContextTest extends BaseTest {

	@PersistenceContext
	EntityManager entityManager;

	@SuppressWarnings("rawtypes")
	@Test
	public void testEntityManagerFactory() throws ParseException {
		Query query = entityManager.createNativeQuery("select UTC_TIMESTAMP() from dual");
		List list = query.getResultList();
		String currentTimeAsStringActual = list.get(0).toString();
		String withoutSeconds = currentTimeAsStringActual.split(":[^:]*:[^:]*$")[0];
		String expected = new SimpleDateFormat("YYYY-MM-dd HH").format(new Date());
		assertEquals(expected, withoutSeconds);
	}
}
