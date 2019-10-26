import React, {Component} from "react";
import {Form, Icon, Input, Button, Checkbox, message} from 'antd';
import 'antd/dist/antd.css';
import axios from 'axios';
import qs from 'querystring';

import {LOGIN, HEADER} from "../constants/BackendAPI"
import logo from "../../img/NEET.png";

const FormItem = Form.Item;

// Login Page
export default class Login extends Component {
    constructor(props) {
        super(props);

        this.state = {
            Email: "",
            Password: "",
            isLoading: false
        };
        if(this.props.isAuthenticated){
            this.props.history.push("/home");// when user is logged in
        }

    }

    // make sure email and password is not empty
    validateForm() {
        return this.state.email.length > 0 && this.state.password.length > 0;
    }


    handleSubmit = async event => {
        event.preventDefault();
        this.setState({isLoading: true});

        try {
            console.log(this.state);
            axios.post(LOGIN, qs.stringify(this.state))
            .then(res => {
                if (res.status == 200) {
                    const token = res.data.token;
                    localStorage.setItem('token', token);// set token in local storage for continuous authentication
                    this.props.setAuthenticated();
                    this.props.history.push("/about");
                }else{
                    message.error("Wrong Username/Password !");
                }
            })
        } catch (e) {
            alert(e.message);
            this.setState({isLoading: false});
        }
        
    }

    render() {
        return (
            <div className="container">
                <div style={{display:'flex',
                  justifyContent:"center",
                  alignItems:'center',
                  paddingTop: 80}}>
                  <img style={{ width: "200px", height: "200px"}} src={logo}/>
                </div>
                <Form onSubmit={this.handleSubmit} align="middle" style={{ paddingLeft: 400, paddingRight: 400, paddingBottom: 0, paddingTop: 70}}>
                    
                    {/* Email */}
                    <FormItem>
                        <Input
                            prefix={<Icon type="user" style={{color: 'rgba(0,0,0,.25)'}}/>}
                            placeholder="Email"
                            onChange={(event) => {
                                this.setState({Email: event.target.value});
                            }}/>
                    </FormItem>

                    {/* Password */}
                    <FormItem>
                        <Input
                            prefix={<Icon type="lock" style={{color: 'rgba(0,0,0,.25)'}}/>}
                            type="password"
                            placeholder="Password"
                            onChange={(event) => this.setState({Password: event.target.value})}/>
                    </FormItem>

                    {/* Login Button */}
                    <FormItem align="middle">
                        <Button htmlType="submit" size='large' className="login-button">
                            Login
                        </Button>

                    {/* Link to Signup Page*/}
                    </FormItem>
                    No account? <a href="/signup"> Sign up here! </a>
                </Form>
            </div>
        )
    }
}