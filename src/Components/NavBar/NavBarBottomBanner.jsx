import "./NavBarBottomBanner.css";
import { Link } from "react-router-dom";

function NavBarBottomBanner() {
  return (
    <div className="banner">
      <p>
        SHOP ALL SNEAKERS <Link to="/shop/Shop-All">Shop</Link>
      </p>
    </div>
  );
}

export default NavBarBottomBanner;
