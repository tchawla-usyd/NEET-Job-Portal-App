import React, {Component} from "react";
import {Form, Icon, Input, Button, Checkbox, message} from 'antd';
import 'antd/dist/antd.css';
import logo from "../../NEET.png";
import axios from 'axios';

const FormItem = Form.Item;

// Login Page
export default class Login extends Component {
    constructor(props) {
        super(props);

        this.state = {
            email: "",
            password: "",
            isLoading: false
        };
    }

    // make sure email and password is not empty
    validateForm() {
        return this.state.email.length > 0 && this.state.password.length > 0;
    }


    handleSubmit = async event => {
        event.preventDefault();
        this.setState({isLoading: true});

        /* TODO: Add backend */
        // try {
        //     axios.post('localhost:8000/user/api/login/', this.state)
        //     .then(res => {
        //         if (res.status == 200) {
        //             const token = res.data.token;
        //             localStorage.setItem('token', token);// set token in local storage for continuous authentication
        //             this.props.setAuthenticated();
        //             this.props.history.push("/songs");

        //         }else{
        //             message.error("Wrong Username/Password !");
        //         }
        //     })
        // } catch (e) {
        //     alert(e.message);
        //     this.setState({isLoading: false});
        // }
        console.log("Submit");
    }

    render() {
        if(!this.props.isAuthenticated){
            return (
                <div className="container">
                    <div style={{display:'flex',
                      justifyContent:"center",
                      alignItems:'center',
                      paddingTop: 80}}>
                      <img style={{ width: "200px", height: "200px"}} src={logo}/>
                    </div>
                    <Form onSubmit={this.handleSubmit} align="middle" style={{ paddingLeft: 400, paddingRight: 400, paddingBottom: 0, paddingTop: 80}}>
                        
                        {/* Email */}
                        <FormItem>
                            <Input
                                prefix={<Icon type="user" style={{color: 'rgba(0,0,0,.25)'}}/>}
                                placeholder="Email"
                                onChange={(event) => {
                                    this.setState({email: event.target.value});
                                }}/>
                        </FormItem>

                        {/* Password */}
                        <FormItem>
                            <Input
                                prefix={<Icon type="lock" style={{color: 'rgba(0,0,0,.25)'}}/>}
                                type="password"
                                placeholder="Password"
                                onChange={(event) => this.setState({password: event.target.value})}/>
                        </FormItem>

                        {/* Login Button */}
                        <FormItem align="middle">
                            <Button type="primary" htmlType="submit" className="login-button">
                                Login
                            </Button>

                        {/* Link to Signup Page*/}
                        </FormItem>
                        No account? <a href="/signup"> Sign up here! </a>
                    </Form>
                </div>
            )
        }else{
            this.props.history.push("/songs");// when user is logged in
            return ""
        }
    }
}