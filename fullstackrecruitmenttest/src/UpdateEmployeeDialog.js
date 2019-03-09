import React, { Component } from 'react';
import ReactDOM from 'react-dom';

class UpdateEmployeeDialog extends Component {

	constructor(props) {
		super(props);
		this.handleSubmit = this.handleSubmit.bind(this);
	}

	handleSubmit(e) {
		e.preventDefault();
		const updatedEmployee = {};
		this.props.attributes.forEach(attribute => {
			updatedEmployee[attribute] = ReactDOM.findDOMNode(this.refs[attribute]).value.trim();
		});
		this.props.onUpdate(this.props.employee, updatedEmployee);
		window.location = "#";
	}

	render() {
		var self = this;
		const inputs = "";
		if(self.props.attributes) {
			const inputs = self.props.attributes.map(attribute =>
			<p key={self.props.employee.id}>
				<input type="text" placeholder={attribute}
					   defaultValue={self.props.employee.id}
					   ref={attribute} className="field"/>
			</p>
		)}else{
			const inputs = "";
		}
		

		const dialogId = "updateEmployee-" + this.props.employee.id;

		return (
			<div key={this.props.employee.id}>
				<a href={"#" + dialogId}>Update</a>
				<div id={dialogId} className="modalDialog">
					<div>
						<a href="#" title="Close" className="close">X</a>

						<h2>Update an employee</h2>

						<form>
							{inputs}
							<button onClick={this.handleSubmit}>Update</button>
						</form>
					</div>
				</div>
			</div>
		)
	}

};

export default UpdateEmployeeDialog;