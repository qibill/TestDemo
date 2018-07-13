package net.biosan.net.service.impl;

import java.util.List;

import net.biosan.net.service.ImportData;

public class ImportDataHD extends ImportData {

	public void editKeylist(){
		//取得父类keylist
		List<String> keylist = getKeylist();
		//是否免费 1.免费 2.收费
		keylist.add("isfree");
	}
}
