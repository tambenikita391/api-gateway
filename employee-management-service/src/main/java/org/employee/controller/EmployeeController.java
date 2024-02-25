package org.employee.controller;

import java.util.List;

import org.employee.dto.CustomerRequest;
import org.employee.dto.EmployeeRequest;
import org.employee.dto.EmployeeResponse;
import org.employee.dto.SearchCustomerResponse;
import org.employee.entity.Employee;
import org.employee.service.EmployeeService;
import org.employee.service.SearchCustomer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins="*")
public class EmployeeController {

	@Autowired
	EmployeeService service;
	
	@Autowired
	SearchCustomer cust;
	
	@PostMapping("/employee")
	public EmployeeResponse addEmployee(@RequestBody EmployeeRequest request) {
		return service.saveEmployeeDetails(request);
	}
	
	@PutMapping("/employee")
	public Employee update(@RequestBody Employee employee) {
		return service.updateDetails(employee);
	}
	
	@GetMapping("/employee/{empId}")
	public EmployeeResponse searchEmployee(@PathVariable int empId) {
		return service.searchEmployee(empId);
	}
	
	@GetMapping("/employee")
	public List<Employee> getAllEmployee(){
		return service.getAllEmployee();
	}
	
	@DeleteMapping("/employee/{empId}")
	public String deleteEmp(@PathVariable int empId) {
		return service.deleteEmployee(empId);
	}
	
	@GetMapping("/customer/{id}")
	public SearchCustomerResponse getCustomer(@PathVariable long id) {
		return cust.searchCustomerById(id);
	}
}
