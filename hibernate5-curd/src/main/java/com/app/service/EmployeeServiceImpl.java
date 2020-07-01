package com.app.service;

import java.util.List;
import java.util.Scanner;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.app.model.Employee;
import com.app.utility.HibernateUtils;

public class EmployeeServiceImpl implements EmployeeService {

	@SuppressWarnings("unchecked")
	public List<Employee> findAll() {
		List<Employee> employeeList = null;
		SessionFactory sessionFactory = HibernateUtils.getSessionFactory();
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		try {
			CriteriaQuery<Employee> cq = session.getCriteriaBuilder().createQuery(Employee.class);
			cq.from(Employee.class);
			employeeList = session.createQuery(cq).getResultList();

			// Commit data.
			tx.commit();
		} catch (Exception e) {
			e.printStackTrace();
			// Rollback in case of an error occurred.
			tx.rollback();
		}
		return employeeList;
	}

	public Employee findOne() {

		Scanner sc = new Scanner(System.in);
		System.out.println("Enter Id which you want to find Employee");
		int id = sc.nextInt();

		Employee employee = null;
		SessionFactory sessionFactory = HibernateUtils.getSessionFactory();
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		try {
			CriteriaBuilder builder = session.getCriteriaBuilder();
			CriteriaQuery<Employee> cq = builder.createQuery(Employee.class);
			Root<Employee> root = cq.from(Employee.class);
			cq.select(root);
			cq.where(builder.equal(root.get("empId"), id));
			employee = session.createQuery(cq).uniqueResult();
			tx.commit();
		} catch (Exception e) {
			e.printStackTrace();
			// Rollback in case of an error occurred.
			tx.rollback();
		}
		return employee;
	}

	public void deleteEmployeeById() {
		Employee employee = null;
		Scanner sc = new Scanner(System.in);
		SessionFactory sessionFactory = HibernateUtils.getSessionFactory();
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		try {
			employee = findOne();
			session.delete(employee);
			tx.commit();
		} catch (Exception e) {
			e.printStackTrace();
			// Rollback in case of an error occurred.
			tx.rollback();
		}
	}

	public void updateEmployee() {
		Scanner sc = new Scanner(System.in);
		Employee employee = new Employee();

		SessionFactory sessionFactory = HibernateUtils.getSessionFactory();
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		try {
			employee = findOne();
			System.out.println("Enter Name");
			employee.setEmpName(sc.next());
			session.update(employee);
			tx.commit();
		} catch (Exception e) {
			e.printStackTrace();
			// Rollback in case of an error occurred.
			tx.rollback();
		}
	}

	public void createEmployee() {
		Scanner sc = new Scanner(System.in);
		Employee employee = new Employee();
		System.out.println("Enter Name");
		employee.setEmpName(sc.next());

		SessionFactory sessionFactory = HibernateUtils.getSessionFactory();
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		try {
			session.save(employee);
			tx.commit();
		} catch (Exception e) {
			e.printStackTrace();
			// Rollback in case of an error occurred.
			tx.rollback();
		}
	}

}
