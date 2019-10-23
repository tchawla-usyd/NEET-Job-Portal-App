import React, {Component} from "react";
import axios from 'axios';
import qs from 'querystring';
import { Form, Input, Tooltip, Icon, Row, Col, Checkbox, Button, Radio} from 'antd';

import logo from "../../NEET.png";
import Tags from "../components/Tags";
import {SIGNUP, HEADER} from "../constants/BackendAPI"

//Singup Page
class Signup extends Component {
  constructor(props) {
    super(props);

    //Auth
    this.headers = {headers:{...HEADER}};

    this.state = {isEmployer:'nothing',};
  }

  //send input information to backend to create new user 
  handleSubmit = async(e) => {
    e.preventDefault();
    this.props.form.validateFieldsAndScroll((err, values) => {
      if (!err) {
        try {
            // check user is signed up here, so far this does nothing lmao
            axios.post(SIGNUP,  qs.stringify(values), this.headers).
            then(res => {
              if (res.status == 200) {
                  this.props.history.push("/");//go to login page
              }
            })
          }catch (e) {
            alert(e.message);
        }
      }
    });
  }

  handleRadioChange = (e) => {
    this.setState({isEmployer: e.target.value == 'employer'});
  }

  //validate two passwords are the same
  compareToFirstPassword = (rule, value, callback) => {
    const form = this.props.form;
    if (value && value !== form.getFieldValue('password')) {
      callback('Two passwords that you enter is inconsistent!');
    } else {
      callback();
    }
  }

  render() {
    const { getFieldDecorator } = this.props.form;
    const FormItemLayout = {
      labelCol: { span: 4, offset: 6},
      wrapperCol: { span: 5 },
      align: "middle"
    };

    let details;
    if (this.state.isEmployer == true) {
        details = <Form.Item {...FormItemLayout} label="Company Name">
                  {getFieldDecorator('company', {
                      rules: [{
                        required: true, message: 'Please enter your company\'s name!!',
                      }]
                    })( <Input />)}
                  </Form.Item>;
      }else if (this.state.isEmployer == false){
        details = <div><Form.Item {...FormItemLayout} label="Skills">
                    {getFieldDecorator('skills', {
                      rules: [{
                        required: true, message: 'Please enter your skills!!',
                      }]
                    })( <Tags />)}
                  </Form.Item>
                  <Form.Item {...FormItemLayout} label="Education">
                    {getFieldDecorator('education')(<Input.TextArea rows={3}/>)}
                  </Form.Item>
                  <Form.Item {...FormItemLayout} label="Experience">
                    {getFieldDecorator('experience')(<Input.TextArea rows={4}/>)}
                  </Form.Item></div>;
      }
    

    return (
      <Form style={{paddingTop:"20px"}} onSubmit= {this.handleSubmit}>
        {/* Title */}

        <Form.Item layout='center' >
          <div style={{display:'flex',
            justifyContent:"center",
            alignItems:'center'}}>
            <img style={{ width: "200px", height: "200px"}} src={logo}/>
          </div>
          <div className='title' style={{display:'flex',
            justifyContent:"center", alignItems:'center',
            fontSize: 40, marginTop: 20, marginBottom: 20}}>Join NEET.NET</div>

        </Form.Item>

        {/* First Name */}
        <Form.Item {...FormItemLayout} label="First Name">
          {getFieldDecorator('first_name', {
            rules: [{ required: true, message: 'Please enter your First Name!'}],
          })(<Input/>)}
        </Form.Item>

        {/* Last Name */}
        <Form.Item {...FormItemLayout} label="Last Name">
          {getFieldDecorator('last_name', {
            rules: [{ required: true, message: 'Please enter your Last Name!'}],
          })(<Input/>)}
        </Form.Item>

        {/* E-mail*/}
        <Form.Item {...FormItemLayout} label="E-mail">
          {getFieldDecorator('email', {
            rules: [{
              type: 'email', message: 'The input is not valid E-mail!',
            }, {
              required: true, message: 'Please input your E-mail!',
            }],
          })(<Input/>)}
        </Form.Item>

        {/* Password */}
        <Form.Item {...FormItemLayout} label="Password" >
          {getFieldDecorator('password', {
            rules: [{
              required: true, message: 'Please input your password!',
            }],
          })(<Input type="password" />)}
        </Form.Item>

        {/* Password Confirm*/}
        <Form.Item {...FormItemLayout} label="Confirm Password" >
          {getFieldDecorator('confirm', {
            rules: [{
              required: true, message: 'Please confirm your password!',
            }, {
              validator: this.compareToFirstPassword,
            }],
          })( <Input type="password"/>)}
        </Form.Item>

        <Form.Item {...FormItemLayout} label="I am">
          {getFieldDecorator('user_type', {rules: [{
              required: true, message: 'Please Select Who You Are!'
            }]})(
            <Radio.Group onChange={this.handleRadioChange}>
              <Radio value="seeker"> A Job Seeker</Radio>
              <Radio value="employer">An Employer</Radio>
            </Radio.Group>
          )}
        </Form.Item>

        {details}
        
        {/* Agreement checkbox*/}
        <Form.Item style= {{textAlign: 'center'}}>
          {getFieldDecorator('agreement', {
            valuePropName: 'checked',
            rules: [{
              required: true, message: 'Please tick the agreement box!!',
            }]
          })( <Checkbox>I have read and agree the <a href="">agreement</a></Checkbox>)}
        </Form.Item>

        {/* Submit Button */}
        <Form.Item style= {{textAlign: 'center'}}>
          <Button size='large' htmlType="submit">Sign Up</Button>
        </Form.Item>
      </Form>
    );
  }
}

const SignupForm = Form.create()(Signup)
export default SignupForm
