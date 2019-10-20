import { Layout, Menu, Dropdown, Avatar, Icon } from 'antd';
import { Link } from 'react-router-dom'
import React, {Component} from "react";

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
		  <Menu style={{textAlign: 'left'}}>
		    <Menu.Item>
		      <Link to= '/profile'>
		        <Icon style={{marginRight: 10, color:'#1E90FF'}} type="user" /> Profile
		      </Link>
		    </Menu.Item>
		    <Menu.Item>
		      <Link to='/home'>
		      	<Icon style={{marginRight: 10, color:'#eb2f96'}} type="heart" /> Favorite
		      </Link>
		    </Menu.Item>
		    <Menu.Item>
		      <Link to='/login' >
		        <Icon style={{marginRight: 10, color:'red'}} type="logout" /> Logout
		      </Link>
		    </Menu.Item>
		  </Menu>
		);
   		return (
   		<Layout>
	      <Header style={{ position: 'fixed', zIndex: 1, width: '100%' }}>
		      <span style={{float: 'left'}}><Link to='/home'><img style={{ width: "64px", height: "64px"}} src={logo}/></Link></span>
		      <Menu
		        theme="dark"
		        mode="horizontal"
		        defaultSelectedKeys={['0']}
		        style={{ lineHeight: '64px', margin: 0}}>
		        <MenuItem style={{display: 'none'}} key='0' />
		       	
		        <span style={itemStyle}>
				  <Dropdown placement="bottomCenter"
				  	overlay={menu} trigger={['click']}>
					    <a href="#"><Avatar size="large" shape="square" style={{margin: 10, color: '#666666', backgroundColor: '#ffffff', fontSize: 15}}>RS</Avatar></a>
					  </Dropdown>
				</span>
				<MenuItem className="myitem" style={{...itemStyle, marginRight: 20}} key='3'>nav 3</MenuItem>
				<MenuItem className="myitem" style={itemStyle} key='2'>nav 2</MenuItem>
		        <MenuItem className="myitem" style={itemStyle} key='1'><Link to='/home'>Dashboard</Link></MenuItem>
		      </Menu>
		      
	      </Header>
	      <Content style={{ padding: '0 50px', marginTop: 64 }}>
	      	<div style={{ background: '#fff', padding: 24, minHeight: 500 }}>
	      		{this.props.children}
	      	</div>
	      </Content>
	      <Footer style={{ textAlign: 'center' }}>NEET.inc ©2019</Footer>
    	</Layout>);
   	}
}