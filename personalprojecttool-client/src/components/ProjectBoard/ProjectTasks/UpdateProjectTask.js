import React, { Component } from "react";
import { Link } from "react-router-dom";
import classnames from "classnames";
import PropTypes from "prop-types";
import { connect } from "react-redux";
import {
  getProjectTask,
  updateProjectTask
} from "../../../Actions/BacklogActions";
import { setErrorDefaultValue } from "../../../Actions/GlobalActions";

class UpdateProjectTask extends Component {
  constructor(props) {
    super(props);
    const { projectIdentifier } = this.props.match.params;
    const { ptSequence } = this.props.match.params;

    this.state = {
      summary: "",
      status: "",
      priority: 0,
      dueDate: "",
      acceptanceCriteria: "",
      projectIdentifier: projectIdentifier,
      projectSequence: ptSequence,
      errors: {}
    };
    this.onChange = this.onChange.bind(this);
    this.onSubmit = this.onSubmit.bind(this);
  }

  onChange(e) {
    this.setState({ [e.target.name]: e.target.value });
  }

  onSubmit(e) {
    e.preventDefault();

    const updatedProjectTask = {
      summary: this.state.summary,
      status: this.state.status,
      priority: this.state.priority,
      dueDate: this.state.dueDate,
      acceptanceCriteria: this.state.acceptanceCriteria,
      projectIdentifier: this.state.projectIdentifier,
      projectSequence: this.state.projectSequence
    };

    this.props.updateProjectTask(
      this.state.projectIdentifier,
      this.state.projectSequence,
      updatedProjectTask,
      this.props.history
    );
  }

  async componentWillUnmount() {
    await this.props.setErrorDefaultValue();
  }
  componentWillReceiveProps(nextProps) {
    const {
      summary,
      status,
      priority,
      dueDate,
      acceptanceCriteria,
      projectIdentifier,
      projectSequence
    } = nextProps.projectTask;

    if (nextProps.errors) {
      this.setState({ errors: nextProps.errors });
    }

    this.setState({
      summary,
      projectIdentifier,
      status,
      priority,
      dueDate,
      acceptanceCriteria,
      projectSequence
    });
  }
  componentDidMount() {
    const { projectIdentifier, ptSequence } = this.props.match.params;
    this.props.getProjectTask(
      projectIdentifier,
      ptSequence,
      this.props.history
    );
  }
  render() {
    const { projectIdentifier } = this.props.match.params;
    const { errors } = this.state;
    return (
      <div className="add-PBI">
        <div className="container">
          <div className="row">
            <div className="col-md-8 m-auto">
              <Link
                to={`/projectBoard/${projectIdentifier}`}
                className="btn btn-light"
              >
                Back to Project Board
              </Link>
              <h4 className="display-4 text-center">Add Project Task</h4>
              <p className="lead text-center">Project Name + Project Code</p>
              <form onSubmit={this.onSubmit}>
                <div className="form-group">
                  <input
                    type="text"
                    className={classnames("form-control form-control-lg ", {
                      "is-invalid": errors.summary
                    })}
                    name="summary"
                    placeholder="Project Task summary"
                    value={this.state.summary}
                    onChange={this.onChange}
                  />
                  {this.state.errors.summary && (
                    <div className="invalid-feedback">
                      {this.state.errors.summary}
                    </div>
                  )}
                </div>

                <div className="form-group">
                  <textarea
                    className="form-control form-control-lg"
                    placeholder="Acceptance Criteria"
                    name="acceptanceCriteria"
                    value={this.state.acceptanceCriteria}
                    onChange={this.onChange}
                  ></textarea>
                </div>
                <h6>Due Date</h6>
                <div className="form-group">
                  <input
                    type="date"
                    className="form-control form-control-lg"
                    name="dueDate"
                    value={this.state.dueDate}
                    onChange={this.onChange}
                  />
                </div>
                <div className="form-group">
                  <select
                    className="form-control form-control-lg"
                    name="priority"
                    value={this.state.priority}
                    onChange={this.onChange}
                  >
                    <option value={0}>Select Priority</option>
                    <option value={1}>High</option>
                    <option value={2}>Medium</option>
                    <option value={3}>Low</option>
                  </select>
                </div>

                <div className="form-group">
                  <select
                    className="form-control form-control-lg"
                    name="status"
                    value={this.state.status}
                    onChange={this.onChange}
                  >
                    <option value="">Select Status</option>
                    <option value="TO_DO">TO DO</option>
                    <option value="IN_PROGRESS">IN PROGRESS</option>
                    <option value="DONE">DONE</option>
                  </select>
                </div>

                <input
                  type="submit"
                  className="btn btn-primary btn-block mt-4"
                />
              </form>
            </div>
          </div>
        </div>
      </div>
    );
  }
}

UpdateProjectTask.propTypes = {
  getProjectTask: PropTypes.func.isRequired,
  updateProjectTask: PropTypes.func.isRequired,
  errors: PropTypes.object.isRequired
};

const mapStateToProps = state => ({
  errors: state.errors,
  projectTask: state.backlog.project_task
});

export default connect(mapStateToProps, {
  getProjectTask,
  updateProjectTask,
  setErrorDefaultValue
})(UpdateProjectTask);
