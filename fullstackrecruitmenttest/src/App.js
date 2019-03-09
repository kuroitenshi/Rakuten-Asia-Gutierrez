import React, { Component } from 'react';

import './App.css';
import axios from 'axios';
import EmployeeList from './EmployeeList.js';

class App extends Component {

	
	
	constructor(props) {
		super(props);
		this.state = {
				employees: []
		};
		
		this.componentDidMount = this.componentDidMount.bind(this);
	}

	componentDidMount() {
		
		var self = this;
		axios
	      .get("http://localhost:8080/employee/retrieve/all")
	      .then(response => {

	        const retrievedEmployees = response.data.map(e => {
	          return {
	            id: e.id,
	            name: e.name,
	            department: e.department,
	            designation: e.designation,
	            salary: e.salary,
	            joiningDate: e.joiningDate
	          };
	        });

	        const newState = Object.assign({}, self.state, {
	        	employees: retrievedEmployees
	        });

	        self.setState(newState);
	      })
	      .catch(error => console.log(error));
		

	}
	

	render() {
		return (
			<EmployeeList employees={this.state.employees}/>
		)
	}
}

export default App;
