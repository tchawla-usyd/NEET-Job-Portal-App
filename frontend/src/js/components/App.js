import React, {Component} from "react";
import Routes from "./Routes";
import {message} from 'antd';
import axios from 'axios';


import {GET_USER_ID, GET_USER, HEADER} from "../constants/BackendAPI"
import Spinner from './Spinner';

class App extends Component {
	constructor(props) {
		super(props);
		this.state = {isAuthenticated: false, isLoading: true, userInfo: null};
		this.token = localStorage.getItem("token");// check if token exists
        this.headers = {headers:{...HEADER, 'Authorization': this.token}};

		if(this.token != null){
		  this.getUserID();
		}else{
		  this.state = {isLoading: false, isAuthenticated: false};
		}
    }

    async getUserID() {
    		await Promise.all([this.setState({isLoading: true})]);

		    // check user token against token in the database
		    const res1 = await Promise.all([axios.get(GET_USER_ID, this.headers)]);

		    const res2 = await axios.get(GET_USER + res1[0].data.userId, this.headers);

		    this.setState({userInfo: {id: res1[0].data.userId, 
		          	firstName: res2.data.firstName,
		          	lastName: res2.data.lastName,
		          	email: res2.data.email,
		            isEmployer: (res2.data.userTypeID == 3) /*? true : false bruh moment :)*/}, 
		            isLoading: false, 
		            isAuthenticated: true,
		        	userId: res1[0].data.userId});

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
	  console.log(this.state.isLoading)
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