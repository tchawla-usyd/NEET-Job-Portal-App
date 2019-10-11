import React, {Component} from "react";
import axios from 'axios';
import { Form, Input, Tooltip, Icon, Row, Col, Checkbox, Button} from 'antd';

const FormItem = Form.Item;

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

    const formItemLayout = {
      labelCol: { span: 4, offset: 5},
      wrapperCol: { span: 6 },
      align: "middle"
    };


    return (
      <Form style={{paddingTop:"10vh"}} onSubmit= {this.handleSubmit}>
        {/* Title */}
        <Form.Item wrapperCol={{span: 8, offset:9}} ><h1>Sign Up With NEET</h1></Form.Item>

        {/* First Name */}
        <FormItem {...formItemLayout} label="First Name">
          {getFieldDecorator('first_name', {
            rules: [{ required: true, message: 'Please enter your First Name!'}],
          })(<Input/>)}
        </FormItem>

        {/* Last Name */}
        <FormItem {...formItemLayout} label="Last Name">
          {getFieldDecorator('last_name', {
            rules: [{ required: true, message: 'Please enter your Last Name!'}],
          })(<Input/>)}
        </FormItem>

        {/* E-mail*/}
        <FormItem {...formItemLayout} label="E-mail">
          {getFieldDecorator('email', {
            rules: [{
              type: 'email', message: 'The input is not valid E-mail!',
            }, {
              required: true, message: 'Please input your E-mail!',
            }],
          })(<Input/>)}
        </FormItem>

        {/* Password */}
        <FormItem {...formItemLayout} label="Password" >
          {getFieldDecorator('password', {
            rules: [{
              required: true, message: 'Please input your password!',
            }],
          })(<Input type="password" />)}
        </FormItem>

        {/* Password Confirm*/}
        <FormItem {...formItemLayout} label="Confirm Password" >
          {getFieldDecorator('confirm', {
            rules: [{
              required: true, message: 'Please confirm your password!',
            }, {
              validator: this.compareToFirstPassword,
            }],
          })( <Input type="password"/>)}
        </FormItem>
        
        {/* Agreement checkbox*/}
        <FormItem wrapperCol={{span: 5,offset: 9,}}>
          {getFieldDecorator('agreement', {
            valuePropName: 'checked',
            rules: [{
              required: true, message: 'Please tick the agreement box!!',
            }]
          })( <Checkbox>I have read the <a href="">agreement</a></Checkbox>)}
        </FormItem>

        {/* Submit Button */}
        <FormItem wrapperCol={{span: 5,offset: 10,}}>
          <Button type="primary" htmlType="submit">Register</Button>
        </FormItem>
      </Form>
    );
  }
}

const SignupForm = Form.create()(Signup)
export default SignupForm
