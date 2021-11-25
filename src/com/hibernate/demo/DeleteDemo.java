package com.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.hibernate.demo.entity.Instructor;
import com.hibernate.demo.entity.InstructorDetail;

public class DeleteDemo {

	public static void main(String[] args) {
		//create session factory
		SessionFactory factory = new Configuration()
				                 .configure("hibernate.cfg.xml")
				                 .addAnnotatedClass(Instructor.class)
				                 .addAnnotatedClass(InstructorDetail.class)
				                 .buildSessionFactory();	
		
		//create session
		Session session = factory.getCurrentSession();
		
		//use the session object to save Java Object
		try {
			//start a transaction
			session.beginTransaction();
			
			//get instructor by primary key/id
			int theId=1;
			
			Instructor theInstructor = session.get(Instructor.class, theId);
			
			System.out.println("Found Instructor : " + theInstructor);
			
			if(theInstructor!=null) {
				System.out.println("Deleting : " + theInstructor);
				
				//It will also delete the detail object because of CascadeType.ALL
				session.delete(theInstructor);
			}
			
			//commit transaction
			session.getTransaction().commit();
			
		}
		finally {
			factory.close();
		}
	}

}
