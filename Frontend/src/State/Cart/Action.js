import ErrorMessageAlert from "../../Components/Alerts/ErrorMessageAlert";
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
    const { data } = await apiClient.post(`/api/cart`, reqData, {
      headers: {
        Authorization: `Bearer ${jwt}`,
      },
    });
    dispatch({ type: ADD_CART_SUCCESS, payload: data });
    return true;
  } catch (e) {
    dispatch({ type: ADD_CART_FAILURE, payload: e });
    ErrorMessageAlert({ message: "Couldn't add item to cart", e });
    return false;
  }
};

export const removeCart = (productId, jwt) => async (dispatch) => {
  dispatch({ type: REMOVE_CART_REQUEST });
  const parsedProductId = parseInt(productId, 10);
  try {
    const { data } = await apiClient.delete(
      `/api/cart?productId=${parsedProductId}`,
      {
        headers: {
          Authorization: `Bearer ${jwt}`,
        },
      }
    );
    dispatch({ type: REMOVE_CART_SUCCESS, payload: data });
    return true;
  } catch (e) {
    dispatch({ type: REMOVE_CART_FAILURE, payload: e });
    return false;
  }
};

export const updateCart = (reqData, jwt) => async (dispatch) => {
  dispatch({ type: UPDATE_CART_REQUEST });
  try {
    const { data } = await apiClient.put(`/api/cart`, reqData, {
      headers: {
        Authorization: `Bearer ${jwt}`,
      },
    });
    dispatch({ type: UPDATE_CART_SUCCESS, payload: data });
    return true;
  } catch (e) {
    dispatch({ type: UPDATE_CART_FAILURE, payload: e });
    return false;
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
