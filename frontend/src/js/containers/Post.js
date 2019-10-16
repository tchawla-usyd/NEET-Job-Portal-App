import React, {Component} from "react";
import {Form, Input, DatePicker, Tag, Tooltip, Icon, Button} from 'antd';

import BaseLayout from '../components/BaseLayout';

class Tags extends Component {
	constructor(props) {
    	super(props);
	    this.state = {
	    	tags: [],
	    	inputVisible: false,
	    	inputValue: '',
	    };
	}

	triggerChange = () => {
	    // Should provide an event to pass value to Form.
	    const { onChange } = this.props;
	    if (onChange) {
	      onChange({
	        tags: this.state.tags,
	      });
	    }
  	};

    handleClose = removedTag => {
		const tags = this.state.tags.filter(tag => tag !== removedTag);
		console.log(tags);
		this.setState({ tags }, this.triggerChange);
		this.triggerChange();
	};

	showInput = () => {
		this.setState({ inputVisible: true }, () => this.input.focus());
	};

	handleInputChange = e => {
		this.setState({ inputValue: e.target.value });
	};

	handleInputConfirm = () => {
		const { inputValue } = this.state;
		let { tags } = this.state;
		if (inputValue && tags.indexOf(inputValue) === -1) {
		  tags = [...tags, inputValue];
		}
		console.log(tags);
		this.setState({
		  tags,
		  inputVisible: false,
		  inputValue: '',
		},this.triggerChange);
	};

	saveInputRef = input => (this.input = input);

    render() {
    	const { tags, inputVisible, inputValue } = this.state;
    	const color = ["magenta", "red", "volcano", "orange", "gold", "lime", "green", "cyan", "blue", "geekblue", "purple"];
    	return (
    		<span>
    		{tags.map((tag, index) => {
	          const isLongTag = tag.length > 20;
	          const tagElem = (
	            <Tag key={tag} color={color[Math.floor(Math.random()*color.length)]} closable='true' onClose={() => this.handleClose(tag)}>
	              {isLongTag ? `${tag.slice(0, 20)}...` : tag}
	            </Tag>
	          );
	          return isLongTag ? (
	            <Tooltip title={tag} key={tag}>
	              {tagElem}
	            </Tooltip>
	          ) : (tagElem);
        	})}
        	{inputVisible && (
	          <Input
	            ref={this.saveInputRef}
	            type="text"
	            size="small"
	            style={{ width: 78 }}
	            value={inputValue}
	            onChange={this.handleInputChange}
	            onBlur={this.handleInputConfirm}
	            onPressEnter={this.handleInputConfirm}
	          />
	        )}
	        {!inputVisible && (
	          <Tag onClick={this.showInput} style={{ background: '#fff', borderStyle: 'dashed' }}>
	            <Icon type="plus" /> New Tag
	          </Tag>
	        )}
    		</span>
    	)
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