package com.bean;

import java.sql.Timestamp;
import java.util.Date;

import org.codehaus.jackson.map.annotate.JsonSerialize;

import com.util.CustomerDateSerializer;

public class User {
	private Integer id;
	private String name;
	private Integer age;
	private String address;
	private Date birthday;
	private Timestamp born;
	
	public User() {
		super();
	}
	public User(Integer id, String name, Integer age, String address,
			Date birthday) {
		super();
		this.id = id;
		this.name = name;
		this.age = age;
		this.address = address;
		this.birthday = birthday;
	}
	public User(String name, Integer age, String address,
			Date birthday) {
		super();
		this.name = name;
		this.age = age;
		this.address = address;
		this.birthday = birthday;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	
//	@JsonSerialize(using=CustomerDateSerializer.class)
	public Timestamp getBorn() {
		return born;
	}
	public void setBorn(Timestamp born) {
		this.born = born;
	}
	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", age=" + age
				+ ", address=" + address + ", birthday=" + birthday + ", born="
				+ born + "]";
	}
	
	public static void main(String[] args) {
		System.out.println("ending...");
		int num = 1;
		System.out.println(num);
		for(int i=0;i<10;i++){
			System.out.println(i);
		}
		System.out.println("ending...");
	}
}
