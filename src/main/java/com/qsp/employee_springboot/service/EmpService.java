package com.qsp.employee_springboot.service;

import java.util.List;
import java.util.Optional;

import javax.naming.NameNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.qsp.employee_springboot.dao.EmployeeDao;
import com.qsp.employee_springboot.dto.Employee;
import com.qsp.employee_springboot.exception.EmailNotFoundException;
import com.qsp.employee_springboot.exception.IdNotFoundException;
import com.qsp.employee_springboot.exception.NoDataAvailableException;
import com.qsp.employee_springboot.exception.PhoneNotFoundException;
import com.qsp.employee_springboot.util.ResponseStructure;

@Service
public class EmpService {
	@Autowired
	private EmployeeDao dao;

	public ResponseEntity<ResponseStructure<Employee>> saveEmployee(Employee employee) {

		double salary = employee.getSalary();

		if (salary <= 10000) {

			employee.setGrade('D');
		} else if (salary > 10000 && salary <= 20000) {

			employee.setGrade('C');
		} else if (salary > 20000 && salary <= 40000) {

			employee.setGrade('B');
		} else {
			employee.setGrade('A');
		}

		ResponseStructure<Employee> structure = new ResponseStructure<Employee>();
		structure.setStatus(HttpStatus.CREATED.value());
		structure.setMessage("Saved successful");
		structure.setData(dao.saveEmployee(employee));
		return new ResponseEntity<ResponseStructure<Employee>>(structure, HttpStatus.OK);
	}

	public ResponseEntity<ResponseStructure<List<Employee>>> saveEmployees(List<Employee> list) {

		for (Employee employee : list) {

			double salary = employee.getSalary();

			if (salary <= 10000) {

				employee.setGrade('D');
			} else if (salary > 10000 && salary <= 20000) {

				employee.setGrade('C');
			} else if (salary > 20000 && salary <= 40000) {

				employee.setGrade('B');
			} else {
				employee.setGrade('A');
			}

		}
		List<Employee> employees = list;

		ResponseStructure<List<Employee>> structure = new ResponseStructure<List<Employee>>();

		structure.setStatus(HttpStatus.CREATED.value());
		structure.setMessage("Found successful");
		structure.setData(employees);
		return new ResponseEntity<ResponseStructure<List<Employee>>>(structure,HttpStatus.CREATED);

	}

	public ResponseEntity<ResponseStructure<Employee>> findById(int id) {

		Employee employee = dao.findById(id);
		ResponseStructure<Employee> structure = new ResponseStructure<Employee>();
		if (employee != null) {

			structure.setStatus(HttpStatus.FOUND.value());
			structure.setMessage("Found successful");
			structure.setData(employee);
			return new ResponseEntity<ResponseStructure<Employee>>(structure,HttpStatus.FOUND) ;
		} else {
//			structure.setStatus(HttpStatus.NOT_FOUND.value());
//			structure.setMessage("Not Found");
//			structure.setData(employee);
//			return new ResponseEntity<ResponseStructure<Employee>>(structure,HttpStatus.NOT_FOUND);
			throw new IdNotFoundException("Employee with id"+id+"not found");
		}
	}

	public ResponseEntity<ResponseStructure<List<Employee>>> findByName(String name) throws NameNotFoundException{

		List<Employee> employees = dao.findByName(name);

		ResponseStructure<List<Employee>> structure = new ResponseStructure<List<Employee>>();

		if (!employees.isEmpty()) {
			structure.setStatus(HttpStatus.FOUND.value());
			structure.setMessage("Found successful");
			structure.setData(employees);
			return new ResponseEntity<ResponseStructure<List<Employee>>>(structure,HttpStatus.FOUND);
		} else {
//			structure.setStatus(HttpStatus.NOT_FOUND.value());
//			structure.setMessage("Not Found");
//			structure.setData(employees);
//			return new ResponseEntity<ResponseStructure<List<Employee>>>(structure,HttpStatus.NOT_FOUND);
			throw new NameNotFoundException("Employee with name"+ name +"not found");
		}

	}

	public ResponseEntity<ResponseStructure<Employee>> findByPhone(long phone) {
		Employee employee = dao.findByPhone(phone);

		ResponseStructure<Employee> structure = new ResponseStructure<Employee>();

		if (employee != null) {
			structure.setStatus(HttpStatus.FOUND.value());
			structure.setMessage("Found successful");
			structure.setData(employee);
			return new ResponseEntity<ResponseStructure<Employee>>(structure,HttpStatus.FOUND);
		} else {
//			structure.setStatus(HttpStatus.NOT_FOUND.value());
//			structure.setMessage("Not Found");
//			structure.setData(employee);
//			return new ResponseEntity<ResponseStructure<Employee>>(structure,HttpStatus.NOT_FOUND);
			throw new PhoneNotFoundException("Employee with phone"+ phone +"not found");
		}
	}

	public ResponseEntity<ResponseStructure<Employee>> findByEmail(String email) {
		Employee employee = dao.findByEmail(email);

		ResponseStructure<Employee> structure = new ResponseStructure<Employee>();

		if (employee != null) {
			structure.setStatus(HttpStatus.FOUND.value());
			structure.setMessage("Found successful");
			structure.setData(employee);
			return new ResponseEntity<ResponseStructure<Employee>>(structure,HttpStatus.FOUND);
		} else {
//			structure.setStatus(HttpStatus.NOT_FOUND.value());
//			structure.setMessage("Not Found");
//			structure.setData(employee);
//			return new ResponseEntity<ResponseStructure<Employee>>(structure,HttpStatus.NOT_FOUND);
			throw new EmailNotFoundException("Employee with email"+ email +"not found");
		}
	}

	public ResponseEntity<ResponseStructure<List<Employee>>> findByAddress(String address) {
		List<Employee> employee = dao.findByAddress(address);

		ResponseStructure<List<Employee>>structure = new ResponseStructure<List<Employee>>();

		if (employee != null) {
			structure.setStatus(HttpStatus.FOUND.value());
			structure.setMessage("Found successful");
			structure.setData(employee);
			return new ResponseEntity<ResponseStructure<List<Employee>>>(structure,HttpStatus.FOUND);
		} else {
			structure.setStatus(HttpStatus.NOT_FOUND.value());
			structure.setMessage("Not Found");
			structure.setData(employee);
			return new ResponseEntity<ResponseStructure<List<Employee>>>(structure,HttpStatus.NOT_FOUND);
		}
	}

	public ResponseEntity<ResponseStructure<List<Employee>>> findByDesignation(String designation) {
		List<Employee> employee = dao.findByDesignation(designation);

		ResponseStructure<List<Employee>>structure = new ResponseStructure<List<Employee>>();

		if (employee != null) {
			structure.setStatus(HttpStatus.FOUND.value());
			structure.setMessage("Found successful");
			structure.setData(employee);
			return new ResponseEntity<ResponseStructure<List<Employee>>>(structure,HttpStatus.FOUND);
		} else {
			structure.setStatus(HttpStatus.NOT_FOUND.value());
			structure.setMessage("Not Found");
			structure.setData(employee);
			return new ResponseEntity<ResponseStructure<List<Employee>>>(structure,HttpStatus.NOT_FOUND);
		}
	}

	public ResponseEntity<ResponseStructure<List<Employee>>> findAll() {
		List<Employee> employees = dao.findAll();

		ResponseStructure<List<Employee>> structure = new ResponseStructure<List<Employee>>();

		if (!employees.isEmpty()) {
			structure.setStatus(HttpStatus.FOUND.value());
			structure.setMessage("Found successful");
			structure.setData(employees);
			return new ResponseEntity<ResponseStructure<List<Employee>>>(structure,HttpStatus.FOUND);
		} else {
			structure.setStatus(HttpStatus.NOT_FOUND.value());
			structure.setMessage("Not Found");
			structure.setData(employees);
			return new ResponseEntity<ResponseStructure<List<Employee>>>(structure,HttpStatus.NOT_FOUND);
			
		}

	}

	public ResponseEntity<ResponseStructure<List<Employee>>> salaryLessThan(double salary) {
		List<Employee> employees = dao.salaryLessThan(salary);

		ResponseStructure<List<Employee>> structure = new ResponseStructure<List<Employee>>();

		if (!employees.isEmpty()) {
			structure.setStatus(HttpStatus.FOUND.value());
			structure.setMessage("Found successful");
			structure.setData(employees);
			return new ResponseEntity<ResponseStructure<List<Employee>>>(structure,HttpStatus.FOUND);
		} else {
			structure.setStatus(HttpStatus.NOT_FOUND.value());
			structure.setMessage("Not Found");
			structure.setData(employees);
			return new ResponseEntity<ResponseStructure<List<Employee>>>(structure,HttpStatus.NOT_FOUND);
		}
	}

	public ResponseEntity<ResponseStructure<List<Employee>>> salaryGreaterThan(double salary) {
		List<Employee> employees = dao.salaryGreaterThan(salary);

		ResponseStructure<List<Employee>> structure = new ResponseStructure<List<Employee>>();

		if (!employees.isEmpty()) {
			structure.setStatus(HttpStatus.FOUND.value());
			structure.setMessage("Found successful");
			structure.setData(employees);
			return new ResponseEntity<ResponseStructure<List<Employee>>>(structure,HttpStatus.FOUND);
		} else {
			structure.setStatus(HttpStatus.NOT_FOUND.value());
			structure.setMessage("Not Found");
			structure.setData(employees);
			return new ResponseEntity<ResponseStructure<List<Employee>>>(structure,HttpStatus.NOT_FOUND);
		}
	}

	public ResponseEntity<ResponseStructure<List<Employee>>> salaryBetween(double minSalary, double maxSalary) {
		List<Employee> employees = dao.salaryBetween(minSalary, maxSalary);

		ResponseStructure<List<Employee>> structure = new ResponseStructure<List<Employee>>();

		if (!employees.isEmpty()) {
			structure.setStatus(HttpStatus.FOUND.value());
			structure.setMessage("Found successful");
			structure.setData(employees);
			return new ResponseEntity<ResponseStructure<List<Employee>>>(structure,HttpStatus.NOT_FOUND);
		} else {
			structure.setStatus(HttpStatus.NOT_FOUND.value());
			structure.setMessage("Not Found");
			structure.setData(employees);
			return new ResponseEntity<ResponseStructure<List<Employee>>>(structure,HttpStatus.NOT_FOUND);
		}
	}

	public ResponseEntity<ResponseStructure<Employee>> deleteById(int id) {
		
		Employee  employee =dao.deleteById(id);
		ResponseStructure<Employee> structure=new ResponseStructure<Employee>();
		if (employee!=null) {
			structure.setStatus(HttpStatus.OK.value());
			structure.setMessage("delete successful");
			structure.setData(employee);
			return new ResponseEntity<ResponseStructure<Employee>>(structure,HttpStatus.OK);
		} else {
			structure.setStatus(HttpStatus.NOT_FOUND.value());
			structure.setMessage("id not found");
			structure.setData(employee);
			return new ResponseEntity<ResponseStructure<Employee>>(structure,HttpStatus.NOT_FOUND);
		}	
		}

	public ResponseEntity<ResponseStructure<Employee>> deleteByPhone(long phone) {

		Employee  employee =dao.deleteByPhone(phone);
		ResponseStructure<Employee> structure=new ResponseStructure<Employee>();
		if (employee!=null) {
			structure.setStatus(HttpStatus.OK.value());
			structure.setMessage("delete successful");
			structure.setData(employee);
			return new ResponseEntity<ResponseStructure<Employee>>(structure,HttpStatus.OK);
		} else {
			structure.setStatus(HttpStatus.NOT_FOUND.value());
			structure.setMessage("id not found");
			structure.setData(employee);
			return new ResponseEntity<ResponseStructure<Employee>>(structure,HttpStatus.NOT_FOUND);
		}
	}

	public ResponseEntity<ResponseStructure<Employee>> deleteByEmail(String email) {

		Employee  employee =dao.deleteByEmail(email);
		ResponseStructure<Employee> structure=new ResponseStructure<Employee>();
		if (employee!=null) {
			structure.setStatus(HttpStatus.OK.value());
			structure.setMessage("delete successful");
			structure.setData(employee);
			return new ResponseEntity<ResponseStructure<Employee>>(structure,HttpStatus.OK);
		} else {
			structure.setStatus(HttpStatus.NOT_FOUND.value());
			structure.setMessage("id not found");
			structure.setData(employee);
			return new ResponseEntity<ResponseStructure<Employee>>(structure,HttpStatus.NOT_FOUND);
		}	
	}

	public ResponseEntity<ResponseStructure<List<Employee>>> deleteByDesignation(String designation) {

		List<Employee> employee =dao.deleteByDesignation(designation);
		ResponseStructure<List<Employee>> structure=new ResponseStructure<List<Employee>>();
		if (employee!=null) {
			structure.setStatus(HttpStatus.OK.value());
			structure.setMessage("delete successful");
			structure.setData(employee);
			return new ResponseEntity<ResponseStructure<List<Employee>>>(structure,HttpStatus.OK);
		} else {
			structure.setStatus(HttpStatus.NOT_FOUND.value());
			structure.setMessage("id not found");
			structure.setData(employee);
			return new ResponseEntity<ResponseStructure<List<Employee>>>(structure,HttpStatus.NOT_FOUND);
		}	
	}

	public ResponseEntity<ResponseStructure<List<Employee>>> deleteByName(String name) {

		List<Employee> employee =dao.deleteByName(name);
		ResponseStructure<List<Employee>> structure=new ResponseStructure<List<Employee>>();
		if (employee!=null) {
			structure.setStatus(HttpStatus.OK.value());
			structure.setMessage("delete successful");
			structure.setData(employee);
			return new ResponseEntity<ResponseStructure<List<Employee>>>(structure,HttpStatus.OK);
		} else {
			structure.setStatus(HttpStatus.NOT_FOUND.value());
			structure.setMessage("id not found");
			structure.setData(employee);
			return new ResponseEntity<ResponseStructure<List<Employee>>>(structure,HttpStatus.NOT_FOUND);
		}	

	}

	public ResponseEntity<ResponseStructure<List<Employee>>> deleteByAddress(String address) {

		List<Employee> employee =dao.deleteByAddress(address);
		ResponseStructure<List<Employee>> structure=new ResponseStructure<List<Employee>>();
		if (employee!=null) {
			structure.setStatus(HttpStatus.OK.value());
			structure.setMessage("delete successful");
			structure.setData(employee);
			return new ResponseEntity<ResponseStructure<List<Employee>>>(structure,HttpStatus.OK);
		} else {
			structure.setStatus(HttpStatus.NOT_FOUND.value());
			structure.setMessage("id not found");
			structure.setData(employee);
			return new ResponseEntity<ResponseStructure<List<Employee>>>(structure,HttpStatus.NOT_FOUND);
		}	

	}

	public ResponseEntity<ResponseStructure<Employee>> updateEmployee(Employee employee) {

		Employee employee2 =  dao.updateEmployee(employee);
		ResponseStructure<Employee> structure=new ResponseStructure<Employee>();
		structure.setStatus(HttpStatus.OK.value());
		structure.setMessage("update successful");
		structure.setData(employee2);
		return new ResponseEntity<ResponseStructure<Employee>>(structure,HttpStatus.OK);
	}

	public ResponseEntity<ResponseStructure<Employee>> updatePhone(int id, long phone) {

		Employee employee =  dao.updatePhone(id, phone);
		ResponseStructure<Employee> structure=new ResponseStructure<Employee>();
		structure.setStatus(HttpStatus.OK.value());
		structure.setMessage("update successful");
		structure.setData(employee);
		return new ResponseEntity<ResponseStructure<Employee>>(structure,HttpStatus.OK);
	}

	public ResponseEntity<ResponseStructure<Employee>> updateEmail(int id, String email) {

		 Employee employee = dao.updateEmail(id, email);
		 ResponseStructure<Employee> structure=new ResponseStructure<Employee>();
			structure.setStatus(HttpStatus.OK.value());
			structure.setMessage("update successful");
			structure.setData(employee);
			return new ResponseEntity<ResponseStructure<Employee>>(structure,HttpStatus.OK);
	}

	public ResponseEntity<ResponseStructure<Employee>> updateSalary(int id, double salary) {

		Employee employee = dao.updateSalary(id, salary);
		 ResponseStructure<Employee> structure=new ResponseStructure<Employee>();
			structure.setStatus(HttpStatus.OK.value());
			structure.setMessage("update successful");
			structure.setData(employee);
			return new ResponseEntity<ResponseStructure<Employee>>(structure,HttpStatus.OK);
	}

	public ResponseEntity<ResponseStructure<Employee>> updateDesignation(int id, String designation) {

		Employee employee = dao.updateDesignation(id, designation);
		 ResponseStructure<Employee> structure=new ResponseStructure<Employee>();
			structure.setStatus(HttpStatus.OK.value());
			structure.setMessage("update successful");
			structure.setData(employee);
			return new ResponseEntity<ResponseStructure<Employee>>(structure,HttpStatus.OK);
	}

	public ResponseEntity<ResponseStructure<Employee>> updateName(int id, String name) {

		Employee employee = dao.updateName(id, name);
		 ResponseStructure<Employee> structure=new ResponseStructure<Employee>();
			structure.setStatus(HttpStatus.OK.value());
			structure.setMessage("update successful");
			structure.setData(employee);
			return new ResponseEntity<ResponseStructure<Employee>>(structure,HttpStatus.OK);
	}

	public ResponseEntity<ResponseStructure<Employee>> updatePassword(int id, String passoward) {

		Employee employee = dao.updatePassword(id, passoward);
		 ResponseStructure<Employee> structure=new ResponseStructure<Employee>();
			structure.setStatus(HttpStatus.OK.value());
			structure.setMessage("update successful");
			structure.setData(employee);
			return new ResponseEntity<ResponseStructure<Employee>>(structure,HttpStatus.OK);
	}

	public ResponseEntity<ResponseStructure<Employee>> updatePassword(String email, String passoward) {

		Employee employee= dao.updatePassword(email, passoward);
		 ResponseStructure<Employee> structure=new ResponseStructure<Employee>();
			structure.setStatus(HttpStatus.OK.value());
			structure.setMessage("update successful");
			structure.setData(employee);
			return new ResponseEntity<ResponseStructure<Employee>>(structure,HttpStatus.OK);
	}

	public ResponseEntity<ResponseStructure<Employee>> updatePassword(long phone, String passoward) {

		Employee employee= dao.updatePassword(phone, passoward);
		 ResponseStructure<Employee> structure=new ResponseStructure<Employee>();
			structure.setStatus(HttpStatus.OK.value());
			structure.setMessage("update successful");
			structure.setData(employee);
			return new ResponseEntity<ResponseStructure<Employee>>(structure,HttpStatus.OK);
	}

	public ResponseEntity<ResponseStructure<Employee>> getEmployee(String username) {

		Employee employee= dao.getEmployee(username);
		 ResponseStructure<Employee> structure=new ResponseStructure<Employee>();
			structure.setStatus(HttpStatus.OK.value());
			structure.setMessage("update successful");
			structure.setData(employee);
		//	return new ResponseEntity<ResponseStructure<Employee>>(structure,HttpStatus.OK);
			throw new NoDataAvailableException("Data not found");
	}
}