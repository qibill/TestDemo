package com.qibill;

import org.junit.Test;

import com.biosan.pojo.Patient;

public class ReflectTest {

	@Test
	@SuppressWarnings("unchecked")
	public void test() {
		Patient patient = new Patient();
		Class<Patient> clazz = (Class<Patient>) patient.getClass();
		try {
			System.out.println(clazz.getDeclaredField("patientids"));
		} catch (Exception e) {
			System.out.println("there is not");
			e.printStackTrace();
		}
	}
}
