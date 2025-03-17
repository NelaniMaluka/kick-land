import { Link } from "react-router-dom";

export default function PageNotFound() {
  return (
    <div style={{ height: "540px", paddingTop: "180px" }}>
      <h1 style={{ fontSize: "50px", fontFamily: "serif" }}>
        404 Page Not Found
      </h1>
      <span>
        Click{" "}
        <Link to={"/"} style={{ color: "blue" }}>
          here
        </Link>{" "}
        to return home
      </span>
    </div>
  );
}
