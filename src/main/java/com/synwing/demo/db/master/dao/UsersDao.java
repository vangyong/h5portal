package com.synwing.demo.db.master.dao;

import org.springframework.stereotype.Repository;

import com.synwing.demo.common.GenericDao;
import com.synwing.demo.model.Users;

@Repository("UsersDao")
public class UsersDao extends GenericDao<Users>{
//根据网友建议其实也可以在dao中直接注入 sessionFactory 像这样
/*  @Autowired
  @Qualifier("masterSessionFactory")
 private SessionFactory sessionFactory;
  
  public Session getSession() {
		return sessionFactory.getCurrentSession();
	}*/
}
