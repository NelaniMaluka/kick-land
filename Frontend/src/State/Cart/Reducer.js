import {
  ADD_CART_FAILURE,
  ADD_CART_REQUEST,
  ADD_CART_SUCCESS,
  GET_CART_FAILURE,
  GET_CART_REQUEST,
  GET_CART_SUCCESS,
  REMOVE_CART_FAILURE,
  REMOVE_CART_REQUEST,
  REMOVE_CART_SUCCESS,
  UPDATE_CART_FAILURE,
  UPDATE_CART_REQUEST,
  UPDATE_CART_SUCCESS,
} from "./ActionType";

const initialState = {
  cart: null,
  isLoading: false,
  error: null,
  success: false,
};

export const cartReducer = (state = initialState, action) => {
  switch (action.type) {
    case GET_CART_REQUEST:
    case ADD_CART_REQUEST:
    case REMOVE_CART_REQUEST:
    case UPDATE_CART_REQUEST:
      return {
        ...state,
        error: null,
        success: false,
        isLoading: true,
      };

    case GET_CART_FAILURE:
    case ADD_CART_FAILURE:
    case REMOVE_CART_FAILURE:
    case UPDATE_CART_FAILURE:
      return {
        ...state,
        error: action.payload,
        success: false,
        isLoading: false,
      };

    case GET_CART_SUCCESS:
    case ADD_CART_SUCCESS:
    case REMOVE_CART_SUCCESS:
    case UPDATE_CART_SUCCESS:
      return {
        ...state,
        cart: action.payload,
        error: null,
        success: true,
        isLoading: false,
      };

    default:
      return state;
  }
};
