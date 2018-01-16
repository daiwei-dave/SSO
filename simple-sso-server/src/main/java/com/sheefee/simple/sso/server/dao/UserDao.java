package com.sheefee.simple.sso.server.dao;

import com.sheefee.simple.sso.server.domain.User;
import org.apache.ibatis.annotations.Param;


public interface UserDao {
	public User find(@Param("user") User user, @Param("table") String table, @Param("userid") String userid,
					 @Param("username") String username, @Param("password") String password);

	public void updateUser(@Param("user") User user, @Param("table") String table, @Param("userid") String userid,
			   @Param("username") String username, @Param("password") String password);
	User findByToken(String token);
}