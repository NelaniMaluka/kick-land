import {
  LOGIN_REQUEST,
  REGISTER_REQUEST,
  REGISTER_SUCCESS,
  LOGIN_SUCCESS,
  GET_USER_REQUEST,
  GET_USER_SUCCESS,
  LOGOUT_REQUEST,
  REGISTER_FAILURE,
  LOGIN_FAILURE,
  GET_USER_FAILURE,
  UPDATE_USER_SUCCESS,
  UPDATE_USER_FAILURE,
  UPDATE_USER_REQUEST,
} from "./ActionType";
import { apiClient, url } from "../../Context/Api";
import axios from "axios";

export const registerUser = (reqData) => async (dispatch) => {
  dispatch({ type: REGISTER_REQUEST });
  try {
    const { data } = await axios.post(`${url}/auth/create-account`, reqData);
    if (data.jwt) localStorage.setItem("jwt", data.jwt);
    dispatch({ type: REGISTER_SUCCESS, payload: data.jwt });
    return true;
  } catch (e) {
    dispatch({ type: REGISTER_FAILURE, payload: e });
    return false;
  }
};

export const loginUser = (reqData, navigate) => async (dispatch) => {
  dispatch({ type: LOGIN_REQUEST });
  try {
    const { data } = await axios.post(`${url}/auth/login`, reqData);
    if (data.jwt) localStorage.setItem("jwt", data.jwt);
    dispatch({ type: LOGIN_SUCCESS, payload: data.jwt });
    return true;
  } catch (e) {
    dispatch({ type: LOGIN_FAILURE, payload: e });
    return false;
  }
};

export const getUser = (jwt) => async (dispatch) => {
  dispatch({ type: GET_USER_REQUEST });
  try {
    const { data } = await apiClient.get(`/api/user-details`, {
      headers: {
        Authorization: `Bearer ${jwt}`,
      },
    });
    dispatch({ type: GET_USER_SUCCESS, payload: data });
  } catch (e) {
    dispatch({ type: GET_USER_FAILURE, payload: e });
  }
};

export const updateUserData = (reqData, jwt) => async (dispatch) => {
  dispatch({ type: UPDATE_USER_REQUEST });
  try {
    const { data } = await apiClient.post("/api/user-details", reqData, {
      headers: {
        Authorization: `Bearer ${jwt}`,
      },
    });

    dispatch({ type: UPDATE_USER_SUCCESS, payload: data });
    return true;
  } catch (e) {
    dispatch({ type: UPDATE_USER_FAILURE, payload: e });
    return false;
  }
};

export const logoutUser = () => async (dispatch) => {
  dispatch({ type: LOGOUT_REQUEST });
  localStorage.clear();
  dispatch({ type: LOGOUT_REQUEST });
};
