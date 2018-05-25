package com.qibill;

import org.junit.Test;

import com.qibill.pojo.ContentItem;

public class XMLTest {

	@Test
	public void xmlTOString() {
		ContentItem contentItem = new ContentItem();
		contentItem.setBGDH("2");
		contentItem.setCKZ("2");
		contentItem.setJLCKZDW("2");
		System.out.println(contentItem.toXml());
	}
}
