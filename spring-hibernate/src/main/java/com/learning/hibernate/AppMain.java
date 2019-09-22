package com.learning.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

public class AppMain {

	static Session sessionObj;
	static SessionFactory sessionFactoryObj;

	// This Method Is Used To Create The Hibernate's SessionFactory Object
	private static SessionFactory buildSessionFactory() {
		// Creating Configuration Instance & Passing Hibernate Configuration File
		Configuration configObj = new Configuration();
		configObj.configure("hibernate.cfg.xml");

		// Since Hibernate Version 4.x, ServiceRegistry Is Being Used
		ServiceRegistry serviceRegistryObj = new StandardServiceRegistryBuilder()
				.applySettings(configObj.getProperties()).build();

		// Creating Hibernate SessionFactory Instance
		sessionFactoryObj = configObj.buildSessionFactory(serviceRegistryObj);
		return sessionFactoryObj;
	}

	// This Method Is Used To Display The Records From The Database Table (i.e.
	// Assuming The Records Are Already Present In The Database Table)
	public static void displayRecords() {
		Employee empObj;

		// Opening The Hibernate's Session Object To Start The Database Transaction
		sessionObj = buildSessionFactory().openSession();

		// Load The Employee Details Whose Emp Id is '2'
		int emp_id1 = 2;
		empObj = (Employee) sessionObj.load(Employee.class, new Integer(emp_id1));
		if (empObj != null) {
			System.out.println(empObj.toString());
		}

		empObj = (Employee) sessionObj.get(Employee.class, new Integer(emp_id1));
		if (empObj != null) {
			System.out.println(empObj.toString());
		}

		// Load The Employee Details Whose Emp Id is '10'. This Will Throw The
		// 'ObjectNotFoundException' As Record Doesn't Exist In The Database
		int emp_id2 = 10;
		empObj = (Employee) sessionObj.load(Employee.class, new Integer(emp_id2));
		if (empObj != null) {
			System.out.println(empObj.toString());
		}
		sessionFactoryObj.close();
	}

	public static void main(String[] args) {
		displayRecords();
	}
}
