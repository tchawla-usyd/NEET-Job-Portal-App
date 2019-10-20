import React, {Component} from "react";
import { Divider, Avatar, Typography, Input, Icon, Button } from 'antd';

import BaseLayout from '../components/BaseLayout';
import Uploader from '../components/Uploader';
import Paragraph_ from '../components/Paragraph_';
import Tags from '../components/Tags';

const { Title, Text, Paragraph } = Typography;
const para_style = {marginLeft:60,  marginRight: 50, marginBottom: 40, marginTop: 40, fontSize: 18};

//Profile Page
export default class Profile extends Component {
	
	onChange = (value) => {
	  //TODO
      console.log(value);
	 }

	componentDidMount(prevProps) {
      window.scrollTo(0, 0);
	}

	render() {
		// const Paragraph_ = (props) => <Paragraph editable={{ onChange: this.onChange }} style={para_style}>{props.children}</Paragraph>;
		const Divider_ = (props) => <Divider orientation="left"><Text style={{fontSize: 30}} strong  >{props.children}</Text></Divider>;
		return(
		<BaseLayout>
			<Avatar size={120} shape="square" style={{margin: 50, color: '#ffffff', backgroundColor: '#666666', fontSize: 55 }}>RS</Avatar>
			
			<div style={{display:'inline-block', verticalAlign: 'bottom', marginLeft: 50, marginBottom: 40}}>
				<Title editable={{ onChange: this.onChange }}>Rex Shen</Title>
				<Icon type="mail" theme="twoTone" /><a href={"mailto:" + 123} style={{marginLeft:10}}>123456789@gmail.com </a>
			</div>
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