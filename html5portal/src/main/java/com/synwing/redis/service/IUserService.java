package com.synwing.redis.service;

import java.util.List;

import com.synwing.redis.entity.User;

public interface IUserService {
	public boolean add(List<User> list);

	public boolean add(User user);

	public void delete(String key);

	public boolean update(User user);

	public User get(String keyId);

}
