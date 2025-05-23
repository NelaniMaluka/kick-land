import React, { useState } from "react";
import Tabs from "@mui/material/Tabs";
import Tab from "@mui/material/Tab";
import Typography from "@mui/material/Typography";
import Box from "@mui/material/Box";
import AddIcon from "@mui/icons-material/Add";
import RemoveIcon from "@mui/icons-material/Remove";
import UserProfile from "./UserProfile/UserProfile";
import CartView from "./Cart/CartView";
import OrdersView from "./Order/OrdersView";

function TabPanel(props) {
  const { children, value, index, ...other } = props;

  return (
    <div
      role="tabpanel"
      hidden={value !== index}
      id={`vertical-tabpanel-${index}`}
      aria-labelledby={`vertical-tab-${index}`}
      {...other}
      style={{
        width: "100%",
        display: "flex",
        flexDirection: "column",
      }}
    >
      {value === index && (
        <Box
          sx={{
            p: 3,
            flexGrow: 1,
          }}
        >
          {children}
        </Box>
      )}
    </div>
  );
}

export default function Dashboard() {
  const [value, setValue] = useState(0);
  const [tabsVisible, setTabsVisible] = useState(false);

  const handleChange = (event, newValue) => {
    setValue(newValue);
    if (window.innerWidth <= 881) {
      setTabsVisible(false); // Optional: auto-hide on mobile
    }
  };

  const toggleTabsVisibility = () => {
    setTabsVisible(!tabsVisible);
  };

  return (
    <div>
      {/* Toggle Button */}
      <div style={{ display: "flex", alignItems: "center", padding: "10px" }}>
        {tabsVisible ? (
          <RemoveIcon
            onClick={toggleTabsVisibility}
            style={{ cursor: "pointer" }}
          />
        ) : (
          <AddIcon
            onClick={toggleTabsVisibility}
            style={{ cursor: "pointer" }}
          />
        )}
        <Typography sx={{ ml: 1 }}>
          {tabsVisible ? "Hide Tabs" : "Show Tabs"}
        </Typography>
      </div>

      {/* Content and Sidebar */}
      <Box
        sx={{
          flexGrow: 1,
          bgcolor: "background.paper",
          display: "flex",
          position: "relative",
          width: "100%",
        }}
      >
        {/* Sidebar Tabs */}
        <Box
          sx={{
            display: {
              xs: tabsVisible ? "flex" : "none",
              sm: tabsVisible ? "flex" : "none",
              md: "flex",
            },
            "@media (max-width:881px)": {
              position: "absolute",
              top: 0,
              left: 0,
              height: "100vh",
              zIndex: 10,
              backgroundColor: "white",
              boxShadow: 3,
              width: "200px",
            },
            "@media (min-width:882px)": {
              position: "relative",
              width: "150px",
              boxShadow: "none",
            },
            borderRight: { md: 1 },
            borderColor: "divider",
            flexDirection: "column",
          }}
        >
          <Tabs
            orientation="vertical"
            variant="scrollable"
            value={value}
            onChange={handleChange}
            aria-label="Vertical tabs"
            sx={{
              width: "100%",
            }}
          >
            <Tab
              label="Profile"
              icon={<span className="material-symbols-outlined">person</span>}
            />
            <Tab
              label="Cart"
              icon={
                <span className="material-symbols-outlined">shopping_cart</span>
              }
            />
            <Tab
              label="Orders"
              icon={
                <span className="material-symbols-outlined">shopping_bag</span>
              }
            />
          </Tabs>
        </Box>

        {/* Main Content */}
        <Box sx={{ flexGrow: 1 }}>
          <TabPanel value={value} index={0}>
            <UserProfile />
          </TabPanel>
          <TabPanel value={value} index={1}>
            <CartView />
          </TabPanel>
          <TabPanel value={value} index={2}>
            <OrdersView />
          </TabPanel>
        </Box>
      </Box>
    </div>
  );
}
