import React, {Component} from "react";
import {Form, Input, DatePicker, Tag, Tooltip, Icon, Button, message} from 'antd';

import BaseLayout from '../components/BaseLayout';
import Tags from '../components/Tags';

import axios from 'axios';
import qs from 'querystring';
import moment from 'moment';


const config = {
  headers: {
    'Content-Type': 'application/x-www-form-urlencoded'
  }
}

class Post extends Component {
    constructor(props) {
        super(props);
    }

	handleSubmit = async(e) => {
		e.preventDefault();
		this.props.form.validateFieldsAndScroll((err, values) => {
		  if (!err) {
		  	var payload = {
		  		"title": values.title,
		  		"description": values.description,
		  		"location": values.location,
		  		"start_date": moment(values.start_date).format('YYYY-MM-DD'),
		  		"end_date": moment(values.end_date).format('YYYY-MM-DD'),
		  		"skills": values.skills.tags,
		  		"job_category": 1
		  	}

		    console.log(values)
		    console.log(payload)
		    /* TODO: Backend */
		    try {
	            axios.post('http://localhost:8081/jobsite/job/add', qs.stringify(payload), config)
	            .then(res => {
	                if (res.status == 200) {
	                    // const token = res.data.token;
	                    // localStorage.setItem('token', token);// set token in local storage for continuous authentication
	                    // this.props.setAuthenticated();
	                    // this.props.history.push("/songs");

	                }else{
	                    message.error("Wrong Username/Password !");
	                }
		        })
	        } catch (e) {
	            alert(e.message);
	        }

		  }
		});
	}


    render(){
    	const { getFieldDecorator } = this.props.form;
	    const formItemLayout = {
	      labelCol: { span: 4, offset: 6},
	      wrapperCol: { span: 6 },
	      align: "middle"
	    };
	    
    	return(
    	<BaseLayout>
	    	<p className='title' style={{display: 'flex',  justifyContent:'center', alignItems:'center', fontSize: 50}}>Post A Job</p>
	    	<Form style={{paddingTop: 10}} onSubmit= {this.handleSubmit}>
				<Form.Item {...formItemLayout} label="Job Title">
				  {getFieldDecorator('title', {
		            rules: [{ required: true, message: 'Please enter the Job Position!' }],
		          })(<Input/>)}
        		</Form.Item>

        		<Form.Item {...formItemLayout} label="Job Location">
		          {getFieldDecorator('location', {
		            rules: [{ required: true, message: 'Please enter the Job Location!' }],
		          })(<Input/>)}
        		</Form.Item>

        		<Form.Item {...formItemLayout} label="Start Date">
		          {getFieldDecorator('start_date', {
		            rules: [{ required: true, message: 'Please enter the Start Date!' }],
		          })(<DatePicker />)}
        		</Form.Item>

        		<Form.Item {...formItemLayout} label="End Date">
		          {getFieldDecorator('end_date', {
		            rules: [{ required: true, message: 'Please enter the End Date!' }],
		          })(<DatePicker />)}
        		</Form.Item>

        		<Form.Item {...formItemLayout} label="Job Description">
		          {getFieldDecorator('description', {
		            rules: [{ required: false}],
		          })(<Input.TextArea rows={4}/>)}
        		</Form.Item>

        		<Form.Item {...formItemLayout} label="Job Skills">
		          {getFieldDecorator('skills', {
		            rules: [{ required: true}], message: 'Please add Skills!' 
		          })(<Tags />)}
        		</Form.Item>

        		{/* Submit Button */}
		        <Form.Item wrapperCol={{span: 5,offset: 11,}}>
		          <Button type="primary" htmlType="submit">Post</Button>
		        </Form.Item>
	    	</Form>
	    </BaseLayout>
	    )
    }	
}

const PostForm = Form.create()(Post)
export default PostForm