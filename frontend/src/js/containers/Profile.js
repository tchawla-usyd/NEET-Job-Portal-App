import React, {Component} from "react";
import axios from 'axios';
import { Divider, Avatar, Typography, Input, Icon, Button } from 'antd';

import BaseLayout from '../components/BaseLayout';
import Uploader from '../components/Uploader';
import Paragraph_ from '../components/Paragraph_';
import Tags from '../components/Tags';
import Spinner from '../components/Spinner';

import {GET_CAN, GET_CAN_SKILLS, HEADER} from "../constants/BackendAPI"
const { Title, Text, Paragraph } = Typography;
const para_style = {marginLeft:60,  marginRight: 50, marginBottom: 40, marginTop: 40, fontSize: 18};

const isEmployer = true;
//Profile Page
export default class Profile extends Component {
	
	constructor(props){
		super(props);

		// Auth
		this.token = localStorage.getItem("token");
        this.headers = {headers:{...HEADER, 'Authorization': this.token}};

		this.state={
        	loading_profile: true,
        	loading_skills: true
        };

		var urlParams = new URLSearchParams(window.location.search);
        if(!urlParams.has('id')){
        	props.history.push('/home');
        	return;
        }

		this.id = urlParams.get('id');
        axios.get(GET_CAN + this.id, this.headers)
        .then(res => { 
        	this.setState({...res.data, 
        		name: res.data.basicInfo.firstName + ' ' + res.data.basicInfo.lastName,
        		loading_profile: false});
        })

        axios.get(GET_CAN_SKILLS + this.id, this.headers)
        .then(res => { 
        	this.setState({skills: res.data, 
        		loading_skills: false});
        })

	}

	onChange = (value) => {
	  //TODO
      console.log(value);
	 }



	componentDidMount(prevProps) {
      window.scrollTo(0, 0);
	}

	getInit = (name) =>{
		var initials = name.match(/\b\w/g) || [];
		initials = ((initials.shift() || '') + (initials.pop() || '')).toUpperCase();
		return initials;
	}

	render() {
		console.log(this.state);
		// const Paragraph_ = (props) => <Paragraph editable={{ onChange: this.onChange }} style={para_style}>{props.children}</Paragraph>;
		const Divider_ = (props) => <Divider orientation="left"><Text style={{fontSize: 30}} strong  >{props.children}</Text></Divider>;
		return(
		<BaseLayout parentProps={this.props}>
		{this.state.loading_profile ||  this.state.loading_skills ? <Spinner /> :
			<div>
			<Avatar size={200} shape="square" style={{margin: 50, color: '#ffffff', backgroundColor: '#666666', fontSize: 80 }}>{this.getInit(this.state.name)}</Avatar>
			
			<div style={{display:'inline-block', verticalAlign: 'bottom', marginLeft: 50, marginBottom: 40}}>
				<Title editable={isEmployer ? false : { onChange: this.onChange }}>{this.state.name}</Title>
				<Icon type="mail" theme="twoTone" /><a href={"mailto:" + 123} style={{marginLeft:10}}>{this.state.basicInfo.email}</a>
			</div>
			<Divider_>Education</Divider_>
			<Paragraph_>{this.state.candidateInfo.education == null ? "" : this.state.candidateInfo.education}</Paragraph_>

			<Divider_>Skills</Divider_>
			<div style={para_style}><Tags skills={this.state.skills}/> </div>

			<Divider_>Experience</Divider_>
			<Paragraph_>{this.state.candidateInfo.experience == null ? "" : this.state.candidateInfo.experience}</Paragraph_>

			<Divider_>Resume</Divider_>
			<div style={{...para_style, width: 200}}><Uploader resume={this.state.candidateInfo.resume} /></div>
			</div>
		}
		</BaseLayout>);
	}
}