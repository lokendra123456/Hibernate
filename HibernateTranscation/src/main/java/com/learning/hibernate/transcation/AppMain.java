package com.learning.hibernate.transcation;

public class AppMain {

	public static void main(String[] args) {
		HibernateUtil.createRecord();

		HibernateUtil.updateRecord();

		System.exit(0);
	}
}
