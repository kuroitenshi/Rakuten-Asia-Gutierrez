package com.rakuten.fullstackrecruitmenttest.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import com.rakuten.fullstackrecruitmenttest.model.EmployeeDesignations;

public class EmployeeValidationService {
	
	private List<String> errorList;
	
	public EmployeeValidationService() {
		errorList = new ArrayList<String>();
	}

	public boolean validateEmployeeDataRow(String[] employeeData) {

		boolean dataIsValid = true;

		// Name -> can only contain alphabets
		if (!employeeData[0].trim().matches("[a-zA-Z]+")) {
			dataIsValid = false;
			errorList.add("[Employee Name can only contain alphabet letters]");
		}

		// Department -> alphanumeric with -_* as special characters
		String departmentRegex = "^[a-zA-Z0-9-_*]+$";
		Pattern pattern = Pattern.compile(departmentRegex);
		if (!pattern.matcher(employeeData[1].trim()).matches()) {
			dataIsValid = false;
			errorList.add("[Employee Department can only contain alphabet letters and special characters -_*]");
		}

		
		// Designation -> Developer, Senior Developer, Manager, Team Lead, VP, CEO
		if (!employeeData[2].trim().equals(EmployeeDesignations.getDeveloperTag())
				&& !employeeData[2].trim().equals(EmployeeDesignations.getSeniordeveloperTag())
				&& !employeeData[2].trim().equals(EmployeeDesignations.getManagerTag())
				&& !employeeData[2].trim().equals(EmployeeDesignations.getTeamleadTag())
				&& !employeeData[2].trim().equals(EmployeeDesignations.getVpTag())
				&& !employeeData[2].trim().equals(EmployeeDesignations.getCeoTag())) {
			dataIsValid = false;
			errorList.add("[Employee designation should only have values Developer, Senior Developer, Manager, Team Lead, VP, CEO]");
		}
			

		// Salary -> can only contain Numeric value
		if (!employeeData[3].trim().matches("\\d+")) {
			dataIsValid = false;
			errorList.add("[Employee Salary can only contain Numeric values]");
		
		}

		// Joining date -> yyyy-MM-dd format
		DateTimeFormatter formatter = new DateTimeFormatterBuilder().appendPattern("yyyy-MM-dd").parseStrict()
				.toFormatter();
		try {
			LocalDate.parse(employeeData[4].trim(), formatter);
		}catch (DateTimeParseException e) {
			dataIsValid = false;
			errorList.add("[Employee joining date can only have the format yyyy-MM-dd]");
		
        }
		

		return dataIsValid;

	}
	
	public String returnErrorListAsString() {
		StringBuilder builder = new StringBuilder();
		for(String error: errorList) {
			builder.append(error);
		}
		errorList.clear();
		return builder.toString();
		
	}

}