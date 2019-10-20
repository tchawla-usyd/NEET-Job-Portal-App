import React, {Component} from "react";
import { Divider, Avatar, DatePicker, Typography, Input, Icon, Button } from 'antd';

import BaseLayout from '../components/BaseLayout';
import Paragraph_ from '../components/Paragraph_';
import Tags from '../components/Tags';
import SeekerList from '../components/SeekerList';

const {RangePicker} = DatePicker;
const { Title, Text, Paragraph } = Typography;
const para_style = {marginLeft:60,  marginRight: 50, marginBottom: 40, marginTop: 40, fontSize: 18};

export default class Job extends Component {
    constructor(props) {
        super(props);

    }
    
    componentDidMount(prevProps) {
      window.scrollTo(0, 0);
	}

    render(){
    	const Divider_ = (props) => <Divider orientation="left"><Text style={{fontSize: 30}} strong  >{props.children}</Text></Divider>;
    	return(
    		<BaseLayout>
				<Title style={{display:'inline-block', margin: 60, marginBottom: 10}} 
				editable={{ onChange: this.onChange }}>Software Developer</Title>
				<div style={{marginLeft: 60, marginBottom: 5}}><Icon type="idcard" theme="twoTone" style={{marginRight: 10}}/>Microsoft Inc.</div>
				<div style={{marginLeft: 60, marginBottom: 50}}><Icon type="environment" theme="twoTone" style={{marginRight: 10}}/>Sydney, NSW, Australia</div>
				
				<Divider_>Description</Divider_>
				<Paragraph_>Blah Blah Blah, You are hired</Paragraph_>

				<Divider_>Skills</Divider_>
				<div style={para_style}><Tags /> </div>

				<Divider_>Application Open</Divider_>
				<RangePicker style={para_style} />

				<Divider_>Applicants</Divider_>
				<SeekerList />
    		</BaseLayout>
    	)
    }
}