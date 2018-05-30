package com.qibill;

import java.util.List;

import org.junit.Test;

import com.biosan.dao.PatientDao;
import com.biosan.dao.impl.PatientDaoImpl;
import com.biosan.pojo.Patient;

public class DaoTest {

	@Test
	public void patientDaoTest() {
		PatientDao dao = new PatientDaoImpl();
		List<Patient> patients = dao.getPatientByPatientid(78408, null);
		if (patients.size() == 0) {
			System.out.println("么有数据");
		} else {
			System.out.println(patients.get(0));
		}
	}
}
