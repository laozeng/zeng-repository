package com.shiro.entity;

import static javax.persistence.GenerationType.IDENTITY;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name="user",catalog = "shiro")
public class User implements Serializable {
	@Id
	@GeneratedValue(strategy=IDENTITY)
	@Column(name="id", unique=true, nullable=false)
	private int id;
	private String username;
	private String password;
	private String description;
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name="user_role", joinColumns={@javax.persistence.JoinColumn(name="user_id")}, inverseJoinColumns={@javax.persistence.JoinColumn(name="role_id")})
	private List<Role> roles = new ArrayList<>();
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public List<Role> getRoles() {
		return roles;
	}
	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}
	public User(int id, String username, String password, String description) {
		this.id = id;
		this.username = username;
		this.password = password;
		this.description = description;
	}
	public User(String username, String password) {
		this.username = username;
		this.password = password;
	}
	public User() {
	}
	
}
