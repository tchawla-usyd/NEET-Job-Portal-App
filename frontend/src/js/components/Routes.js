import React from "react";
import { Route, Switch } from "react-router-dom";
import Login from "../containers/Login"
import Signup from "../containers/Signup"
import Home from "../containers/Home"
import Post from "../containers/Post"
import Profile from "../containers/Profile"
import Job from "../containers/Job"


export default ({ childProps }) =>
<Switch>
  <Route path="/" exact component={Login} props={childProps} />
  <Route path="/login" exact component={Login} props={childProps} />
  <Route path="/signup" exact component={Signup} props={childProps} />
  <Route path="/home" exact component={Home} props={childProps} />
  <Route path="/post" exact component={Post} props={childProps} />
  <Route path="/profile" exact component={Profile} props={childProps} />
  <Route path="/job" exact component={Job} props={childProps} />


{ /* Finally, catch all unmatched routes */ }
  <Route {...childProps} render={props => { 
    props.history.push("/") 
    return ""
  }} />

</Switch>;