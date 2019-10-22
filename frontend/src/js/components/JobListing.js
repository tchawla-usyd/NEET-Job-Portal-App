import React, {Component} from "react";
import axios from 'axios';
import qs from 'querystring';
import { Table, Divider, Tag, Input, Button, Icon, Typography, message, Pagination, Dropdown, Menu} from 'antd';
import { Link } from 'react-router-dom'

import {APPLY, GET_JOB_FOR, HEADER} from "../constants/BackendAPI"
import Spinner from '../components/Spinner';

const { Search } = Input;
const { Text } = Typography;

//dummy
const userSkills = ['jobs', 'teacher', 'developer'];
const isEmployer = false;

export default class JobListing extends Component {
  
    constructor(props) {
        super(props);
        let columns = [
        {
          title: 'Job Title',
          dataIndex: 'title',
          key: 'title',
          align: 'center',
          sorter: (a, b) => a.title.localeCompare(b.title),
          render: (text, record) => <Text strong><Link to={'/job?id='+ record.key}>{text}</Link></Text>,
        },{
          title: 'Company',
          dataIndex: 'company',
          key: 'company',
          align: 'center',
          sorter: (a, b) => a.company.localeCompare(b.company),
        },{
          title: 'Location',
          dataIndex: 'location',
          key: 'location',
          align: 'center',
          sorter: (a, b) => a.location.localeCompare(b.location),
        },{
          title: 'Skills Wanted',
          dataIndex: 'skills',
          key: 'skills',
          align: 'center',
          sorter: isEmployer ? null : (a, b) => a.skills.filter(skill => userSkills.includes(skill)).length - 
            b.skills.filter(skill => userSkills.includes(skill)).length,
          render: tags => (
            <span>
              {tags.map(tag => {
                const color = ["magenta", "red", "volcano", "orange", "gold", "lime", "green", "cyan", "blue", "geekblue", "purple"];
                return (
                  <Tag color={color[Math.floor(Math.random()*color.length)]} key={tag}>
                    {tag.toUpperCase()}
                  </Tag>
                );
              }).slice(0, 5)}
            </span>
          ),
        },{
          title: 'Action',
          key: 'action',
          align: 'center',
          render: (text, record) => (
            <span>
            <Button type= 'link' onClick={(e) => this.handleClickLike(record)}>
                {record.like ?  <Icon style={{fontSize: 18}} type="heart" theme='twoTone' twoToneColor="#eb2f96" />
                 : <Icon style={{fontSize: 18, color: '#666666'}} type="heart" />}
              </Button>
              <Divider style={{fontSize: 20}} type="vertical" />
              <Button type= 'link' onClick={(e) => this.handleApply(record.key)} style={{fontSize: 15}}>Apply</Button>
            </span>
          ),
        },
      ];
      this.columns = isEmployer ? columns.slice(0, -1) : columns;

      this.locations = [];
      this.state = {loading: true};
      axios.get(GET_JOB_FOR + 1)
        .then(res => { 
        var jobs = res.data.map(job =>{
              return {key: job.uid,
              title: job.title,
              company: 'Micro',
              location: job.location,
              skills: job.skills.map(skill => skill.name),
              like: false,
            }});
          this.setState({
            jobs: jobs, 
            filteredJobs: jobs,
            filteredSearch: jobs}, 
            this.sortLocations);
        }).catch(function (error) {
          console.log(error);
      });
    }

    sortLocations = () => {
      this.locations = [...new Set(this.state.jobs.map((job) => job.location))];
      this.locationJobs = new Object();
      this.locations.map((target => this.locationJobs[target] = 
      this.state.jobs.filter(({location}) => location.toLowerCase() == target.toLowerCase())));
      this.locationJobs['Location'] = this.state.jobs;
      this.setState({
            filterLocationBy: 'Location',
            loading: false,
          });
    }

    handleApply = (job_id) =>{
      axios.post(APPLY, qs.stringify({job_id: job_id}), HEADER)
        .then(res => {
          message.success("Apply Success!");
        });
    }

    handleClickLike = (record) => {
      record.like = !record.like;
      //TODO: add to db

      if(record.like){
        message.success(record.title + ' Saved');
      }
      this.forceUpdate();
    }
    
    handleSearch = (value) => {
      const filteredJobs = this.state.jobs.filter(({ title, company, location, skills}) =>
        {
            //row data
            title = title.toLowerCase();
            company = company.toLowerCase();
            location = location.toLowerCase();
            //target
            value = value.toLowerCase();

            let matchSkills = false;
            skills.map((skill) => {
              if(skill.toLowerCase().startsWith(value)) matchSkills = true;
            })

            //matching all the title, company, location and skills starts with the value
            return title.startsWith(value) || location.startsWith(value) || company.startsWith(value) || matchSkills;
          });
      this.setState({
        filteredSearch: filteredJobs,
        filteredJobs: filteredJobs.filter(value => this.locationJobs[this.state.filterLocationBy].includes(value))
      });
    }

    onFilterLocation = (target) =>{
      if(target == 'Clear'){
        this.setState({
          filterLocationBy: 'Location',
          filteredJobs: this.state.filteredSearch
        });
      }else{
        const filteredJobs = 
        this.setState({
          filterLocationBy: target,
          filteredJobs: this.state.filteredSearch.filter((job) => target == job.location)
        });
      }
      
    }

    render(){
      const locationMenu = 
        <Menu>
          {this.locations.map((location) => <Menu.Item onClick={(e)=>this.onFilterLocation(location)} style={{textAlign: 'center'}} key={location}><Button type='link'>{location}</Button></Menu.Item>)}
          <Menu.Item onClick={(e)=>this.onFilterLocation('Clear')} style={{textAlign: 'center'}} key='clear'><Button type='link' >Clear</Button></Menu.Item>
        </Menu>;

      return (
        <div>
          {/* Seach Bar */}
          <Search placeholder="input search text" 
          style={{ width: 300 }}
          onChange={(e)=> this.handleSearch(e.target.value)}
          onSearch={(value)=> this.handleSearch(value)}
          enterButton={<Button style={{ width: 60 }} icon="search" />} />

          { /* Filter By */}
          <span style={{marginLeft: 20, verticalAlign: 'bottom'}}>
          <Text style={{marginRight: 5}}>Filtered By: </Text>
            <Dropdown overlay={locationMenu} trigger='click' placement="bottomCenter">
              <Button type='link'>
                {this.state.filterLocationBy} <Icon type="down" />
              </Button>
            </Dropdown>
          </span>


          {/* Post Button */}
          {isEmployer ? <Link to='/post' style={{float: 'right'}}><Button shape="round" size='large'>Post</Button></Link> : ''}

          {/* Table */}
          {this.state.loading ? <Spinner /> :
          <Table style={{marginTop: 20}} columns={this.columns} 
          dataSource={this.state.filteredJobs} pagination={{ defaultPageSize: 20, simple: true}}/>}
        </div>
        );
    }
}
