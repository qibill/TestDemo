package net.biosan.net.dao.impl;

import java.sql.SQLException;

import net.biosan.net.dao.HosDao;

import com.biosan.utils.BiosanUtils;

public class HosDaoimpl implements HosDao{

	public Object selectNewHos(String hoscode_old){
		String select_newhos = "select HOSCODE_NEW || '|' || HOSNAME_NEW from ground_tb_department_compare where HOSCODE_OLD = '" + hoscode_old + "'";
		Object newhosObj;
		try {
			newhosObj = BiosanUtils.getSingle(select_newhos);
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		return newhosObj;
	}
}
