import React, {Component} from "react";
import axios from 'axios';
import qs from 'querystring';
import { Divider, Avatar, Typography, Input, Icon, Button, message} from 'antd';

import BaseLayout from '../components/BaseLayout';
import Uploader from '../components/Uploader';
import Paragraph_ from '../components/Paragraph_';
import Tags from '../components/Tags';
import Spinner from '../components/Spinner';
import JobListing from '../components/JobListing';


import {GET_CAN, GET_CAN_SKILLS, GET_JOB_FOR, EDIT_USER, GET_USER, HEADER} from "../constants/BackendAPI"
const { Title, Text, Paragraph } = Typography;
const para_style = {marginLeft:60,  marginRight: 50, marginBottom: 40, marginTop: 40, fontSize: 18};

//Profile Page
export default class Profile extends Component {
	
	constructor(props){
		super(props);

		// user info
        const userInfo = this.props.userInfo;

		this.isEmployer = userInfo.isEmployer;
		this.userId = userInfo.id;

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

		this.profileId = urlParams.get('id');
		this.self = this.userId == this.profileId;
        
		axios.get(GET_USER + this.profileId, this.headers)
	      .then(res => {
	        if (res.status == 200){
	          this.setState({ name: res.data.firstName + ' ' + res.data.lastName, 
	          	basicInfo:{
	          		email: res.data.email,
	          	},
	            isEmployer: res.data.userTypeID == 3 ? true : false}, this.getDetails);
	        }
	    }).catch(function (error) {
            message.err("Something is wrong!");
		    console.log(error);
		});;

        
	}

	// get user details: if user is employer/candidate
	getDetails = () =>{
		if(this.state.isEmployer) {
			this.setState({loading_profile: false, loading_skills: false});
		} else{
			axios.get(GET_CAN + this.profileId, this.headers)
	        .then(res => { 
	        	this.setState({...res.data, 
	        		loading_profile: false});
	        });

	        axios.get(GET_CAN_SKILLS + this.profileId, this.headers)
	        .then(res => { 
	        	this.setState({skills: res.data, 
	        		loading_skills: false});
	        });
		}
	}

	// post changes
	handleSubmit = (payload) => {
        axios.post(EDIT_USER, qs.stringify({"userId": this.profileId, ...payload}), this.headers)
        .then(res => {
            if (res.status == 200) {
                this.setState({candidateInfo: Object.assign({}, this.state.candidateInfo, payload)});
                message.success('Changes Saved');
            }else{
                message.error("Something is wrong !");
            }
        })
    }


	componentDidMount(prevProps) {
      window.scrollTo(0, 0);
	}

	// get initials of user
	getInit = (name) =>{
		var initials = name.match(/\b\w/g) || [];
		initials = ((initials.shift() || '') + (initials.pop() || '')).toUpperCase();
		return initials;
	}

	render() {
		console.log(this.state);
		const Divider_ = (props) => <Divider orientation="left"><Text style={{fontSize: 30}} strong >{props.children}</Text></Divider>;
		return(
		<BaseLayout parentProps={this.props}>
		{this.state.loading_profile ||  this.state.loading_skills ? <Spinner /> :
			<div>
			<Avatar size={200} shape="square" style={{margin: 50, color: '#ffffff', backgroundColor: '#666666', fontSize: 80 }}>{this.getInit(this.state.name)}</Avatar>
			
			<div style={{display:'inline-block', verticalAlign: 'bottom', marginLeft: 50, marginBottom: 40}}>
				<Title>{this.state.name}</Title>
				<Icon type="mail" theme="twoTone" /><a href={"mailto:" + 123} style={{marginLeft:10}}>{this.state.basicInfo.email}</a>
			</div>
			
			{this.state.isEmployer 
			? <div><Divider_>Jobs From The Company</Divider_><div style={para_style}><JobListing companyId={this.profileId} parentProps={this.props}/></div></div>
			: <div><Divider_>Education</Divider_>
			<Paragraph_ editable={this.self} name='education' handleSubmit={this.handleSubmit} >{this.state.candidateInfo.education == null ? "" : this.state.candidateInfo.education}</Paragraph_>

			<Divider_>Skills</Divider_>
			<div style={para_style}><Tags editable={this.self} onChange={this.handleSubmit} skills={this.state.skills}/> </div>

			<Divider_>Experience</Divider_>
			<Paragraph_ editable={this.self} name='experience' handleSubmit={this.handleSubmit} >{this.state.candidateInfo.experience == null ? "" : this.state.candidateInfo.experience}</Paragraph_>

			<Divider_>Resume</Divider_>
			<div style={{...para_style, width: 200}}><Uploader editable={this.self} resume={this.state.candidateInfo.resume} /></div>
			</div>}
			</div>
		}
		</BaseLayout>);
	}
}