package com.synwing.redis.dao;



import java.util.List;

import com.synwing.redis.entity.User;


/** 
 * @author http://blog.csdn.net/java2000_wl 
 * @version <b>1.0</b> 
 */ 
public interface IUserDao {
	
	/**
	 * 新增
	 * <br>------------------------------<br>
	 * @param user
	 * @return
	 */
	boolean add(User user);
	
	/**
	 * 批量新增 使用pipeline方式
	 * <br>------------------------------<br>
	 * @param list
	 * @return
	 */
	boolean add(List<User> list);
	
	/**
	 * 删除
	 * <br>------------------------------<br>
	 * @param key
	 */
	void delete(String key);
	
	/**
	 * 删除多个
	 * <br>------------------------------<br>
	 * @param keys
	 */
	void delete(List<String> keys);
	
	/**
	 * 修改
	 * <br>------------------------------<br>
	 * @param user
	 * @return 
	 */
	boolean update(User user);

	/**
	 * 通过key获取
	 * <br>------------------------------<br>
	 * @param keyId
	 * @return 
	 */
	User get(String keyId);
}