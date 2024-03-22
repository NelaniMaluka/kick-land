import React, { useState } from "react";
import Tabs from "@mui/material/Tabs";
import Tab from "@mui/material/Tab";
import Typography from "@mui/material/Typography";
import Box from "@mui/material/Box";
import AddIcon from "@mui/icons-material/Add";
import RemoveIcon from "@mui/icons-material/Remove";
import UserProfile from "./Profile/UserProfile";
import CartView from "./Cart/CartView";

function TabPanel(props) {
  const { children, value, index, tabsVisible, ...other } = props;

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
          <Typography>{children}</Typography>
        </Box>
      )}
    </div>
  );
}

function Dashboard() {
  const [value, setValue] = useState(0);
  const [tabsVisible, setTabsVisible] = useState(true);

  const toggleTabsVisibility = () => {
    setTabsVisible(!tabsVisible);
  };

  const handleChange = (event, newValue) => {
    setValue(newValue);
  };

  return (
    <div>
      <div style={{ display: "flex", alignItems: "center" }}>
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
        <Typography>{tabsVisible ? "Hide Tabs" : "Show Tabs"}</Typography>
      </div>

      <Box
        sx={{
          flexGrow: 1,
          bgcolor: "background.paper",
          display: "flex",
          height: "maxContent",
          minHeight: 629,
          width: "100%",
        }}
      >
        {tabsVisible && (
          <Tabs
            orientation="vertical"
            variant="scrollable"
            value={value}
            onChange={handleChange}
            aria-label="Vertical tabs example"
            sx={{ borderRight: 1, borderColor: "divider", width: "150px" }}
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
        )}

        <TabPanel value={value} index={0} tabsVisible={tabsVisible}>
          <UserProfile />
        </TabPanel>
        <TabPanel value={value} index={1} tabsVisible={tabsVisible}>
          <CartView />
        </TabPanel>
        <TabPanel value={value} index={2} tabsVisible={tabsVisible}>
          Item Three
        </TabPanel>
      </Box>
    </div>
  );
}

export default Dashboard;
