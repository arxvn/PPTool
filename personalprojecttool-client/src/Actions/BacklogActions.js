import axios from "axios";
import {
  GET_ERRORS,
  GET_BACKLOG,
  GET_PROJECT_TASK,
  DELETE_PROJECT_TASK
} from "./Types";

export const addProjectTask = (
  backlog_id,
  project_task,
  history
) => async dispatch => {
  try {
    await axios.post(`/api/backlog/${backlog_id}`, project_task);
    history.push(`/projectBoard/${backlog_id}`);
    dispatch({
      type: GET_ERRORS,
      payload: {}
    });
  } catch (err) {
    dispatch({
      type: GET_ERRORS,
      payload: err.response.data
    });
  }
};

export const getBacklog = backlog_id => async dispatch => {
  try {
    const res = await axios.get(`/api/backlog/${backlog_id}`);
    dispatch({
      type: GET_BACKLOG,
      payload: res.data
    });
  } catch (err) {
    dispatch({
      type: GET_ERRORS,
      payload: err.response.data
    });
  }
};

export const getProjectTask = (
  projectIdentifier,
  projectSequence,
  history
) => async dispatch => {
  try {
    const res = await axios.get(
      `/api/backlog/${projectIdentifier}/${projectSequence}`
    );
    dispatch({
      type: GET_PROJECT_TASK,
      payload: res.data
    });
  } catch (err) {
    history.push(`/projectBoard/${projectIdentifier}`);
    dispatch({
      type: GET_ERRORS,
      payload: err.response.data
    });
  }
};

export const updateProjectTask = (
  projectIdentifier,
  projectSequence,
  projectTask,
  history
) => async dispatch => {
  try {
    await axios.patch(
      `/api/backlog/${projectIdentifier}/${projectSequence}`,
      projectTask
    );
    history.push(`/projectBoard/${projectIdentifier}`);
    dispatch({
      type: GET_ERRORS,
      payload: {}
    });
  } catch (err) {
    dispatch({
      type: GET_ERRORS,
      payload: err.response.data
    });
  }
};

export const deleteProjectTask = (
  projectIdentifier,
  projectSequence
) => async dispatch => {
  if (
    window.confirm(
      `Are you sure you wish to delete project task ${projectSequence}?`
    )
  ) {
    await axios.delete(`/api/backlog/${projectIdentifier}/${projectSequence}`);
    dispatch({
      type: DELETE_PROJECT_TASK,
      payload: projectSequence
    });
  }
};
