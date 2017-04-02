package com.synwing.demo.db.slave.dao;

import org.springframework.stereotype.Repository;

import com.synwing.demo.common.GenericDao;
import com.synwing.demo.model.Userinfo;

@Repository("UserinfoDao")
public class UserinfoDao extends GenericDao<Userinfo>{
// 根据网友建议实也可以在dao中直接注入 sessionFactory 像这样
/*    @Autowired
    @Qualifier("slaveSessionFactory")
   private SessionFactory sessionFactory;
    
    public Session getSession() {
		return sessionFactory.getCurrentSession();
	}*/
}
