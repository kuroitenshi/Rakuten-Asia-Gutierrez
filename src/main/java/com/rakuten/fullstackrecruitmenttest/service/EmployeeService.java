package com.rakuten.fullstackrecruitmenttest.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import com.rakuten.fullstackrecruitmenttest.model.Employee;

public class EmployeeService {

	private FileWriter errorFileWriter = null;
	private static final String COMMA_DELIMITER = ",";
	private static final String NEW_LINE_SEPARATOR = "\n";
	private List<Employee> employeeEntries = new ArrayList<Employee>();

	/**
	 * Reads Employee csv file and maps to designated objects
	 * 
	 * @param dataFilePath
	 * @return
	 */
	public List<Employee> processEmployeeDataFile(String dataFilePath) {

		try {

			errorFileWriter = new FileWriter(dataFilePath + "[Error file " + LocalDate.now() + " ].csv");

			File csvFile = new File(dataFilePath);
			InputStream csvFileInputStream = new FileInputStream(csvFile);
			BufferedReader br = new BufferedReader(new InputStreamReader(csvFileInputStream));

			/* Optional for headers */
			// inputList = br.lines().skip(1).map(mapToItem).collect(Collectors.toList());

			String line = "";
			while ((line = br.readLine()) != null) {

				String[] employeeData = line.split(",");
				this.mapToEmployee(employeeData);
			}
			br.close();

		} catch (IOException e) {

		} finally {

			try {
				errorFileWriter.flush();
				errorFileWriter.close();
			} catch (IOException e) {
				e.printStackTrace();
			}

		}

		return employeeEntries;

	}

	private void mapToEmployee(String[] dataLine) throws IOException {

		if (dataLine.length < 5 || dataLine.length > 5) {
			this.errorFileWriter.append(dataLine.toString() + " Invalid employee data line");
			this.errorFileWriter.append(NEW_LINE_SEPARATOR);

		} else {
			Employee employee = new Employee();
			EmployeeValidationService validationService = new EmployeeValidationService();
			if (validationService.validateEmployeeDataRow(dataLine) == true) {
				employee.setName(dataLine[0]);
				employee.setDepartment(dataLine[1]);
				employee.setDesignation(dataLine[2]);
				employee.setSalary(Double.parseDouble(dataLine[3]));
				employee.setJoiningDate(LocalDate.parse(dataLine[4]));
				employeeEntries.add(employee);
				this.appendDataToErrorCSV (dataLine);
				
			}else {
				this.appendDataToErrorCSV(dataLine);
				this.errorFileWriter.append(validationService.returnErrorListAsString());
			}
			
			this.errorFileWriter.append(NEW_LINE_SEPARATOR);
			
		}

	}
	
	private void appendDataToErrorCSV (String[] dataLine) throws IOException
	{
		this.errorFileWriter.append(dataLine[0]);
		this.errorFileWriter.append(COMMA_DELIMITER);
		this.errorFileWriter.append(dataLine[1]);
		this.errorFileWriter.append(COMMA_DELIMITER);
		this.errorFileWriter.append(dataLine[2]);
		this.errorFileWriter.append(COMMA_DELIMITER);
		this.errorFileWriter.append(dataLine[3]);
		this.errorFileWriter.append(COMMA_DELIMITER);
		this.errorFileWriter.append(dataLine[4]);
	}
	

}
