import {
  ADD_ORDER_FAILURE,
  ADD_ORDER_REQUEST,
  ADD_ORDER_SUCCESS,
  GET_ORDER_FAILURE,
  GET_ORDER_REQUEST,
  GET_ORDER_SUCCESS,
} from "./ActionType";

const initialState = {
  order: null,
  isLoading: false,
  error: null,
  success: false,
};

export const orderReducer = (state = initialState, action) => {
  switch (action.type) {
    case GET_ORDER_REQUEST:
    case ADD_ORDER_REQUEST:
      return {
        ...state,
        order: null,
        error: null,
        success: false,
        isLoading: true,
      };

    case GET_ORDER_FAILURE:
    case ADD_ORDER_FAILURE:
      return {
        ...state,
        error: action.payload,
        success: false,
        isLoading: false,
      };

    case GET_ORDER_SUCCESS:
    case ADD_ORDER_SUCCESS:
      return {
        ...state,
        order: action.payload,
        error: null,
        success: true,
        isLoading: false,
      };

    default:
      return state;
  }
};
