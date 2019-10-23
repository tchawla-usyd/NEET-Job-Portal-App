import React, {Component} from "react";
import Routes from "./Routes";
import {message} from 'antd';

import Spinner from './Spinner';

class App extends Component {
	constructor(props) {
		super(props);
		this.state = {isAuthenticated: false, isLoading: true};
		const hasToken = localStorage.getItem("token");// check if token exists

		console.log(hasToken);
		if(hasToken != null){
		  // try {
		  //   const authStr = "Token " + hasToken;
		  //   // check user token against token in the database
		  //   axios.get('http://ec2-54-252-139-212.ap-southeast-2.compute.amazonaws.com:8000/user/api/get_user_detials/', {'headers': {'Authorization': authStr}})
		  //     .then(res => {
		  //       if (res.status == 200) {
		  //         this.setState({isAuthenticated: true})
		  //       }else{
		  //         this.props.history.push('/login');
		  //       }
		  //   });
		  // } catch (e) {
		  //   alert(e.message);
		  // }
		  this.state = {isLoading: false, isAuthenticated: true};
		}else{
		  this.state = {isLoading: false, isAuthenticated: false};
		}
    }

    setAuthenticated = ()=>{
	  this.setState({isAuthenticated:true});
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
  	  }
	  return (this.state.isLoading ? <Spinner /> :
	  <div className="App">
	      <Routes childProps={childProps}/>
	  </div>
	  );
	}
}
export default App;