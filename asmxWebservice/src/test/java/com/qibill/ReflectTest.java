package com.qibill;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import org.junit.Test;

import com.biosan.pojo.Patient;
import com.biosan.utils.ResultSetMapper;

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

	@Test
	public void getTclass() {
		Foo<String> foo = new Foo<String>() {
		};
		// 在类的外部这样获取
		Type type = ((ParameterizedType) foo.getClass().getGenericSuperclass())
				.getActualTypeArguments()[0];
		System.out.println(type);
		// 在类的内部这样获取
		System.out.println(foo.getTClass());
	}

	abstract class Foo<T>{
	    public Class<T> getTClass()
	    {
	        Class<T> tClass = (Class<T>)((ParameterizedType)getClass().getGenericSuperclass()).getActualTypeArguments()[0];
	        return tClass;
	    }
	}
	
	@Test
	public void getTclass1() {
		ResultSetMapper<Patient> mapper = new ResultSetMapper<Patient>() {};
		// 在类的外部这样获取
/*		Type type = ((ParameterizedType) foo.getClass().getGenericSuperclass())
				.getActualTypeArguments()[0];
		System.out.println(type);*/
		// 在类的内部这样获取
/*		Class<Patient> clazz = mapper.getTClass();
		System.out.println(clazz);*/
	}
}
