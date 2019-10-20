import React, {Component} from "react";
import {Icon, Spin } from 'antd';

const Spinner = (props) => <Spin style={{position: 'absolute', left: '50%', top: '50%',}} indicator={<Icon type="loading" style={{ fontSize: 50 }} spin />}/>;
export default Spinner;