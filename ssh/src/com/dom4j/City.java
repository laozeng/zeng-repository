package com.dom4j;

import java.util.ArrayList;
import java.util.List;

//ʡ�µ��У�ֱϽ���е���
public class City extends Area {
	public List<Town> list = new ArrayList<Town>();

	public City(int id, String name, List<Town> list) {
		super(id, name);
		this.list = list;
	}

	public City(int id, String name) {
		super(id, name);
	}

	// @Override
	// public String toString() {
	// return "City [list=" + list + "]";
	// }
}