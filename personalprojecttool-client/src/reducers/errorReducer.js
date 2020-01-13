import { GET_ERRORS, DEFAULT_ERROR } from "../Actions/Types";

const initialState = {};

export default function(state = initialState, action) {
  switch (action.type) {
    case GET_ERRORS:
      return action.payload;

    case DEFAULT_ERROR:
      return {};

    default:
      return state;
  }
}
