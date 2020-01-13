import { DEFAULT_ERROR } from "./Types";

export const setErrorDefaultValue = () => async dispatch => {
  dispatch({
    type: DEFAULT_ERROR,
    payload: {}
  });
};
