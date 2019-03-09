import React, { Component } from 'react';
import axios from 'axios';
import UpdateEmployeeDialog from './UpdateEmployeeDialog.js';

class Employee extends Component{
	
	constructor(props) {
		super(props);
		this.handleDelete = this.handleDelete.bind(this);
		this.state = {
			display: true
		};
	}
	
	handleDelete(id){
		var self = this;
		axios
	      .delete("http://localhost:8080/employee/delete/" + self.props.employee.id)
	      .then(response => { 
	    	  
	    	  self.setState({display: false});    	  
	      })
	      .catch(error => console.log(error));
		
	}
	
	render() {
		if (this.state.display==false) 
			return null;
		else{
			return (

					<tr>
					<td>{this.props.employee.name}</td>
					<td>{this.props.employee.department}</td>
					<td>{this.props.employee.designation}</td>
					<td>{this.props.employee.salary}</td>
					<td>{this.props.employee.joiningDate}</td>
					<td>
						<button onClick={this.handleDelete}>Delete</button>
						<UpdateEmployeeDialog employee={this.props.employee}
						  attributes={this.props.attributes}
						  onUpdate={this.props.onUpdate}/>
					</td>
				</tr>
				
			)
		}
		
	}
}

export default Employee;