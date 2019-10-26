import React, {Component} from "react";
import { Carousel, Button, Icon } from 'antd';
import { Link } from 'react-router-dom'

import BaseLayout from '../components/BaseLayout';
import slide_1 from "../../img/Slide_1.png";
import slide_2 from "../../img/Slide_2.png";
import slide_3 from "../../img/Slide_3.png";

export default class Slides extends Component {
	componentDidMount(prevProps) {
      window.scrollTo(0, 0);
	}
	render(){
		const style={maxHeight: '100%', maxWidth: '100%', objectFit: 'contain'};
		return(
		<BaseLayout parentProps={this.props}>
			<Carousel autoplay>
		    <div>
		      <img style={style} src={slide_1}/>
		    </div>
		    <div>
		      <img style={style} src={slide_2}/>
		    </div>
		    <div>
		      <img style={style} src={slide_3}/>
		    </div>
		  </Carousel>
		  <Button size='large' style={{float:'right', marginTop: 20, marginBottom: 10}}><Link to='/home'>Get Started <Icon type="right" /></Link></Button>
		  <div style={{clear: 'both'}}></div>
		  </BaseLayout>);
	}
}
