import React, {Component} from "react";
import axios from 'axios';
import { Table, Divider, Tag, Input, Button, Icon, Typography, message} from 'antd';
import { Link } from 'react-router-dom';

import {GET_CANS_FOR, HEADER} from '../constants/BackendAPI';

const { Search } = Input;
const { Text } = Typography;

export default class SeekerListing extends Component {
  
    constructor(props) {
        super(props);

        // Auth
        this.token = localStorage.getItem("token");
        this.headers = {headers:{...HEADER, 'Authorization': this.token}};

        //columns for the table
        this.columns = [
        {
          title: 'Candidate',
          dataIndex: 'name',
          key: 'name',
          align: 'center',
          render: (text, record) => <Text strong><Link to={'/profile?id=' + record.key}>{text}</Link></Text>,
        },{
          title: 'Email Address',
          dataIndex: 'email',
          key: 'email',
          align: 'center',
          render: text => <a href={"mailto:" + text}>{text}</a>
        },{
          title: 'Applied At',
          dataIndex: 'applyDate',
          key: 'applyDate',
          align: 'center',
        },{
          title: 'Skills',
          dataIndex: 'skills',
          key: 'skills',
          align: 'center',
          render: tags => (
            <span>
              {tags.map(tag => {
                let color = tag.length > 5 ? 'geekblue' : 'green';
                if (tag === 'loser') {
                  color = 'volcano';
                }
                return (
                  <Tag color={color} key={tag}>
                    {tag.toUpperCase()}
                  </Tag>
                );
              }).slice(0, 5)}
            </span>
          ),
        }
      ];

      this.state = {data: []};
      //get applicants for job
      axios.get(GET_CANS_FOR + this.props.job_id, this.headers)
        .then(res => { 
          this.setState({data: res.data.map((entry) => {
          return({
            key: entry.user_id,
            name: entry.first_name + ' ' + entry.last_name,
            email: entry.email,
            skills: entry.skills.map(skill => skill.name),
            applyDate: entry.applyDate});
          })});
        })
        .catch(function (error) {
          console.log(error);
      });
    }

    render(){
      console.log();
      return <Table style={{marginLeft:60,  marginRight: 50, marginBottom: 40, marginTop: 40}} 
        columns={this.columns} dataSource={this.state.data} pagination={false}/>;
    }
}
