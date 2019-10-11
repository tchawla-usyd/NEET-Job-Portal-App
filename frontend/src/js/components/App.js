import React from "react";
import List from "./List.jsx";
import Form from "./Form.jsx";
import Routes from "./Routes"

// const App = () => (
//   <div className="row mt-5">
//     <div className="col-md-4 offset-md-1">
//       <h2>Articles</h2>
//       <List />
//     </div>
//     <div className="col-md-4 offset-md-1">
//       <h2>Add a new article</h2>
//       <Form />
//     </div>
//   </div>
// );

const childProps = {
  isAuthenticated: false,
};
const App = () => (
  <div className="App">
      <Routes childProps={childProps}/>
  </div>
);

export default App;