import React, {Component} from "react";
import axios from 'axios';
import { Divider, Avatar, DatePicker, Typography, Input, Icon, Button } from 'antd';

import BaseLayout from '../components/BaseLayout';
import Paragraph_ from '../components/Paragraph_';
import Tags from '../components/Tags';
import SeekerList from '../components/SeekerList';
import Spinner from '../components/Spinner';

import {GET_JOB} from "../constants/BackendAPI"

const {RangePicker} = DatePicker;
const { Title, Text, Paragraph } = Typography;
const para_style = {marginLeft:60,  marginRight: 50, marginBottom: 40, marginTop: 40, fontSize: 18};

export default class Job extends Component {
    constructor(props) {
        super(props);
        this.state={
        	loading: true
        };
        var urlParams = new URLSearchParams(window.location.search);
        if(!urlParams.has('id')){
        	props.history.push('/home');
        	return;
        }
    	this.id = urlParams.get('id');
        axios.get(GET_JOB + this.id)
        .then(res => { 
        	this.setState({...res.data, loading: false});})
        .catch(function (error) {
		    console.log(error);
		});
    }
    
    componentDidMount(prevProps) {
      window.scrollTo(0, 0);

	}

    render(){
    	const Divider_ = (props) => <Divider orientation="left"><Text style={{fontSize: 30}} strong  >{props.children}</Text></Divider>;
    	let title = this.state.title;
    	return(
    		<BaseLayout> 
    		{this.state.loading ? <Spinner /> :
				<div><Title style={{display:'inline-block', margin: 60, marginBottom: 10}} 
				editable={{ onChange: this.onChange }}>{title}</Title>
				<div style={{marginLeft: 60, marginBottom: 5}}><Icon type="idcard" theme="twoTone" style={{marginRight: 10}}/>Microsoft Inc.</div>
				<div style={{marginLeft: 60, marginBottom: 50}}><Icon type="environment" theme="twoTone" style={{marginRight: 10}}/>{this.state.location}</div>
				
				<Divider_>Description</Divider_>
				<Paragraph_>{this.state.description}</Paragraph_>

				<Divider_>Skills</Divider_>
				<div style={para_style}><Tags skills={this.state.skills}/> </div>

				<Divider_>Application Open</Divider_>
				<RangePicker style={para_style} />

				<Divider_>Applicants</Divider_>
				<SeekerList /></div>
			}
    		</BaseLayout> 
    	)
    }
}