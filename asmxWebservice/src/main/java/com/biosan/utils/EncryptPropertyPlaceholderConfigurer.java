package com.biosan.utils;

import java.util.List;

import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;

public class EncryptPropertyPlaceholderConfigurer extends PropertyPlaceholderConfigurer {

	private List<String> encryptPropNames;

	@Override
	protected String convertProperty(String propertyName, String propertyValue) {

		if (encryptPropNames.contains(propertyName)) {
			return DESUtil.decrypt(propertyValue, "Bio-San#17");
		}

		return super.convertProperty(propertyName, propertyValue);
	}

	public List<String> getEncryptPropNames() {
		return encryptPropNames;
	}

	public void setEncryptPropNames(List<String> encryptPropNames) {
		this.encryptPropNames = encryptPropNames;
	}
}
