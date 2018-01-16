package com.sheefee.simple.sso.server.service;

import com.sheefee.simple.sso.server.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;


import com.sheefee.simple.sso.server.dao.UserDao;

@Service("userService")
public class UserServiceImpl implements UserService {
	@Autowired
	private UserDao userDao;
	@Value("${db.table}")
	private String table;
	@Value("${db.table.userid}")
	private String userid;
	@Value("${db.table.username}")
	private String username;
	@Value("${db.table.password}")
	private String password;

	/**
	 * 1.改为不从db中获取
	 * @param user
	 * @return
	 */
	public User find(User user) {
//		return new User("1","sheefee","123456");
		return userDao.find(user, table, userid, username, password);
	}
}