package com.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.hibernate.demo.entity.Instructor;
import com.hibernate.demo.entity.InstructorDetail;

public class CreateDemo {

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
			//create object
			Instructor theInstructor = new Instructor("Payal", "Tikyani", "payal@abc.com");
			
			InstructorDetail theInstructorDetail = new InstructorDetail("http://www.abc.com/youtube", "Reading");
			
			//associate the objects
			theInstructor.setInstructorDetail(theInstructorDetail);

			//start a transaction
			session.beginTransaction();
			
			//save the instructor object
			//This will save the details object also because of CascadeType.ALL 
			System.out.println("Saving the instructor : " + theInstructor);
			session.save(theInstructor);
			
			//commit transaction
			session.getTransaction().commit();
			
		}
		finally {
			factory.close();
		}
	}

}
