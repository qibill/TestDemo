package com.biosan.dao;

import java.sql.Connection;

import com.biosan.pojo.Patient;

public interface PatientDao {

	Patient getPatientByIdentitycard(String identitycard, Connection connection);
}
