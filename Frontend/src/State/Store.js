import { applyMiddleware, combineReducers, legacy_createStore } from "redux";
import { authReducer } from "./Authentication/Reducer";
import { orderReducer } from "./Order/Reducer";
import { cartReducer } from "./Cart/Reducer";
import { thunk } from "redux-thunk"; // Import as default

const rootReducer = combineReducers({
  auth: authReducer,
  order: orderReducer,
  cart: cartReducer,
});

// Create the store
export const store = legacy_createStore(
  rootReducer,
  applyMiddleware(thunk) // Add redux-thunk middleware
);

export default store;
