import BaseLayout from '../components/BaseLayout';
import Listing from '../components/Listing';

import React, {Component} from "react";

export default class Home extends Component {
    constructor(props) {
        super(props);
    }

   	render(){
   		return (
   		<BaseLayout >
   			<Listing />
    	</BaseLayout>);
   	}
}