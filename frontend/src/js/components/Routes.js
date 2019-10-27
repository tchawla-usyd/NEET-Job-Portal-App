import React from "react";
import { Route, Switch } from "react-router-dom";
import Login from "../containers/Login"
import Signup from "../containers/Signup"
import Home from "../containers/Home"
import Post from "../containers/Post"
import Profile from "../containers/Profile"
import Job from "../containers/Job"
import Slides from "../containers/Slides"
import PrivateRoute from './PrivateRoute'


const Route_ = ({component: C, props: cProps, ...rest}) =>
    <Route {...rest} render={props =>  <C {...props} {...cProps} />} />;

export default ({ childProps }) =>
<Switch>
  <Route_ path="/" exact component={Login} props={childProps} />
  <Route_ path="/login" exact component={Login} props={childProps} />
  <Route_ path="/signup" exact component={Signup} props={childProps} />
  <PrivateRoute path="/home" exact component={Home} props={{...childProps, key: 'home'}} />
  <PrivateRoute path="/post" exact component={Post} props={childProps} />
  <PrivateRoute path="/profile" exact component={Profile} props={{...childProps, key: 'profile'}} />
  <PrivateRoute path="/company" exact component={Profile} props={{...childProps, key: 'company'}} />
  <PrivateRoute path="/job" exact component={Job} props={childProps} />
  <PrivateRoute path="/about" exact component={Slides} props={childProps} />
  <PrivateRoute path="/application" exact component={Home} props={{...childProps, key: 'app'}} />
  <PrivateRoute path="/favorite" exact component={Home} props={{...childProps, key: 'fav'}} />




{ /* Finally, catch all unmatched routes */ }
  <Route {...childProps} render={props => { 
    props.history.push("/") 
    return ""
  }} />

</Switch>;