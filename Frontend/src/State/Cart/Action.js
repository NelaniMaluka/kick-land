import { apiClient } from "../../Context/Api";
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

export const addCart = (reqData, jwt) => async (dispatch) => {
  dispatch({ type: ADD_CART_REQUEST });
  try {
    const { data, status } = await apiClient.post(`/api/cart`, reqData, {
      headers: {
        Authorization: `Bearer ${jwt}`,
      },
    });
    dispatch({ type: ADD_CART_SUCCESS, payload: data });

    // If request is successful (status code 2xx)
    if (status >= 200 && status < 300) {
      return {
        status: status,
        data: data.data,
      };
    }

    // Handle case when the response status isn't successful
    throw new Error(data.data || "Something went wrong");
  } catch (error) {
    dispatch({ type: ADD_CART_FAILURE, payload: error });
    return {
      status: error.response?.status || 500, // Use status from error if it exists
      message: error.message || "An unexpected error occurred while signing up",
    };
  }
};

export const removeCart = (productId, jwt) => async (dispatch) => {
  dispatch({ type: REMOVE_CART_REQUEST });
  const parsedProductId = parseInt(productId, 10);
  try {
    const { data, status} = await apiClient.delete(
      `/api/cart?productId=${parsedProductId}`,
      {
        headers: {
          Authorization: `Bearer ${jwt}`,
        },
      }
    );
    dispatch({ type: REMOVE_CART_SUCCESS, payload: data });

    // If request is successful (status code 2xx)
    if (status >= 200 && status < 300) {
      return {
        status: status,
        data: data.data,
      };
    }

    // Handle case when the response status isn't successful
    throw new Error(data.data || "Something went wrong");
  } catch (error) {
    dispatch({ type: REMOVE_CART_FAILURE, payload: error });
    return {
      status: error.response?.status || 500, // Use status from error if it exists
      message: error.message || "An unexpected error occurred while signing up",
    };
  }
};

export const updateCart = (reqData, jwt) => async (dispatch) => {
  dispatch({ type: UPDATE_CART_REQUEST });
  try {
    const {data, status } = await apiClient.put(`/api/cart`, reqData, {
      headers: {
        Authorization: `Bearer ${jwt}`,
      },
    });
    dispatch({ type: UPDATE_CART_SUCCESS, payload: data });

    // If request is successful (status code 2xx)
    if (status >= 200 && status < 300) {
      return {
        status: status,
        data: data.data,
      };
    }

    // Handle case when the response status isn't successful
    throw new Error(data.data || "Something went wrong");
  } catch (error) {
    dispatch({ type: UPDATE_CART_FAILURE, payload: error });
    return {
      status: error.response?.status || 500, // Use status from error if it exists
      message: error.message || "An unexpected error occurred while signing up",
    };
  }
};

export const getCart = (jwt) => async (dispatch) => {
  dispatch({ type: GET_CART_REQUEST });
  try {
    const { data } = await apiClient.get(`/api/cart`, {
      headers: {
        Authorization: `Bearer ${jwt}`,
      },
    });
    dispatch({ type: GET_CART_SUCCESS, payload: data });
  } catch (e) {
    dispatch({ type: GET_CART_FAILURE, payload: e });
  }
};
