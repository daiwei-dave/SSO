package com.sheefee.simple.sso.server.domain;

import java.io.Serializable;

/**
 * 用户
 * 
 * @author sheefee
 * @date 2017年9月17日 下午2:47:27
 *
 */
public class User implements Serializable{
	// id
	private String id;
	// 名称
	private String username;
	// 密码
	private String password;

	private String token;

	public User() {
	}

	public User(String id, String username, String password) {
		this.id = id;
		this.username = username;
		this.password = password;
	}


	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
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




}