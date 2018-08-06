package com.wantao.test;

import java.util.Date;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.MetadataSources;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.junit.Test;

import com.wantao.entity.Student;

/*
 *@author:wantao
 *@time:Jul 29, 2018 2:20:59 PM
 *@description:
 */
public class HibernateTest {
	@Test
	public void test() {
		// 1.创建一个SessionFactory工厂类:通过它与数据库连接对话session
		SessionFactory sessionFactory = null;
		Configuration configuration = new Configuration().configure();
		// hibernate规定,所有的配置和服务要生效,必须把服务和配置注册到一个服务注册类
		ServiceRegistry serviceRegistry = configuration.getStandardServiceRegistryBuilder().build();
		sessionFactory = new MetadataSources(serviceRegistry).buildMetadata().buildSessionFactory();
		// 2.通过sessionFactory获取session
		Session session = sessionFactory.openSession();
		// 3.开启事务处理
		Transaction transaction = session.beginTransaction();
		// 4.执行数据库操作
		// 面向对象的方式操作数据
		Student student = new Student("万涛", "男", new Date());
		session.save(student);
		// 5.提交事务(不然不会执行)
		transaction.commit();
		// 6.关闭session
		session.close();
		// 7.关闭工厂
		sessionFactory.close();
	}

}
