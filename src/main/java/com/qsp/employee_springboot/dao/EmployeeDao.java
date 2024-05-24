package com.qsp.employee_springboot.dao;


import java.util.List;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;

import com.qsp.employee_springboot.dto.Employee;
import com.qsp.employee_springboot.exception.IdNotFoundException;
import com.qsp.employee_springboot.repo.EmpRepo;
import com.qsp.employee_springboot.util.ResponseStructure;



@Component
public class EmployeeDao {
	@Autowired
	private EmpRepo repo;

	public Employee saveEmployee(Employee employee) {
		return repo.save(employee);
	}

	public List<Employee> saveEmployees(List<Employee> list) {
		return repo.saveAll(list);
	}

	public Employee getEmployee(String username) {
		try {
			long phone = Long.parseLong(username);
			return findByPhone(phone);
		} catch (NumberFormatException e) {
			return findByEmail(username);
		}
	}

	public Employee findById(int id)  {
		Optional<Employee> optional = repo.findById(id);
		
		if (optional.isPresent()) {
			return optional.get();
		} 
			else {
			return null;
		}
	
	
	}

	public List<Employee> findByName(String name) {
		return repo.findByName(name);
	}

	public Employee findByPhone(long phone) {
		return repo.findByPhone(phone);
	}

	public Employee findByEmail(String email) {
		return repo.findByEmail(email);
	}

	public List<Employee> findByAddress(String address) {
		return repo.findByAddress(address);
	}

	public List<Employee> findByDesignation(String designation) {
		return repo.findByDesignation(designation);
	}

	public List<Employee> findAll() {
		return repo.findAll();
	}

	public List<Employee> salaryLessThan(double salary) {
		return repo.findBySalaryLessThan(salary);
	}

	public List<Employee> salaryGreaterThan(double salary) {
		return repo.findBySalaryGreaterThan(salary);
	}

	public List<Employee> salaryBetween(double minSalary, double maxSalary) {
		return repo.findBySalaryBetween(minSalary, maxSalary);
	}

	public Employee deleteById(int id) {
		Optional<Employee> optional = repo.findById(id);

		if (optional.isPresent()) {
			repo.delete(optional.get());
			return optional.get();
		} else {
			return null;
		}

	}

	public Employee deleteByPhone(long phone) {
		Employee optional = repo.findByPhone(phone);

		if (optional != null) {
			repo.delete(optional);
			return optional;
		} else {
			return optional;
		}

	}

	public Employee deleteByEmail(String email) {
		Employee optional = repo.findByEmail(email);

		if (optional != null) {
			repo.delete(optional);
			return optional;
		} else {
			return optional;
		}
	}

	public List<Employee> deleteByDesignation(String designation) {
		List<Employee> optional = repo.findByDesignation(designation);

		if (optional != null) {
			repo.deleteAll();
			return optional;
		} else {
			return optional;
		}
	}

	public List<Employee> deleteByName(String name) {
		List<Employee> optional = repo.findByName(name);

		if (optional != null) {
			repo.deleteAll();
			return optional;
		} else {
			return optional;
		}
	}

	public List<Employee> deleteByAddress(String address) {
		List<Employee> optional = repo.findByName(address);

		if (optional != null) {
			repo.deleteAll();
			return optional;
		} else {
			return optional;
		}
	}

	public List<Employee> deleteEmp() {

		List<Employee> employees = repo.findAll();

		if (!employees.isEmpty()) {

			repo.deleteAll();
			return employees;
		} else {
			return employees;
		}
	}

	public Employee updateEmployee(Employee employee) {

		return repo.save(employee);
	}

	public Employee updatePhone(int id, long phone) {

		Optional<Employee> optional = repo.findById(id);

		if (optional.isPresent()) {

			Employee employee = optional.get();

			employee.setPhone(phone);

			return repo.save(employee);

		} else {
			return null;
		}
	}

	public Employee updateEmail(int id, String email) {

		Optional<Employee> optional = repo.findById(id);

		if (optional.isPresent()) {

			Employee employee = optional.get();

			employee.setEmail(email);

			return repo.save(employee);

		} else {
			return null;
		}
	}

	public Employee updateSalary(int id, double salary) {

		Optional<Employee> optional = repo.findById(id);

		if (optional.isPresent()) {

			Employee employee = optional.get();

			employee.setSalary(salary);

			return repo.save(employee);

		} else {
			return null;
		}
	}

	public Employee updateDesignation(int id, String designation) {
		Optional<Employee> optional = repo.findById(id);

		if (optional.isPresent()) {

			Employee employee = optional.get();

			employee.setDesignation(designation);

			return repo.save(employee);

		} else {
			return null;
		}
	}

	public Employee updateName(int id, String name) {

		Optional<Employee> optional = repo.findById(id);

		if (optional.isPresent()) {

			Employee employee = optional.get();

			employee.setName(name);
			;

			return repo.save(employee);

		} else {
			return null;
		}
	}

	public Employee updatePassword(int id, String passoward) {

		Optional<Employee> optional = repo.findById(id);

		if (optional.isPresent()) {

			Employee employee = optional.get();

			employee.setPassword(passoward);
			;

			return repo.save(employee);

		} else {
			return null;
		}
	}

	public Employee updatePassword(String email, String passoward) {

		Employee optional = repo.findByEmail(email);

		if (optional != null) {

			optional.setPassword(passoward);

			return repo.save(optional);

		} else {
			return null;
		}
	}

	public Employee updatePassword(long phone, String passoward) {

		Employee optional = repo.findByPhone(phone);

		if (optional != null) {

			optional.setPassword(passoward);

			return repo.save(optional);

		} else {
			return null;
		}
	}
	
}