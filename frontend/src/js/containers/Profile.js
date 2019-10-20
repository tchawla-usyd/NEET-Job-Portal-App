import React, {Component} from "react";
import { Divider, Avatar, Typography, Input, Icon, Button } from 'antd';

import BaseLayout from '../components/BaseLayout';
import Tags from '../components/Tags';
import Uploader from '../components/Uploader';

const { Title, Text, Paragraph } = Typography;
const para_style = {marginLeft:60,  marginRight: 50, marginBottom: 40, marginTop: 40, fontSize: 18};

class Paragraph_ extends Component {
	constructor(props){
		super(props);
		this.state = {edit: false};
	}

	handleToggle = (e) => {
		if(this.state.edit){
			// Save the change
			console.log(this.state.input);
		}
		this.setState({edit: !this.state.edit});
	}

	render(){
		if(this.state.edit){
			const btn_style={float:'right', marginRight: 20, marginTop: 20, width: 80};
			return(
				<div style={para_style}>
					<Input.TextArea autosize defaultValue={this.props.children} onChange={(e)=>this.setState({input: e.target.value})}/>
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
					<Paragraph>{this.props.children}</Paragraph>
				</div>
			);
		}
	}
}

//Profile Page
export default class Profile extends Component {
	
	onChange = (value) => {
	  //TODO
      console.log(value);
	 }

	render() {
		// const Paragraph_ = (props) => <Paragraph editable={{ onChange: this.onChange }} style={para_style}>{props.children}</Paragraph>;
		const Divider_ = (props) => <Divider orientation="left"><Text style={{fontSize: 30}} strong  >{props.children}</Text></Divider>;
		return(
		<BaseLayout>
			<Avatar size={120} shape="square" style={{margin: 50, color: '#ffffff', backgroundColor: '#666666', fontSize: 55 }}>RS</Avatar>
			<Title style={{display:'inline-block', verticalAlign: 'bottom', marginLeft: 50, marginBottom: 60}} 
			editable={{ onChange: this.onChange }}>Rex Shen</Title>
			<Divider_>Education</Divider_>
			<Paragraph_>USYD - Bachelor of Computer Science</Paragraph_>

			<Divider_>Skills</Divider_>
			<div style={para_style}><Tags /> </div>

			<Divider_>Experience</Divider_>
			<Paragraph_>USYD - Wasting time </Paragraph_>

			<Divider_>Resume</Divider_>
			<div style={{...para_style, width: 200}}><Uploader /></div>
		</BaseLayout>);
	}
}