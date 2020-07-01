package com.app.service;

import java.util.List;
import java.util.Scanner;

import com.app.model.Employee;

public class Client {

	public static void main(String[] args) {

		EmployeeService employeeService = new EmployeeServiceImpl();

		while (true) {
			Scanner sc = new Scanner(System.in);
			System.out.println("1] Create Employee");
			System.out.println("2] Show Employees");
			System.out.println("3] Update Employee");
			System.out.println("4] Delete Employee");
			System.out.println("5] Find One Employees");
			System.out.println("Enter Your Options ");
			int option = sc.nextInt();

			switch (option) {
			case 1:
				employeeService.createEmployee();
				break;
			case 2:
				List<Employee> list = employeeService.findAll();
				for (Employee employee : list) {
					System.out.println(employee.getEmpId() + "\t" + employee.getEmpName());
				}
				break;
			case 3:
				employeeService.updateEmployee();
				System.out.println("Employee Update Successfuly");
				break;
			case 4:
				employeeService.deleteEmployeeById();
				System.out.println("Employee Delete Successfuly");
				break;
			case 5:
				Employee employee = employeeService.findOne();
				System.out.println(employee.getEmpId() + "\t" + employee.getEmpName());
				break;
			default:
				System.exit(0);
				break;
			}
		}

	}

}
