import React, {Component} from "react";
import { Table, Divider, Tag, Input, Button, Icon, Typography, message} from 'antd';
import { Link } from 'react-router-dom'

const { Search } = Input;
const { Text } = Typography;

const data = [
  {
    key: '1',
    title: 'Software Dev',
    location: 'Sydney',
    skills: ['nice', 'developer'],
    like: false
  },
  {
    key: '2',
    title: 'Tester',
    location: 'Melbourne',
    skills: ['loser'],
    like: false
  },
  {
    key: '3',
    title: 'Whooooohla',
    location: 'Brisbane',
    skills: ['cool', 'teacher'],
    like: true
  },
];

export default class Home extends Component {
  
    constructor(props) {
        super(props);
        this.columns = [
        {
          title: 'Job Title',
          dataIndex: 'title',
          key: 'title',
          align: 'center',
          render: text => <Text strong><Link to='/job'>{text}</Link></Text>,
        },
        {
          title: 'Location',
          dataIndex: 'location',
          key: 'location',
          align: 'center',
        },
        {
          title: 'Skills Wanted',
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
        },
        {
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
    }

    handleClickLike = (e, record) => {
      record.like = !record.like;
      //TODO: add to db

      if(record.like){
        message.success(record.title + ' Saved To Favorite');
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
        <div>
          {/* Seach Bar */}
          <Search placeholder="input search text" 
          style={{ width: 200 }} onSearch={value => console.log(value)} 
          onChange={(e)=> this.handleFilter(e.target.value)}
          onSearch={(value)=> this.handleFilter(value)}
          enterButton={<Button style={{ width: 30 }} icon="search" />} />

          {/* Table */}
          <Table style={{marginTop: 20}} columns={this.columns} dataSource={data} />
        </div>
        );
    }
}
