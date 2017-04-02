package com.synwing.redis.service.impl;

import java.util.List;

import com.synwing.redis.dao.impl.UserDao;
import com.synwing.redis.entity.User;
import com.synwing.redis.service.IUserService;

public class UserService implements IUserService {

	private UserDao userDao;

	public UserDao getUserDao() {
		return userDao;
	}

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	@Override
	public boolean add(List<User> list) {
		return userDao.add(list);
	}

	@Override
	public boolean add(User user) {
		return userDao.add(user);
	}

	@Override
	public void delete(String key) {
		userDao.delete(key);
	}

	@Override
	public boolean update(User user) {
		return userDao.update(user);
	}

	@Override
	public User get(String keyId) {
		return userDao.get(keyId);
	}

}
