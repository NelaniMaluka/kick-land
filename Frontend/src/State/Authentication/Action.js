import {
  LOGIN_REQUEST,
  REGISTER_REQUEST,
  REGISTER_SUCCESS,
  LOGIN_SUCCESS,
  GET_USER_REQUEST,
  GET_USER_SUCCESS,
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
    const { data, status } = await axios.post(
      `${url}/auth/create-account`,
      reqData
    );
    if (data.jwt) localStorage.setItem("jwt", data.jwt);
    dispatch({ type: REGISTER_SUCCESS, payload: data.jwt });

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
    dispatch({ type: REGISTER_FAILURE, payload: error });
    return {
      status: error.response?.status || 500, // Use status from error if it exists
      message: error.message || "An unexpected error occurred while signing up",
    };
  }
};

export const loginUser = (reqData) => async (dispatch) => {
  dispatch({ type: LOGIN_REQUEST });
  try {
    const { data, status } = await axios.post(`${url}/auth/login`, reqData);
    if (data.jwt) localStorage.setItem("jwt", data.jwt);
    dispatch({ type: LOGIN_SUCCESS, payload: data.jwt });

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
    dispatch({ type: LOGIN_FAILURE, payload: error });
    return {
      status: error.response?.status || 500, // Use status from error if it exists
      message: error.message || "An unexpected error occurred while signing up",
    };
  }
};

export const getUser = (jwt) => async (dispatch) => {
  dispatch({ type: GET_USER_REQUEST });
  try {
    const { data } = await apiClient.get(`/api/user`, {
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
    const { data, status } = await apiClient.put("/api/user", reqData, {
      headers: {
        Authorization: `Bearer ${jwt}`,
      },
    });

    dispatch({ type: UPDATE_USER_SUCCESS, payload: data });

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
    dispatch({ type: UPDATE_USER_FAILURE, payload: error });
    return {
      status: error.response?.status || 500, // Use status from error if it exists
      message: error.message || "An unexpected error occurred while signing up",
    };
  }
};

export const logoutUser = () => async (dispatch) => {
  dispatch({ type: "RESET_APP_STATE" });
  localStorage.clear();
};
