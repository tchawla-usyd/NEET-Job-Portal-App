import React, {Component} from "react";
import {Form, Input, DatePicker, Tag, Tooltip, Icon, Button, message} from 'antd';
import axios from 'axios';
import qs from 'querystring';
import moment from 'moment';

import BaseLayout from '../components/BaseLayout';
import Tags from '../components/Tags';
import {ADD_JOB, HEADER} from '../constants/BackendAPI'
const {RangePicker} = DatePicker;

class Post extends Component {
    constructor(props) {
        super(props);
        this.token = localStorage.getItem("token");
        this.headers = {headers:{...HEADER, 'Authorization': this.token}};
    }

	componentDidMount(prevProps) {
      window.scrollTo(0, 0);
	}

	handleSubmit = async(e) => {
		e.preventDefault();
		this.props.form.validateFieldsAndScroll((err, values) => {
		  if (!err) {
		  	var payload = {
		  		title: values.title,
		  		description: values.description,
		  		location: values.location,
		  		start_date: moment(values.start_date[0]).format('YYYY-MM-DD'),
		  		end_date: moment(values.start_date[1]).format('YYYY-MM-DD'),
		  		skills: values.skills.skills,
		  		job_category: 1
		  	}

		  	//add a new job post
            axios.post(ADD_JOB, qs.stringify(payload), this.headers)
            .then(res => {
                if (res.status == 200) {
                    message.success(values.title + ' Posted!');
					this.props.history.push("/home");
                }else{
                    message.error("Something is wrong !");
                }
	        }).catch (e =>{
	        	this.props.history.push("/home");
	            alert(e.message);
        	});
          }

		});
	}


    render(){
    	const { getFieldDecorator } = this.props.form;
	    const formItemLayout = {
	      labelCol: { span: 4, offset: 6},
	      wrapperCol: { span: 5 },
	      align: "middle"
	    };
	    
    	return(
    	<BaseLayout parentProps={this.props}>
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

        		<Form.Item {...formItemLayout} label="Application Open Date">
		          {getFieldDecorator('start_date', {
		            rules: [{ required: true, message: 'Please enter the Start/End Date!' }],
		          })(<RangePicker />)}
        		</Form.Item>

        		<Form.Item {...formItemLayout} label="Job Description">
		          {getFieldDecorator('description', {
		            rules: [{ required: false}],
		          })(<Input.TextArea style={{whiteSpace: 'pre-line'}} rows={4}/>)}
        		</Form.Item>

        		<Form.Item {...formItemLayout} label="Job Skills">
		          {getFieldDecorator('skills', {
		            rules: [{ required: true}], message: 'Please add Skills!' 
		          })(<Tags editable = 'true'/>)}
        		</Form.Item>

        		{/* Submit Button */}
		        <Form.Item style= {{textAlign: 'center'}}>
		          <Button size='large' htmlType="submit">Post</Button>
		        </Form.Item>
	    	</Form>
	    </BaseLayout>
	    )
    }	
}

const PostForm = Form.create()(Post)
export default PostForm