import React, {Component} from "react";
import { Typography, Input, Button } from 'antd';

import {EDIT_JOB} from "../constants/BackendAPI"

const { Text, Paragraph } = Typography;

const para_style = {marginLeft:60,  marginRight: 50, marginBottom: 40, marginTop: 40, fontSize: 18};
export default class Paragraph_ extends Component {
	constructor(props){
		super(props);
		this.state = {edit: false};
	}
	
	handleToggle = (e) => {
		if(this.state.edit){
			var payload = {};
			payload[this.props.name] = this.state.input;
			// Save the change
			this.props.handleSubmit(payload);
		}
		this.setState({edit: !this.state.edit});
	}

	render(){
		if(this.state.edit){
			const btn_style={float:'right', marginRight: 20, marginTop: 20, width: 80};
			return(
				<div style={para_style}>
					<Input.TextArea autosize style={{whiteSpace: 'pre-line'}} defaultValue={this.props.children} onChange={(e)=>this.setState({input: e.target.value})}/>
					<Button onClick={()=> this.setState({edit: !this.state.edit, input: ""})} type="danger" style={btn_style}>Cancel</Button>
					<Button onClick={this.handleToggle} style={btn_style}>Save</Button>
				</div>
			);
		}else{
			return(
				<div style={para_style}>
					<span style={{float:'right', verticalAlign: 'top'}}>
						<Button type="link" icon='edit' onClick={this.handleToggle}>Edit</Button>
					</span>
					<Paragraph style={{whiteSpace: 'pre-line'}}>{this.props.children}</Paragraph>
				</div>
			);
		}
	}
}