import React, {Component} from "react";
import { Table, Divider, Tag, Input, Button, Typography} from 'antd';
const { Search } = Input;
const { Text } = Typography;

const columns = [
  {
    title: 'Job Title',
    dataIndex: 'title',
    key: 'title',
    align: 'center',
    render: text => <a><Text strong>{text}</Text></a>,
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
        <a>Save</a>
        <Divider type="vertical" />
        <a>Apply</a>
      </span>
    ),
  },
];

const data = [
  {
    key: '1',
    title: 'Software Dev',
    location: 'Sydney',
    skills: ['nice', 'developer'],
  },
  {
    key: '2',
    title: 'Tester',
    location: 'Melbourne',
    skills: ['loser'],
  },
  {
    key: '3',
    title: 'Whooooohla',
    location: 'Brisbane',
    skills: ['cool', 'teacher'],
  },
];

export default class Home extends Component {
    constructor(props) {
        super(props);
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
          <Table style={{marginTop: 20}} columns={columns} dataSource={data} />
        </div>
        );
    }
}
