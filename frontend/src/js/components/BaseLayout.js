import { Layout, Menu, Dropdown, Avatar, Icon, Button } from 'antd';
import { Link } from 'react-router-dom'
import React, {Component} from "react";

import logo from "../../img/NEET.png";

const { Header, Footer, Content } = Layout;

export default class BaseLayout extends Component {
    constructor(props) {
        super(props);

        //user info
        const userInfo = this.props.parentProps.userInfo;
		this.isEmployer = userInfo.isEmployer;
		this.name = userInfo.firstName + " " + userInfo.lastName;
		this.userId = userInfo.id;

        if(!this.props.parentProps.isAuthenticated){
        	this.props.parentProps.history.push('/');
        }
    }

    getInit = (name) =>{
		var initials = name.match(/\b\w/g) || [];
		initials = ((initials.shift() || '') + (initials.pop() || '')).toUpperCase();
		return initials;
	};

	goBack = (e) =>{
		this.props.parentProps.history.goBack();
	}

   	render(){
   		const itemStyle = {float:'right', 
   				  display:'flex',
	              justifyContent:"center",
	              alignItems:'center'};
   		const MenuItem = Menu.Item;
   		const menu = this.isEmployer ? 
		<Menu style={{textAlign: 'middle'}} >
		    <Menu.Item>
		      <Link to= {'/profile?id=' + this.userId}>
		        <Icon style={{marginRight: 10, color:'#1E90FF'}} type="user" /> Profile
		      </Link>
		    </Menu.Item>
		    <Menu.Item>
		      <Link onClick={this.props.parentProps.handleLogout} to='/login'>
		        <Icon style={{marginRight: 10, color:'red'}} type="logout" /> Logout
		      </Link>
		    </Menu.Item>
		 </Menu>

		: <Menu style={{textAlign: 'middle'}} >
		    <Menu.Item>
		      <Link to= {'/profile?id=' + this.userId}>
		        <Icon style={{marginRight: 10, color:'#1E90FF'}} type="user" /> Profile
		      </Link>
		    </Menu.Item>

			<Menu.Item >
		      <Link to='/application'>
		      	<Icon style={{marginRight: 10, color:'#52c41a'}} type="profile" /> Application
		      </Link>
		    </Menu.Item>

		    <Menu.Item>
		      <Link to='/favorite' >
		      	<Icon style={{marginRight: 10, color:'#eb2f96'}} type="heart" /> Favorite
		      </Link>
		    </Menu.Item>

		    <Menu.Item>
		      <Link onClick={this.props.parentProps.handleLogout} to='/login'>
		        <Icon style={{marginRight: 10, color:'red'}} type="logout" /> Logout
		      </Link>
		    </Menu.Item>
		  </Menu>;

		console.log(this.props);
   		return (
   		<Layout>
	      <Header style={{ position: 'fixed', zIndex: 1, width: '100%' }}>
		      <span style={{float: 'left'}}><Link to='/home'><img style={{ width: "64px", height: "64px"}} src={logo}/></Link></span>
		      <Menu
		        theme="dark"
		        mode="horizontal"
		        selectedKeys={[this.props.parentProps.location.pathname]}
		        style={{ lineHeight: '64px', margin: 0}}>
		        <MenuItem style={{display: 'none'}} key='0' />
		       	
		        <span style={itemStyle}>
				  <Dropdown placement="bottomCenter"
				  	overlay={menu} trigger={['click']}>
					    <a href="#"><Avatar size="large" shape="square" style={{margin: 10, color: '#666666', backgroundColor: '#ffffff', fontSize: 15}}>{this.getInit(this.name)}</Avatar></a>
					  </Dropdown>
				</span>
				<MenuItem className="myitem" style={{...itemStyle, marginRight: 20}} key='/about'><Link to='/about'>About</Link></MenuItem>
		        <MenuItem className="myitem" style={itemStyle} key='/home'><Link to='/home'>Dashboard</Link></MenuItem>
		      </Menu>
		      
	      </Header>
	      <Content style={{ padding: '0 50px', marginTop: 64 }}>
	      	<div style={{ background: '#fff', padding: 24, minHeight: 600 }}>
	      		{this.props.children}
	      		{this.props.parentProps.location.pathname == '/about' ? '' : <Button style={{margin: 20}} onClick={this.goBack}><Icon type="left" /> Back</Button>}
	      	</div>
	      </Content>
	      <Footer style={{ textAlign: 'center' }}>NEET.inc ©2019</Footer>
    	</Layout>);
   	}
}