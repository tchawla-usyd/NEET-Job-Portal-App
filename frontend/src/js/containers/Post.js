import React, {Component} from "react";
import {Form, Input, DatePicker, Tag, Tooltip, Icon, Button} from 'antd';

import BaseLayout from '../components/BaseLayout';
import Tags from '../components/Tags';

class Post extends Component {
    constructor(props) {
        super(props);
    }

	handleSubmit = async(e) => {
		e.preventDefault();
		this.props.form.validateFieldsAndScroll((err, values) => {
		  if (!err) {
		    console.log(values)
		    /* TODO: Backend */

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
	    	<h2 style={{display: 'flex',  justifyContent:'center', alignItems:'center', }}> Post A Job</h2>
	    	<Form style={{paddingTop:"10vh"}} onSubmit= {this.handleSubmit}>
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