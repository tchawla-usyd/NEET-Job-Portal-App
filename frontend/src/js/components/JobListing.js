import React, {Component} from "react";
import { Table, Divider, Tag, Input, Button, Icon, Typography, message, Pagination} from 'antd';
import { Link } from 'react-router-dom'

const { Search } = Input;
const { Text } = Typography;

const data = [
  {
    key: '1',
    title: 'Software Dev',
    company: 'Microsoft',
    location: 'Sydney',
    skills: ['nice', 'developer'],
    like: false
  },
  {
    key: '2',
    title: 'Tester',
    company: 'Google',
    location: 'Melbourne',
    skills: ['loser'],
    like: false
  },
  {
    key: '3',
    title: 'Whooooohla',
    company: 'Oracle',
    location: 'Brisbane',
    skills: ['cool', 'teacher'],
    like: true
  },
];

//dummy
const userSkills = ['cool', 'teacher', 'developer'];
const isEmployer = true;

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
          render: text => <Text strong><Link to='/job'>{text}</Link></Text>,
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
          sorter: (a, b) => a.skills.filter(skill => userSkills.includes(skill)).length - 
            b.skills.filter(skill => userSkills.includes(skill)).length ,
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
              })}
            </span>
          ),
        },{
          title: 'Action',
          key: 'action',
          align: 'center',
          render: (text, record) => (
            <span>
            <Button type= 'link' onClick={(e) => this.handleClickLike(e, record)}>
                {record.like ?  <Icon style={{fontSize: 18}} type="heart" theme='twoTone' twoToneColor="#eb2f96" />
                 : <Icon style={{fontSize: 18, color: '#666666'}} type="heart" />}
              </Button>
              <Divider style={{fontSize: 20}} type="vertical" />
              <Link to='' style={{fontSize: 15}}>Apply</Link>
            </span>
          ),
        },
      ];

      this.columns = isEmployer ? columns.slice(0, -1) : columns;

      this.state = {
        jobs: data, 
        filteredJobs: data,
      }
    }

    handleClickLike = (e, record) => {
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
            return title.startsWith(value) || company.startsWith(value) || matchSkills;
          });
      this.setState({
        filteredJobs: filteredJobs
      });
    }


    render(){
      // const onRow=(record, rowIndex) => {
      //   return {
      //     onClick: ()=>console.log(rowIndex)
      //   }
      // };
      return (
        <div>
          {/* Seach Bar */}
          <Search placeholder="input search text" 
          style={{ width: 300 }}
          onChange={(e)=> this.handleSearch(e.target.value)}
          onSearch={(value)=> this.handleSearch(value)}
          enterButton={<Button style={{ width: 60 }} icon="search" />} />

          {/* Post Button */}
          {isEmployer ? <Link to='/post' style={{float: 'right'}}><Button shape="round" size='large'>Post</Button></Link> : ''}

          {/* Table */}
          <Table style={{marginTop: 20}} columns={this.columns} 
          dataSource={this.state.filteredJobs} pagination={{ defaultPageSize: 20, simple: true}}/>
        </div>
        );
    }
}
