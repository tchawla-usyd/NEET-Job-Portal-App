import { Layout, Menu, Dropdown, Link } from 'antd';
import React, {Component} from "react";

import Listing from '../components/Listing';

import logo from "../../NEET.png";

const { Header, Footer, Content } = Layout;

export default class BaseLayout extends Component {
    constructor(props) {
        super(props);
    }

   	render(){
   		const itemStyle = {float:'right', 
   				  display:'flex',
	              justifyContent:"center",
	              alignItems:'center'};
   		const MenuItem = Menu.Item;
   		const menu = (
		  <Menu>
		    <Menu.Item>
		      <a target="_blank" rel="noopener noreferrer" href="http://www.alipay.com/">
		        1st menu item
		      </a>
		    </Menu.Item>
		    <Menu.Item>
		      <a target="_blank" rel="noopener noreferrer" href="http://www.taobao.com/">
		        2nd menu item
		      </a>
		    </Menu.Item>
		    <Menu.Item>
		      <a target="_blank" rel="noopener noreferrer" href="http://www.tmall.com/">
		        3rd menu item
		      </a>
		    </Menu.Item>
		  </Menu>
		);
   		return (
   		<Layout>
	      <Header style={{ position: 'fixed', zIndex: 1, width: '100%' }}>
		      <span style={{float: 'left'}}><a herf='#'><img style={{ width: "64px", height: "64px"}} src={logo}/></a></span>
		      <Menu
		        theme="dark"
		        mode="horizontal"
		        defaultSelectedKeys={['0']}
		        style={{ lineHeight: '64px', margin: 0}}>
		        <MenuItem style={{display: 'none'}} key='0' />
		       	
		        <span style={itemStyle}>
				  <Dropdown placement="bottomCenter"
				  	overlay={menu} trigger={['click']}>
				      <a href="#">
					    Rex Shen
					  </a>
					  </Dropdown>
				</span>
				<MenuItem className="myitem" style={{...itemStyle, marginRight: 20}} key='3'>nav 3</MenuItem>
				<MenuItem className="myitem" style={itemStyle} key='2'>nav 2</MenuItem>
		        <MenuItem className="myitem" style={itemStyle} key='1'>nav 1</MenuItem>
		      </Menu>
		      
	      </Header>
	      <Content style={{ padding: '0 50px', marginTop: 64 }}>
	      	<div style={{ background: '#fff', padding: 24, minHeight: 500 }}>
	      		<Listing/>
	      	</div>
	      </Content>
	      <Footer style={{ textAlign: 'center' }}>NEET.inc ©2019</Footer>
    	</Layout>);
   	}
}