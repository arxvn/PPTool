import React, { Component } from "react";
import "./App.css";
import Dashboard from "../components/Dashboard";
import "bootstrap/dist/css/bootstrap.min.css";
import Header from "../components/Header/Header";
import { BrowserRouter as Router, Route } from "react-router-dom";
import AddProject from "../components/Project/AddProject";
import { Provider } from "react-redux";
import store from "./Store";
import UpdateProject from "../components/Project/UpdateProject";
import ProjectBoard from "../components/ProjectBoard/ProjectBoard";
import AddProjectTask from "../components/ProjectBoard/ProjectTasks/AddProjectTask";
import UpdateProjectTask from "../components/ProjectBoard/ProjectTasks/UpdateProjectTask";
class App extends Component {
  render() {
    return (
      <Provider store={store}>
        <Router>
          <div className="App">
            <Header />
            <Route exact path="/dashboard" component={Dashboard} />
            <Route exact path="/addProject" component={AddProject} />
            <Route exact path="/updateProject/:id" component={UpdateProject} />
            <Route exact path="/projectBoard/:id" component={ProjectBoard} />
            <Route
              exact
              path="/addprojecttask/:id"
              component={AddProjectTask}
            />
            <Route
              exact
              path="/updateProjectTask/:projectIdentifier/:ptSequence"
              component={UpdateProjectTask}
            />
          </div>
        </Router>
      </Provider>
    );
  }
}

export default App;
