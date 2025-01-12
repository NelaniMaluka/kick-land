import React, { useEffect } from "react";
import Snackbar from "@mui/material/Snackbar";
import Fade from "@mui/material/Fade";
import Slide from "@mui/material/Slide";

const SlideTransition = (props) => {
  return <Slide {...props} direction="up" />;
};

const TransitionsSnackbar = () => {
  const [state, setState] = React.useState({
    open: false,
    Transition: Fade,
  });

  const handleClick = (Transition) => () => {
    setState({
      open: true,
      Transition,
    });
  };

  const handleClose = () => {
    setState({
      ...state,
      open: false,
    });
  };

  useEffect(() => {
    // Trigger the Snackbar when the component mounts
    handleClick(SlideTransition)();
  }, []); // Empty dependency array ensures this effect runs only once on mount

  return (
    <Snackbar
      open={state.open}
      onClose={handleClose}
      TransitionComponent={state.Transition}
      message="Successfully added to Cart."
      key={state.Transition.name}
      autoHideDuration={1200}
    />
  );
};

export default TransitionsSnackbar;
