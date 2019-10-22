import React, {Component} from "react";
import { Carousel } from 'antd';

import BaseLayout from '../components/BaseLayout';

export default class Slides extends Component {
	render(){
		return(
		<BaseLayout>
			<Carousel autoplay>
		    <div>
		      <h3>1</h3>
		    </div>
		    <div>
		      <h3>2</h3>
		    </div>
		    <div>
		      <h3>3</h3>
		    </div>
		    <div>
		      <h3>4</h3>
		    </div>
		  </Carousel>
		  </BaseLayout>);
	}
}
