package com.rakuten.fullstackrecruitmenttest.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.rakuten.fullstackrecruitmenttest.exception.EmployeeDataIsInvalidException;
import com.rakuten.fullstackrecruitmenttest.model.Employee;
import com.rakuten.fullstackrecruitmenttest.repository.EmployeeRepository;
import com.rakuten.fullstackrecruitmenttest.service.EmployeeService;

@CrossOrigin(origins="http://localhost:3000")
@RestController
@RequestMapping("/employee")
public class MainController {
	
	@Autowired
	private EmployeeRepository employeeRepository;
	
	/**
	 * Retrieve all employees
	 * 
	 * @return All Employees
	 */
	@GetMapping("/retrieve/all")
	public List<Employee> retrieveAllEmployees(){
		return employeeRepository.findAll();
	}
	
	/**
	 * Retrieve one employee
	 * 
	 * @param id
	 * @return One Employee
	 */
	@GetMapping("/retrieve/{id}")
	public Employee retrieveEmployee(@PathVariable long id) {
		Optional<Employee> employee = employeeRepository.findById(id);

		if (!employee.isPresent())
			throw new EmployeeDataIsInvalidException("Employee with id " + id + " not found");

		return employee.get();
	}
	
	/**
	 * Delete one employee
	 * 
	 * @param id
	 */
	@DeleteMapping("/delete/{id}")
	public void deleteStudent(@PathVariable long id) {
		employeeRepository.deleteById(id);
	}
	
	
	/**
	 * Update one employee
	 * 
	 * @param employee
	 * @param id
	 * @return
	 */
	@PutMapping("/update/{id}")
	public ResponseEntity<Object> updateStudent(@RequestBody Employee employee, @PathVariable long id) {

		Optional<Employee> studentOptional = employeeRepository.findById(id);

		if (!studentOptional.isPresent())
			throw new EmployeeDataIsInvalidException("The employee you're trying to update is not yet present");

		employee.setId(id);
		
		employeeRepository.save(employee);

		return new ResponseEntity<>("Employee with id " + id +" updated successfully", HttpStatus.OK);
	}
	
	

	/**
	 * Process all employee data from CSV files
	 * 
	 * @param dataFileLocation
	 * @return
	 */
	@Transactional
	@RequestMapping(value = "/upload/", method = RequestMethod.GET)
	public ResponseEntity<Object> uploadEmployeeCSVData(@RequestParam("employeeDataPath") String dataFileLocation) {

		Path filePath = Paths.get(dataFileLocation);
		try {
			if (!Files.probeContentType(filePath).equals("application/vnd.ms-excel") || Files.isDirectory(filePath)) {

				throw new EmployeeDataIsInvalidException("Only CSV files are allowed for employee data upload");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		List<Employee> employeeData = new ArrayList<>();
		EmployeeService employeeService = new EmployeeService();
		employeeData = employeeService.processEmployeeDataFile(dataFileLocation);
				
		employeeRepository.deleteAll();
		for(Employee employee: employeeData) {
			employeeRepository.save(employee);
		}

		return new ResponseEntity<>("Employee data file with name " + filePath.getFileName() +" uploaded successfully", HttpStatus.OK);

	}
}
