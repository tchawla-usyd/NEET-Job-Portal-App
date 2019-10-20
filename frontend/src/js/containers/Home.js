import BaseLayout from '../components/BaseLayout';
import JobListing from '../components/JobListing';

import React, {Component} from "react";

export default class Home extends Component {
    constructor(props) {
        super(props);
    }
    
    componentDidMount(prevProps) {
      window.scrollTo(0, 0);
	}

   	render(){
   		return (
   		<BaseLayout >
   			<JobListing />
    	</BaseLayout>);
   	}
}