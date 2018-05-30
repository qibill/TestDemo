package com.biosan.dao;

import java.sql.Connection;
import java.util.List;

import com.biosan.pojo.Patient;

public interface PatientDao {

	List<Patient> getPatientByPatientid(Integer patientid, Connection connection);
}
