import React from "react";
import { Route, Switch } from "react-router-dom";
import Login from "../containers/Login"
import Signup from "../containers/Signup"
import Home from "../containers/Home"
import Post from "../containers/Post"
import Profile from "../containers/Profile"
import Job from "../containers/Job"
import Slides from "../containers/Slides"

const Route_ = ({component: C, props: cProps, ...rest}) =>
    <Route {...rest} render={props =>  <C {...props} {...cProps} />}/>;

export default ({ childProps }) =>
<Switch>
  <Route_ path="/" exact component={Login} props={childProps} />
  <Route_ path="/login" exact component={Login} props={childProps} />
  <Route_ path="/signup" exact component={Signup} props={childProps} />
  <Route_ path="/home" exact component={Home} props={childProps} />
  <Route_ path="/post" exact component={Post} props={childProps} />
  <Route_ path="/profile" exact component={Profile} props={childProps} />
  <Route_ path="/job" exact component={Job} props={childProps} />
  <Route_ path="/slides" exact component={Slides} props={childProps} />


{ /* Finally, catch all unmatched routes */ }
  <Route {...childProps} render={props => { 
    props.history.push("/") 
    return ""
  }} />

</Switch>;