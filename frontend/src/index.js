import React from 'react';
import ReactDOM from 'react-dom';
import './index.css';
import App from "./js/components/App";

import store from "./js/store/index";
import { Provider } from "react-redux";
import { BrowserRouter as Router } from "react-router-dom";

ReactDOM.render(<Provider store={store}>
     <Router>
     	<App />
     </Router>
  </Provider>, 
  document.getElementById('root'));

