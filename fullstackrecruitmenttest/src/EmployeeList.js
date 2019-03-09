import React, { Component } from 'react';
import Employee from './Employee.js';

class EmployeeList extends Component{
	
	render() {
		const employees = this.props.employees.map(employee =>
			<Employee key={employee.id} employee={employee}/>
		);
		return (
			<table>
				<tbody>
					<tr>
						<th>Name</th>
						<th>Department</th>
						<th>Designation</th>
						<th>Salary</th>
						<th>Joining Date</th>
					</tr>
					{employees}
				</tbody>
			</table>
		)
	}
}

export default EmployeeList;