import { apiClient } from "../../Context/Api";
import {
  ADD_ORDER_FAILURE,
  ADD_ORDER_REQUEST,
  ADD_ORDER_SUCCESS,
  GET_ORDER_FAILURE,
  GET_ORDER_REQUEST,
  GET_ORDER_SUCCESS,
} from "./ActionType";

export const addOrder = (reqData, jwt) => async (dispatch) => {
  dispatch({ type: ADD_ORDER_REQUEST });
  try {
    const { data } = await apiClient.post(`/api/order`, reqData, {
      headers: {
        Authorization: `Bearer ${jwt}`,
      },
    });
    dispatch({ type: ADD_ORDER_SUCCESS, payload: data });
    console.log(data);
  } catch (e) {
    dispatch({ type: ADD_ORDER_FAILURE, payload: e });
    console.log(e);
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
    console.log(data);
  } catch (e) {
    dispatch({ type: GET_ORDER_FAILURE, payload: e });
  }
};
