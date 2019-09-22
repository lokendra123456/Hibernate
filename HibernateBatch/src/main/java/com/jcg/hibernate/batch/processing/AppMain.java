package com.jcg.hibernate.batch.processing;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

@SuppressWarnings("unchecked")
public class AppMain {

	static Session sessionObj;
	static SessionFactory sessionFactoryObj;

	private static SessionFactory buildSessionFactory() {
		// Creating Configuration Instance & Passing Hibernate Configuration File
		Configuration configObj = new Configuration();
		configObj.configure("hibernate.cfg.xml");

		// Since Hibernate Version 4.x, ServiceRegistry Is Being Used
		ServiceRegistry serviceRegistryObj = new StandardServiceRegistryBuilder().applySettings(configObj.getProperties()).build(); 

		// Creating Hibernate SessionFactory Instance
		sessionFactoryObj = configObj.buildSessionFactory(serviceRegistryObj);
		return sessionFactoryObj;
	}

	public static void main(String[] args) {
		System.out.println(".......Hibernate Batch Processing Example.......\n");
		try {
			sessionObj = buildSessionFactory().openSession();
			sessionObj.beginTransaction();

			int batchSize = 30, totalRecords = 100;
			// - - - - - - - - - - - - - - Hibernate/JPA Batch Insert Example - - - - - - - - - - - - //
			for (int i = 0; i < totalRecords; i++) {
				Product product = new Product("Product " + i);
				sessionObj.save(product);
				if (i % batchSize == 0 && i > 0) {
					// Flush A Batch Of Inserts & Release Memory
					sessionObj.flush();
					sessionObj.clear();
				}
			}
			System.out.println("\n.......Records Saved Successfully To The Database.......\n");

			//  - - - - - - - - - - - - - - Hibernate/JPA Batch Update Example - - - - - - - - - - - - //
			String sqlQuery = "FROM Product";
			List<Product> productList = sessionObj.createQuery(sqlQuery).list();
			for (int j = 0; j < productList.size(); j++) {
				Product projectObj = productList.get(j);
				projectObj.setProductCode("New Product " + j);				
				sessionObj.update(projectObj);
				if (j % batchSize == 0 && j > 0) {
					// Flush A Batch Of Updates & Release Memory
					sessionObj.flush();
					sessionObj.clear();
				}
			}
			System.out.println("\n.......Records Updated Successfully In The Database.......\n");

			// Committing The Transactions To The Database
			sessionObj.getTransaction().commit();
		} catch(Exception sqlException) {
			if(null != sessionObj.getTransaction()) {
				System.out.println("\n.......Transaction Is Being Rolled Back.......");
				sessionObj.getTransaction().rollback();
			}
			sqlException.printStackTrace();
		} finally {
			if(sessionObj != null) {
				sessionObj.close();
			}
		}
	}
}