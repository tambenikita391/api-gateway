package org.employee.service;

import java.util.List;
import java.util.Optional;

import org.employee.dto.EmployeeRequest;
import org.employee.dto.EmployeeResponse;
import org.employee.entity.Employee;
import org.employee.repo.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
@Service
@Component
public class EmployeeService {
	
	@Autowired
	EmployeeRepository repo;
	
	public EmployeeResponse saveEmployeeDetails(EmployeeRequest request) {
		EmployeeResponse response = new EmployeeResponse();
		
		Employee employee = new Employee();
		
		employee.setFullName(request.getFullName());
		employee.setPosition(request.getPosition());
		employee.setGender(request.getGender());
		employee.setSalary(request.getSalary());
		employee.setPhone(request.getPhone());
		employee.setAge(request.getAge());
		employee.seteMail(request.geteMail());
		
		employee = repo.save(employee);
		
		response.setMessage("Employee data added successfully!!");
		response.setEmpId(employee.getEmpId());
		return response;
		
	}
	
	public Employee updateDetails(Employee employee) {
		
		Employee updateEmployee = repo.findById(employee.getEmpId()).get();
		
		if(updateEmployee!=null) {
			
			updateEmployee.setFullName(employee.getFullName());
			updateEmployee.setPosition(employee.getPosition());
			updateEmployee.setGender(employee.getGender());
			updateEmployee.setSalary(employee.getSalary());
			updateEmployee.setPhone(employee.getPhone());
			updateEmployee.setAge(employee.getAge());
			updateEmployee.seteMail(employee.geteMail());
			
			repo.save(updateEmployee);
			return updateEmployee;
		}
		return null;
		
	}
	
	public EmployeeResponse searchEmployee(int empId) {
		EmployeeResponse response =  new EmployeeResponse();
		Optional<Employee> empTable = repo.findById(empId);
		
		if(empTable.isEmpty()) {
			response.setMessage("Employee not found");
		}else {
			Employee employee = empTable.get();
			response.setFullName(employee.getFullName());
			response.setPosition(employee.getPosition());
			response.setGender(employee.getGender());
			response.setSalary(employee.getSalary());
			response.setPhone(employee.getPhone());
			response.setAge(employee.getAge());
			response.seteMail(employee.geteMail());
			
			}
		return response;
			
	}
	
	public List<Employee> getAllEmployee(){
		return repo.findAll();
	}
	
	

	public String deleteEmployee(int empId) {
		repo.deleteById(empId);
		return "Employee deleted !!";
	}

}
