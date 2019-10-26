import React, {Component} from "react";
import axios from 'axios';
import qs from 'querystring';
import moment from 'moment';

import { Divider, Avatar, DatePicker, Typography, Input, Icon, Button, message } from 'antd';

import BaseLayout from '../components/BaseLayout';
import Paragraph_ from '../components/Paragraph_';
import Tags from '../components/Tags';
import SeekerList from '../components/SeekerList';
import Spinner from '../components/Spinner';

import {GET_JOB, EDIT_JOB, HEADER} from "../constants/BackendAPI"

const {RangePicker} = DatePicker;
const { Title, Text, Paragraph } = Typography;
const para_style = {marginLeft:60,  marginRight: 50, marginBottom: 40, marginTop: 40, fontSize: 18};


export default class Job extends Component {
    constructor(props) {
        super(props);
        //user info
        const userInfo = this.props.userInfo;

        //Auth
        this.token = localStorage.getItem("token");
        this.headers = {headers:{...HEADER, 'Authorization': this.token}};

        this.state={
        	loading: true
        };
        var urlParams = new URLSearchParams(window.location.search);
        if(!urlParams.has('id')){
        	props.history.push('/home');
        	return;
        }
    	this.id = urlParams.get('id');
        axios.get(GET_JOB + this.id, this.headers)
        .then(res => { 
            console.log(res);
        	this.setState({...res.data, 
                self: res.data.created_by == userInfo.id,
                loading: false});
        })
        .catch(function (error) {
		    console.log(error);
		});
    }
    
    componentDidMount(prevProps) {
      window.scrollTo(0, 0);
	}

    handleSubmit = (payload) => {
        console.log(payload);
        axios.post(EDIT_JOB, qs.stringify({"job_id": this.id, ...payload}), this.headers)
        .then(res => {
            if (res.status == 200) {
                this.setState({payload});
                message.success('Changes Saved');
            }else{
                message.error("Something is wrong !");
            }
        })
    }

    render(){
    	const Divider_ = (props) => <Divider orientation="left"><Text style={{fontSize: 30}} strong  >{props.children}</Text></Divider>;
    	let title = this.state.title;
        console.log(this.state.self);
    	return(
    		<BaseLayout parentProps={this.props}> 
    		{this.state.loading ? <Spinner /> :
				<div><Title style={{display:'inline-block', margin: 60, marginBottom: 10}} 
				editable={this.state.self ? { 
                    onChange: (s)=>this.handleSubmit({title:s}),
                } : false}>{title}</Title>
				<div style={{marginLeft: 60, marginBottom: 5}}><Icon type="idcard" theme="twoTone" style={{marginRight: 10}}/>{this.state.companyInfo.companyName}</div>
				<div style={{marginLeft: 60, marginBottom: 50}}><Icon type="environment" theme="twoTone" style={{marginRight: 10}}/>{this.state.location}</div>
				
				<Divider_>Description</Divider_>
				<Paragraph_ editable={this.state.self} name='description' handleSubmit={this.handleSubmit}>{this.state.description}</Paragraph_>

				<Divider_>Skills</Divider_>
				<div style={para_style}><Tags editable={this.state.self} onChange={this.handleSubmit} skills={this.state.skills}/> </div>

				<Divider_>Application Open</Divider_>
				<RangePicker 
                style={para_style} 
                disabled={!this.state.self}
                onChange={(moments, dates) => this.handleSubmit(
                    {start_date: moment(moments[0]).format('YYYY-MM-DD'),
                    end_date: moment(moments[1]).format('YYYY-MM-DD')})
                } defaultValue={[moment(this.state.startDate, 'YYYY-MM-DD'), moment(this.state.endDate, 'YYYY-MM-DD')]}/>

				{this.state.self ? <div><Divider_>Applicants</Divider_>
				<SeekerList skills={this.state.skills} job_id={this.id}/></div> : ''}
                </div>
			}
    		</BaseLayout> 
    	)
    }
}