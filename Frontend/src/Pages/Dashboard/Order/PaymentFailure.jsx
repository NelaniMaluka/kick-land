import { Link } from "react-router-dom";

export default function PaymentFalure() {
  return (
    <div style={{ height: "540px", paddingTop: "180px" }}>
      <h1 style={{ fontSize: "50px", fontFamily: "serif" }}>Payment Failed</h1>
      <span>
        Click{" "}
        <Link to={"/Dashboard"} style={{ color: "blue" }}>
          here
        </Link>{" "}
        to return to the Dashboard
      </span>
    </div>
  );
}
