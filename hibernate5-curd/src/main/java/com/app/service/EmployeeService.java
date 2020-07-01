package com.app.service;

import java.util.List;

import com.app.model.Employee;

public interface EmployeeService {

	public void createEmployee();

	public List<Employee> findAll();

	public Employee findOne();

	public void deleteEmployeeById();

	public void updateEmployee();

}
