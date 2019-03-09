import React, { Component } from 'react';
import './App.css';
import App from './App.js';
import axios from 'axios';
import Typography from '@material-ui/core/Typography';

class UploadService extends Component {
	
	constructor(props) {
	    super(props);
	    this.state = {data:[]},
	    this.trigger = this.props.trigger
	  }
	
	log(){
		console.log(this.props);
	}
	
	 getAllRecords = () =>{
		 axios.get('http://localhost:8080/employee/retrieve/all', {
			 crossDomain: true
		 })
	      .then(response => {
	        this.setState({ data: response.data[0].name });
	      })
	      .catch(function (error) {
	        console.log(error);
	    })
	}
	
	uploadCsvFile(){
		axios.get('http://localhost:8080/employee/upload', {
			 crossDomain: true
		 })
	      .then(response => {
	        //this.setState({ serverports: response.data });
	    	  alert(response.data);
	      })
	      .catch(function (error) {
	        console.log(error);
	    })
	}
	
	downloadCsvFile(){
		/*axios.get('http://localhost:8080/employee/upload', {
			 crossDomain: true
		 })
	      .then(response => {
	        //this.setState({ serverports: response.data });
	    	  alert(response.data);
	      })
	      .catch(function (error) {
	        console.log(error);
	    })*/
	}
	
	validateCsvFile(){
		//todo
	}
	
	render() {
		this.log();
		return(
				<div>Props : {this.props.service}</div>
		)
	}
}

export default UploadService;
