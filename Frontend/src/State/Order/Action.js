import { apiClient } from "../../Context/Api";
import { getCart } from "../Cart/Action";
import {
  ADD_ORDER_FAILURE,
  ADD_ORDER_REQUEST,
  ADD_ORDER_SUCCESS,
  GET_ORDER_FAILURE,
  GET_ORDER_REQUEST,
  GET_ORDER_SUCCESS,
} from "./ActionType";

export const generatePaymentLink = (reqData, jwt) => async (dispatch) => {
  try {
    const { data, status } = await apiClient.post(`/api/order`, reqData, {
      headers: {
        Authorization: `Bearer ${jwt}`,
      },
    });
    console.log(data, status);

    // If request is successful (status code 2xx)
    if (status >= 200 && status < 300) {
      return {
        status: status,
        data: data,
      };
    }

    // Handle case when the response status isn't successful
    throw new Error(data.data || "Something went wrong");
  } catch (error) {
    return {
      status: error.response?.status || 500, // Use status from error if it exists
      message: error.message || "An unexpected error occurred while signing up",
    };
  }
};

export const addOrder = (paymentId, reqData, jwt) => async (dispatch) => {
  dispatch({ type: ADD_ORDER_REQUEST });
  try {
    const { data, status } = await apiClient.post(
      `/api/order/confirmation`,
      paymentId,
      reqData,
      {
        headers: {
          Authorization: `Bearer ${jwt}`,
        },
      }
    );
    dispatch({ type: ADD_ORDER_SUCCESS, payload: data });
    await dispatch(getCart(jwt));

    // If request is successful (status code 2xx)
    if (status >= 201 && status < 300) {
      return {
        status: status,
        data: data,
      };
    }

    // Handle case when the response status isn't successful
    throw new Error(data.data || "Something went wrong");
  } catch (error) {
    dispatch({ type: ADD_ORDER_FAILURE, payload: error });
    return {
      status: error.response?.status || 500, // Use status from error if it exists
      message: error.message || "An unexpected error occurred while signing up",
    };
  }
};

export const getOrder = (jwt) => async (dispatch) => {
  dispatch({ type: GET_ORDER_REQUEST });
  try {
    const { data } = await apiClient.get(`/api/order`, {
      headers: {
        Authorization: `Bearer ${jwt}`,
      },
    });

    dispatch({ type: GET_ORDER_SUCCESS, payload: data });
  } catch (e) {
    dispatch({ type: GET_ORDER_FAILURE, payload: e });
  }
};
