package com.synwing.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.synwing.demo.db.master.dao.UsersDao;
import com.synwing.demo.model.Userinfo;
import com.synwing.demo.model.Users;

@Service("UsersService")
@Transactional(value="masterTransactionManager")
public class UsersService {
    
    @Autowired
    @Qualifier("UsersDao")
    private UsersDao usersDao;
    
    public int findUserAgeById(int id){
	Users users = (Users) usersDao.getSession().get(Users.class,id);
	return users.age;
    }
    
    public void saveUsers(Users users){
    	usersDao.getSession().persist(users);
    }
    
    public void deleteUserInfo(Users users){
    	usersDao.getSession().delete(users);
    }
}
