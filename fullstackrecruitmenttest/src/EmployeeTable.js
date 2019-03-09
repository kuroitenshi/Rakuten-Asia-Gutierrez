import React, { Component } from 'react';
import PropTypes from 'prop-types';
import { withStyles } from '@material-ui/core/styles';
import Table from '@material-ui/core/Table';
import TableBody from '@material-ui/core/TableBody';
import TableCell from '@material-ui/core/TableCell';
import TableHead from '@material-ui/core/TableHead';
import TableRow from '@material-ui/core/TableRow';
import Paper from '@material-ui/core/Paper';
import EmployeeTableActions from './EmployeeTableActions.js';
import TableFooter from '@material-ui/core/TableFooter';
import TablePagination from '@material-ui/core/TablePagination';

const styles = theme => ({
	  table: {
	    minWidth: 700,
	  },
	  paper: {
		  marginTop: -20,
		  marginLeft: 50,
		  marginRight: 50,
		  overflowX: 'auto',
	  },
	  root: {
		    width: '100%',
		    marginTop: theme.spacing.unit * 3,
		  },
	  tableWrapper: {
		    overflowX: 'auto',
		  },
	});

let counter = 0;
function createData(name, department, designation, salary, joiningDate) {
  counter += 1;
  return { id: counter, name, department, designation, salary, joiningDate };
}


class EmployeeTable extends Component {
	constructor(props){
		super(props);
		this.state = {rows:[]}
	}
	
	handleChangePage = (event, page) => {
	    this.setState({ page });
	  };

	  handleChangeRowsPerPage = event => {
	    this.setState({ page: 0, rowsPerPage: event.target.value });
	  };
	  
	  /**
		 * sample data
		 */
	  /*state = {
			    rows: [
			      createData('Frozen yoghurt', 159, 6.0, 24, 4.0, 100),
			   	  createData('Ice cream sandwich', 237, 9.0, 37, 4.3, 100),
			   	  createData('Eclair', 262, 16.0, 24, 6.0, 100),
			   	  createData('Cupcake', 305, 3.7, 67, 4.3, 100),
			   	  createData('Frozen yoghurt', 159, 6.0, 24, 4.0, 100),
			   	  createData('Ice cream sandwich', 237, 9.0, 37, 4.3, 100),
			   	  createData('Eclai 	  createData('Gingerbread', 356, 16.0, 49, 3.9, 100),
			  r', 262, 16.0, 24, 6.0, 100),
			   	  createData('Cupcake', 305, 3.7, 67, 4.3, 100),
			   	  createData('Gingerbread', 356, 16.0, 49, 3.9, 100),
			    ].sort((a, b) => (a.calories < b.calories ? -1 : 1)),
			    page: 0,
			    rowsPerPage: 5,
		};*/
	  
	 render() {
		 const { classes } = this.props;
		 const { rows, rowsPerPage, page } = this.state;
		 this.state.rowsPerPage = 5;
		 this.state.page = 1;
		 this.state.rows = this.props.employeeTable;
		 const emptyRows = rowsPerPage - Math.min(rowsPerPage, rows.length - page * rowsPerPage);
		    return (
		    		<Paper className={classes.paper}>
		    	      <Table className={classes.table}>
		    	        <TableHead>
		    	          <TableRow>
		    	            <TableCell>Dessert (100g serving)</TableCell>
		    	            <TableCell align="right">Name</TableCell>
		    	            <TableCell align="right">Department</TableCell>
		    	            <TableCell align="right">Designation</TableCell>
		    	            <TableCell align="right">Salary</TableCell>
		    	            <TableCell align="right">Joining Date</TableCell>
		    	          </TableRow>
		    	        </TableHead>
		    	        <TableBody>
		    	        {rows.slice(page * rowsPerPage, page * rowsPerPage + rowsPerPage).map(row => (
		    	                <TableRow key={row.id}>
		    	                  <TableCell component="th" scope="row">
		    	                    {row.name}
		    	                  </TableCell>
		    	                  <TableCell align="right">{row.name}</TableCell>
		    	                  <TableCell align="right">{row.department}</TableCell>
		    	                  <TableCell align="right">{row.designation}</TableCell>
		    	                  <TableCell align="right">{row.salary}</TableCell>
		    	                  <TableCell align="right">{row.joiningDate}</TableCell>
		    	                </TableRow>
		    	              ))}
		    	              {emptyRows > 0 && (
		    	                <TableRow style={{ height: 48 * emptyRows }}>
		    	                  <TableCell colSpan={6} />
		    	                </TableRow>
		    	              )}
		    	        </TableBody>
		    	        <TableFooter>
		                <TableRow>
		                  <TablePagination
		                    rowsPerPageOptions={[5, 10, 25]}
		                    colSpan={3}
		                    count={rows.length}
		                    rowsPerPage={rowsPerPage}
		                    page={page}
		                    SelectProps={{
		                      native: true,
		                    }}
		                    onChangePage={this.handleChangePage}
		                    onChangeRowsPerPage={this.handleChangeRowsPerPage}
		                    ActionsComponent={EmployeeTableActions}
		                  />
		                </TableRow>
		              </TableFooter>
		    	      </Table>
		    	    </Paper>
		    );
	}
}

EmployeeTable.propTypes = {
		  classes: PropTypes.object.isRequired,
		};

export default withStyles(styles)(EmployeeTable);
