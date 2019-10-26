import React, {Component} from "react";
import Routes from "./Routes";
import {message} from 'antd';
import axios from 'axios';


import {GET_USER_ID, GET_USER, HEADER} from "../constants/BackendAPI"
import Spinner from './Spinner';

class App extends Component {
	constructor(props) {
		super(props);
		this.state = {isAuthenticated: false, isLoading: true};
		this.token = localStorage.getItem("token");// check if token exists
        this.headers = {headers:{...HEADER, 'Authorization': this.token}};

		if(this.token != null){
		  this.getUserID();
		}else{
		  this.state = {isLoading: false, isAuthenticated: false};
		}
    }

    getUserID = () => {
    	try {
		    // check user token against token in the database
		    axios.get(GET_USER_ID, this.headers)
		      .then(res => {
		        if (res.status == 200){
		          this.id = res.data.userId;
		          this.getUser();
		        }else{
		          this.props.history.push('/login');
		          this.setState({isLoading: false});
		        }
		    });
		  } catch (e) {
		    alert(e.message);
		  }
    }

    getUser = () =>{
    	try {
    		axios.get(GET_USER + this.id, this.headers)
		      .then(res => {
		        if (res.status == 200){
		        	console.log(res);
		          this.setState({userInfo: {id: this.id, 
		          	firstName: res.data.firstName,
		          	lastName: res.data.lastName,
		          	email: res.data.email,
		            isEmployer: res.data.userTypeID == 3 ? true : false}, isLoading: false, isAuthenticated: true});
		        }else{
		          this.props.history.push('/login');
		          this.setState({isLoading: false});
		        }
		    });
		  } catch (e) {
		    alert(e.message);
		  }
    }

    setAuthenticated = ()=>{
	  this.setState({isAuthenticated:true});
	  this.token = localStorage.getItem("token");// check if token exists
      this.headers = {headers:{...HEADER, 'Authorization': this.token}};
      this.getUserID();
	}

	handleLogout = ()=>{
		localStorage.removeItem('token');
	    this.setState({ isAuthenticated: false });
    }

	render (){
	  const childProps = {
        isAuthenticated: this.state.isAuthenticated,
        setAuthenticated: this.setAuthenticated,
        handleLogout: this.handleLogout,
        userInfo: this.state.userInfo,
  	  }
	  return (this.state.isLoading ? <Spinner /> :
	  <div className="App">
	      <Routes childProps={childProps}/>
	  </div>
	  );
	}
}
export default App;