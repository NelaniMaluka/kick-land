import { createUserWithEmailAndPassword } from "firebase/auth";
import { auth } from "./firebaseConfig";
import { signInWithEmailAndPassword } from "firebase/auth";
import { signOut } from "firebase/auth";
import { onAuthStateChanged } from "firebase/auth";

export const registerUser = async (username, surname, email, password) => {
  try {
    const userCredential = await createUserWithEmailAndPassword(
      auth,
      username,
      surname,
      email,
      password
    );
    console.log(userCredential.user);
    return userCredential.user; // User information
  } catch (error) {
    throw error.message; // Handle error
    console.log(error.message);
  }
};

export const loginUser = async (email, password) => {
  try {
    const userCredential = await signInWithEmailAndPassword(
      auth,
      email,
      password
    );
    return userCredential.user;
  } catch (error) {
    throw error.message;
  }
};

export const logoutUser = async () => {
  try {
    await signOut(auth);
  } catch (error) {
    throw error.message;
  }
};

export const getCurrentUser = (callback) => {
  onAuthStateChanged(auth, (user) => {
    callback(user); // Trigger callback with user info
  });
};
