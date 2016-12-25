package com.hotusm.hibernate;

import java.util.UUID;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.junit.Before;
import org.junit.Test;

import com.hotusm.hibernate.entity.Demo;
import com.hotusm.hibernate.entity.Many;

public class HibernateUnit {

	private SessionFactory sessionFactory;
	
	@Before
	public void before(){
		sessionFactory=new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
	}
	@Test
	public void saveEntity(){
		Session session = sessionFactory.openSession();
		
		Transaction tnx = session.getTransaction();
		tnx.begin();
		
		try {
			Demo demo=new Demo();
			demo.setId(IDProvider());
			demo.setMemo("mark");
			
			Many many=new Many();
			many.setDesc("1211");
			many.setDemo(demo);
			
			session.save(demo);
			session.save(many);
		} catch (Exception e) {
			tnx.rollback();
		}

		tnx.commit();
		session.close();
	}
	@Test
	public void after(){
		sessionFactory.close();
	}
	
	public static String IDProvider(){
		return UUID.randomUUID().toString().replace("-", "");
	}
}
