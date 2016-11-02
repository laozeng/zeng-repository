package com.dom4j;

import java.util.ArrayList;
import java.util.List;

//省，直辖市，自治区一级别
public class Province extends Area {
	public List<City> list = new ArrayList<City>();

	public Province(int id, String name, List<City> list) {
		super(id, name);
		this.list = list;
	}

	public Province(int id, String name) {
		super(id, name);
	}

	// @Override
	// public String toString() {
	// return "Province [list=" + list + "]";
	// }
}
