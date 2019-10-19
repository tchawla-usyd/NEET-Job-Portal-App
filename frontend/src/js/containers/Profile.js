import React, {Component} from "react";
import { Divider, Avatar, Typography} from 'antd';

import BaseLayout from '../components/BaseLayout';
import Tags from '../components/Tags';
import Uploader from '../components/Uploader';

const { Title, Text, Paragraph } = Typography;

//Profile Page
export default class Profile extends Component {
	
	onChange = (value) => {
	  //TODO
      console.log(value);
	 }
	render() {
		const para_style = {marginLeft:60,  marginRight: 50, marginBottom: 40, marginTop: 40, fontSize: 20};
		const Paragraph_ = (props) => <Paragraph editable={{ onChange: this.onChange }} style={para_style}>{props.children}</Paragraph>;
		const Divider_ = (props) => <Divider orientation="left"><Text style = {{fontSize: 30}}strong >{props.children}</Text></Divider>;

		return(
		<BaseLayout>
			<Avatar size={120} shape="square" style={{margin: 50, color: '#ffffff', backgroundColor: '#666666', fontSize: 55 }}>RS</Avatar>
			<Title style={{display:'inline-block', verticalAlign: 'bottom', marginLeft: 50, marginBottom: 60}} 
			editable={{ onChange: this.onChange }}>Rex Shen</Title>
			<Divider_>Education</Divider_>
			<Paragraph_>USYD - Bachelor of Computer Science </Paragraph_>

			<Divider_>Skills</Divider_>
			<div style={para_style}><Tags /> </div>

			<Divider_>Experience</Divider_>
			<Paragraph_>USYD - Wasting time </Paragraph_>

			<Divider_>Resume</Divider_>
			<div style={{...para_style, width: 200}}><Uploader /></div>
		</BaseLayout>);
	}
}