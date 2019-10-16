import React, {Component} from "react";
import axios from 'axios';
import { Form, Input, Tooltip, Icon, Row, Col, Checkbox, Button} from 'antd';

import logo from "../../NEET.png";

//Singup Page
class Signup extends Component {
  constructor(props) {
    super(props);
  }

  //send input information to backend to create new user 
  handleSubmit = async(e) => {
    e.preventDefault();
    this.props.form.validateFieldsAndScroll((err, values) => {
      if (!err) {
        console.log(values)
        /* TODO: Backend */
        // try {
        //     // check user is signed up here, so far this does nothing lmao
        //     axios.post('http://ec2-54-252-139-212.ap-southeast-2.compute.amazonaws.com:8000/user/api/add_user/', values).
        //     then(res => {
        //       if (res.status == 200) {
        //           this.props.history.push("/");//go to login page
        //       }
        //       console.log(res.data);
        //     })
        //   }catch (e) {
        //     alert(e.message);
        // }
      }
    });
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
      labelCol: { span: 4, offset: 5},
      wrapperCol: { span: 6 },
      align: "middle"
    };

    return (
      <Form style={{paddingTop:"20px"}} onSubmit= {this.handleSubmit}>
        {/* Title */}
        <Form.Item layout='center' >
          <div style={{display:'flex',
            justifyContent:"center",
            alignItems:'center'}}>
            <img style={{ width: "200px", height: "200px"}} src={logo}/>
          </div>
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
        
        {/* Agreement checkbox*/}
        <Form.Item wrapperCol={{span: 5,offset: 9,}}>
          {getFieldDecorator('agreement', {
            valuePropName: 'checked',
            rules: [{
              required: true, message: 'Please tick the agreement box!!',
            }]
          })( <Checkbox>I have read the <a href="">agreement</a></Checkbox>)}
        </Form.Item>

        {/* Submit Button */}
        <Form.Item wrapperCol={{span: 5,offset: 10,}}>
          <Button type="primary" htmlType="submit">Register</Button>
        </Form.Item>
      </Form>
    );
  }
}

const SignupForm = Form.create()(Signup)
export default SignupForm
