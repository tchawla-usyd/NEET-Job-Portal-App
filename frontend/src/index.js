import React from 'react';
import ReactDOM from 'react-dom';
import './index.css';
import App from "./js/components/App.jsx";

import store from "./js/store/index";
import { Provider } from "react-redux";

ReactDOM.render(<Provider store={store}>
    <App />
  </Provider>, document.getElementById('root'));

