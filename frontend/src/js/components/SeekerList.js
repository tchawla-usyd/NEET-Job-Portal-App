import React, {Component} from "react";
import { Table, Divider, Tag, Input, Button, Icon, Typography, message} from 'antd';
import { Link } from 'react-router-dom'

const { Search } = Input;
const { Text } = Typography;

const data = [
  {
    key: '1',
    name: 'Rex Shen',
    email: '123@gmail.com',
    skills: ['nice', 'developer'],
  }
];

export default class SeekerListing extends Component {
  
    constructor(props) {
        super(props);
        let isEmployer = true;
        this.columns = [
        {
          title: 'Candidate',
          dataIndex: 'name',
          key: 'name',
          align: 'center',
          render: text => <Text strong><Link to='/profile'>{text}</Link></Text>,
        },{
          title: 'Email Address',
          dataIndex: 'email',
          key: 'email',
          align: 'center',
          render: text => <a href={"mailto:" + text}>{text}</a>
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
              })}
            </span>
          ),
        }
      ];
    }

    handleClickLike = (e, record) => {
      record.like = !record.like;
      //TODO: add to db

      if(record.like){
        message.success(record.title + ' Saved');
      }
      this.forceUpdate();
    }
    
    handleFilter = (value) => {
      //TODO
      console.log(value);
    }

    render(){
      // const onRow=(record, rowIndex) => {
      //   return {
      //     onClick: ()=>console.log(rowIndex)
      //   }
      // };
      return (
          <Table style={{marginLeft:60,  marginRight: 50, marginBottom: 40, marginTop: 40}} columns={this.columns} dataSource={data} pagination={false}/>
        );
    }
}
