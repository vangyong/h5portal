package com.synwing.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.synwing.demo.db.slave.dao.UserinfoDao;
import com.synwing.demo.model.Userinfo;

@Service("UserinfoService")
@Transactional(value="slaveTransactionManager")
public class UserinfoService {

    @Autowired
    @Qualifier("UserinfoDao")
    private UserinfoDao userinfoDao;
    
    public String findUserInfoById(int id){
	Userinfo userinfo = (Userinfo) userinfoDao.getSession().get(Userinfo.class,id);
	return userinfo.info;
    }
    
    public void saveUserInfo(Userinfo userinfo){
    	userinfoDao.getSession().persist(userinfo);
    }
    
    public void deleteUserInfo(Userinfo userinfo){
    	userinfoDao.getSession().delete(userinfo);
    }
    
    
}
