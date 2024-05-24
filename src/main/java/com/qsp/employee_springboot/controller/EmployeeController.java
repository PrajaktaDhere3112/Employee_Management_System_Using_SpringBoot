package com.qsp.employee_springboot.controller;


	import java.util.List;
	import java.util.Optional;

import javax.naming.NameNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
	import org.springframework.web.bind.annotation.RequestMapping;
	import org.springframework.web.bind.annotation.RequestParam;
	import org.springframework.web.bind.annotation.RestController;

import com.qsp.employee_springboot.dao.EmployeeDao;
import com.qsp.employee_springboot.dto.Employee;
import com.qsp.employee_springboot.service.EmpService;
import com.qsp.employee_springboot.util.ResponseStructure;

	@RestController
	@RequestMapping("/employee")
	public class EmployeeController {
		
		 @Autowired
		    private EmployeeDao dao;
		    
		    @Autowired
		    private EmpService service;

		    @PostMapping
		    public ResponseEntity<ResponseStructure<Employee>> saveEmployee(@RequestBody Employee employee) {
		        return service.saveEmployee(employee);
		    }

		    @PostMapping("/save/all")
		    public ResponseEntity<ResponseStructure<List<Employee>>> saveEmployees(@RequestBody List<Employee> list) {
		        return service.saveEmployees(list);
		    }

		    @GetMapping("/login")
		    public String loginEmployee(@RequestParam String username, @RequestParam String password) {
		        Employee employee = dao.getEmployee(username);

		        if (employee != null) {
		            if (password.equals(employee.getPassword())) {
		                return "Login Successful!!";
		            } else {
		                return "Invalid username or password!!";
		            }
		        } else {
		            return "Employee not found!!";
		        }
		    }

		    @GetMapping("/find/id")
		    public ResponseEntity<ResponseStructure<Employee>> findById(@RequestParam int id) {
		        return service.findById(id);
		    }

		    @GetMapping("/find/name")
		    public ResponseEntity<ResponseStructure<List<Employee>>> findByName(@RequestParam String name) throws NameNotFoundException {
		        return service.findByName(name);
		    }

		    @GetMapping("/find/phone")
		    public ResponseEntity<ResponseStructure<Employee>> findByPhone(@RequestParam long phone) {
		        return service.findByPhone(phone);
		    }

		    @GetMapping("/find")
		    public ResponseEntity<ResponseStructure<Employee>> findByEmail(@RequestParam String email) {
		        return service.findByEmail(email);
		    }

		    @GetMapping("/find/address")
		    public ResponseEntity<ResponseStructure<List<Employee>>> findByAddress(@RequestParam String address) {
		        return service.findByAddress(address);
		    }
		    
		    @GetMapping("/find/designation")
		    public ResponseEntity<ResponseStructure<List<Employee>>> findByDesignation(@RequestParam String designation) {
		        return service.findByDesignation(designation);
		    }

		    @GetMapping("/find/all")
		    public ResponseEntity<ResponseStructure<List<Employee>>> findAll() {
		        return service.findAll();
		    }

		    @GetMapping("/salary/less")
		    public ResponseEntity<ResponseStructure<List<Employee>>> salaryLessThan(@RequestParam double salary) {
		        return service.salaryLessThan(salary);
		    }

		    @GetMapping("/salary/greater")
		    public ResponseEntity<ResponseStructure<List<Employee>>> salaryGreaterThan(@RequestParam double salary) {
		        return service.salaryGreaterThan(salary);
		    }

		    @GetMapping("/salary/between")
		    public ResponseEntity<ResponseStructure<List<Employee>>> salaryBetween(@RequestParam double minSalary, @RequestParam double maxSalary) {
		        return service.salaryBetween(minSalary, maxSalary);
		    }
		    
		    @DeleteMapping("/delete/id")
		    public ResponseEntity<ResponseStructure<Employee>> deleteById(@RequestParam int id) {
				
		    	return service.deleteById(id);
			}
		    
		    @DeleteMapping("/delete/phone")   //not working
		    public ResponseEntity<ResponseStructure<Employee>> deleteByPhone(@RequestParam long phone) {
				
		    	return service.deleteByPhone(phone);
			}
		    @DeleteMapping("/delete/email")   //delete not working
		    public ResponseEntity<ResponseStructure<Employee>> deleteByEmail(@RequestParam String email) {
				
		    	return service.deleteByEmail(email);
			}
		    @DeleteMapping("/delete/designation")  //not working
		    public ResponseEntity<ResponseStructure<List<Employee>>> deleteByDesignation(@RequestParam String designation) {
				
		    	return service.deleteByDesignation(designation);
			}
		    @DeleteMapping("/delete/name")  //not working
		    public ResponseEntity<ResponseStructure<List<Employee>>> deleteByName(@RequestParam String name) {
				
		    	return service.deleteByName(name);
			}
		    @DeleteMapping("/delete/address")//not working
		    public ResponseEntity<ResponseStructure<List<Employee>>> deleteByAddress(@RequestParam String address) {
				
		    	return service.deleteByAddress(address);
			}
		    
		    @PutMapping("/update")
		    public ResponseEntity<ResponseStructure<Employee>> updateEmployee(@RequestParam int id, @RequestBody Employee employee) {
		    	
		    	return service.updateEmployee(employee);
		    }
		    
		    @PatchMapping("/update/phone")
		    public ResponseEntity<ResponseStructure<Employee>> updatePhone(@RequestParam int id, @RequestParam long phone) {
				
		    	return service.updatePhone(id,phone);
			}
		    
		    @PatchMapping("/update/email")
		    public ResponseEntity<ResponseStructure<Employee>> updateEmail(@RequestParam int id, @RequestParam String email) {
				
		    	return service.updateEmail(id,email);
			}
		    
		    @PatchMapping("/update/salary")
		    public ResponseEntity<ResponseStructure<Employee>> updateSalary(@RequestParam int id, @RequestParam double salary) {
				
		    	return service.updateSalary(id,salary);
			}
		    
		    @PatchMapping("/update/designation")
		    public ResponseEntity<ResponseStructure<Employee>> updateDesignation(@RequestParam int id, @RequestParam String designation) {
				
		    	return service.updateDesignation(id,designation);
			}
		    
		    @PatchMapping("/update/name")
		    public ResponseEntity<ResponseStructure<Employee>> updateName(@RequestParam int id, @RequestParam String name) {
				
		    	return service.updateName(id,name);
			}
		    
		    @PatchMapping("/update/id/password")
		    public ResponseEntity<ResponseStructure<Employee>> updatePassword(@RequestParam int id, @RequestParam String passoward) {
				
		    	return service.updatePassword(id,passoward);
			}
		    
		    @PatchMapping("/update/email/password")
		    public ResponseEntity<ResponseStructure<Employee>> updatePassword(@RequestParam String email, @RequestParam String passoward) {
				
		    	return service.updatePassword(email,passoward);
			}
		    
		    @PatchMapping("/update/phone/password")
		    public ResponseEntity<ResponseStructure<Employee>> updatePassword(@RequestParam long phone, @RequestParam String passoward) {
				
		    	return service.updatePassword(phone,passoward);
			}
		    @GetMapping("/find/emp")
		    public ResponseEntity<ResponseStructure<Employee>> getEmployee(@RequestParam String name) {
		        return service.getEmployee(name);
		    }  
	}

