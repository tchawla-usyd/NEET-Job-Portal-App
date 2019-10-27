import React, {Component} from "react";
import axios from 'axios';
import qs from 'querystring';
import { Table, Divider, Tag, Input, Button, Icon, Typography, message, Pagination, Dropdown, Menu} from 'antd';
import { Link } from 'react-router-dom'

import {APPLY, GET_JOB_FOR, GET_CAN_SKILLS, GET_APPLIED, GET_ALL, HEADER} from "../constants/BackendAPI"
import Spinner from '../components/Spinner';

import moment from 'moment';


const { Search } = Input;
const { Text } = Typography;

export default class JobListing extends Component {
  
    constructor(props) {
        super(props);

        // route info
        this.isApplied = this.props.parentProps.location.pathname == '/application';
        this.isFav = this.props.parentProps.location.pathname == '/favorite';
        this.token = localStorage.getItem("token");
        this.headers = {headers:{...HEADER, 'Authorization': this.token}};

        // user info
        const userInfo = this.props.parentProps.userInfo;
        this.userId = userInfo.id;
        this.isEmployer = userInfo.isEmployer;
        this.state = {userSkills: []};

        if(!this.isEmployer){
          axios.get(GET_CAN_SKILLS + this.userId, this.headers)
          .then(res => { 
            this.setState({userSkills: res.data.map(skill => skill.name)});
          })
        }

        // Columns info
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
          render: (text, record) => <Text strong><Link to={'/company?id='+ record.company_id}>{text}</Link></Text>,
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
          sorter: this.isEmployer ? null : (a, b) => a.skills.filter(skill => this.state.userSkills.includes(skill)).length - 
            b.skills.filter(skill => this.state.userSkills.includes(skill)).length,
          render: tags => (
            <span>
              {tags.map(tag => {
                const color = ["magenta", "red", "volcano", "orange", "gold", "lime", "green", "cyan", "blue", "geekblue", "purple"];
                return (
                  <Tag color={!(this.isEmployer || this.state.userSkills.includes(tag)) ? "#d9d9d9" : color[Math.floor(Math.random()*color.length)]} key={tag}>
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
              {record.applied ? <span style={{fontSize: 15}}><Icon type="check-circle" theme="twoTone" twoToneColor="#52c41a" /> Applied</span>
                : <Button type= 'link' onClick={(e) => this.handleApply(record)} style={{fontSize: 15}}>Apply</Button>}
            </span>
          ),
        },
      ];
      this.columns = this.isEmployer ? columns.slice(0, -1) : columns;

      this.locations = [];
      this.state = {loading: true};
      axios.get(this.isEmployer ? (GET_JOB_FOR + this.userId) : GET_ALL, this.headers)
        .then(res => { 
        var jobs = res.data.map(job =>{
            return {key: job.uid,
            title: job.title,
            company: job.companyInfo.companyName,
            company_id: job.created_by,
            location: job.location,
            skills: job.skills.map(skill => skill.name),
            like: this.isFav,
            applied: false,
            end_date: moment(job.endDate, "YYYY-MM-DD")
          }});
        if(!this.isEmployer){
          jobs = this.getFilteredJobs(jobs);
          
        }else{
          this.setState({
          jobs: jobs, 
          filteredJobs: jobs,
          filteredSearch: jobs,}, 
          this.sortLocations);
        }
      }).catch(function (error) {
        console.log(error);
        message.err("Something is wrong!");
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
        sortJobBy: 'Clear',
        loading: false,
      });
    }

    getFilteredJobs = (jobs) =>{
      axios.get(GET_APPLIED + this.userId, this.headers)
        .then(res => { 
        var applied_jobs = res.data;
        jobs.forEach((job) =>{
          applied_jobs.forEach(({uid}) =>{
            if(job.key == uid) job.applied = true;
          });
        });
        if(this.isApplied){
          jobs = jobs.filter((job)=> job.applied == true);
        }
        if(this.props.companyId){
          jobs = jobs.filter((job)=> job.company_id == this.props.companyId);
        }

        this.setState({
        jobs: jobs, 
        filteredJobs: jobs,
        filteredSearch: jobs}, 
        this.sortLocations);
      }).catch(function (error) {
        console.log(error);
        message.err("Something is wrong!");
      });
    }

    handleApply = (record) =>{
      axios.post(APPLY, qs.stringify({job_id: record.key}), this.headers)
        .then(res => {
          message.success("Apply Success!");
          record.applied = true;
          this.forceUpdate();
        }).catch(function (error) {
          console.log(error);
          message.err("Something is wrong!");
        });;
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

    sortRelevance = (target) => {
      if(target == 'EndDate'){
        this.setState({
          sortJobBy: 'Date',
          filteredJobs: this.state.filteredJobs.sort( (a, b) => a.end_date.valueOf() - b.end_date.valueOf())
        });
      }
      else if(target == "Relevance") {
        this.setState({
          sortJobBy: 'Relevance',
          filteredJobs: this.state.filteredJobs.sort( (b, a) => a.skills.filter(skill => this.state.userSkills.includes(skill)).length - 
            b.skills.filter(skill => this.state.userSkills.includes(skill)).length)
        });
      }
      else {
        this.setState({
          sortJobBy: 'Clear',
          filteredJobs: this.state.filteredJobs.sort( (a, b) => a.key - b.key)
        });
      }
        
    }
      
    render(){
      const locationMenu = 
        <Menu>
          {this.locations.map((location) => <Menu.Item onClick={(e)=>this.onFilterLocation(location)} style={{textAlign: 'center'}} key={location}><Button type='link'>{location}</Button></Menu.Item>)}
          <Menu.Item onClick={(e)=>this.onFilterLocation('Clear')} style={{textAlign: 'center'}} key='clear'><Button type='link' >Clear</Button></Menu.Item>
        </Menu>;

      const sortMenu = 
        <Menu>
          <Menu.Item onClick={(e)=>this.sortRelevance('Relevance')}style={{textAlign: 'center'}} key='relevance'><Button type='link'>Relevance</Button></Menu.Item>
          <Menu.Item onClick={(e)=>this.sortRelevance('EndDate')} style={{textAlign: 'center'}} key='endDate'><Button type='link' >Date</Button></Menu.Item>
          <Menu.Item onClick={(e)=>this.sortRelevance('Clear')} style={{textAlign: 'center'}} key='clear'><Button type='link' >Clear</Button></Menu.Item>

        </Menu>;

      return (
        <div>
          <div><Text style={{fontSize: 30}}>{this.isApplied ? 'Your Applications' : this.isFav ? 'Your Favorite' : this.props.companyId ? '' : 'All Jobs'}</Text></div>
          {/* Seach Bar */}
          <Search placeholder="input search text" 
          style={{ width: 300, marginTop: 20}}
          onChange={(e)=> this.handleSearch(e.target.value)}
          onSearch={(value)=> this.handleSearch(value)}
          enterButton={<Button style={{ width: 60 }} icon="search" />} />

          { /* Filter By */}
          <span style={{marginLeft: 20, verticalAlign: 'bottom'}}>
          <Text style={{marginRight: 5}}>Filtered By: </Text>
            <Dropdown overlay={locationMenu} trigger={['click']} placement="bottomCenter">
              <Button type='link'>
                {this.state.filterLocationBy} <Icon type="down" />
              </Button>
            </Dropdown>
          </span>

        { /* Sort By */}
          <span style={{marginLeft: 20, verticalAlign: 'bottom'}}>
          <Text style={{marginRight: 5}}>Sort By: </Text>
            <Dropdown overlay={sortMenu} trigger={['click']} placement="bottomCenter">
              <Button type='link'>
                {this.state.sortJobBy} <Icon type="down" />
              </Button>
            </Dropdown>
          </span>


          {/* Post Button */}
          {this.isEmployer ? <Link to='/post' style={{float: 'right'}}><Button shape="round" size='large'>Post</Button></Link> : ''}

          {/* Table */}
          {this.state.loading ? <Spinner /> :
          <Table style={{marginTop: 20}} columns={this.columns} 
          dataSource={this.state.filteredJobs} pagination={{ defaultPageSize: 20, simple: true}}/>}
        </div>
        );
    }
}
