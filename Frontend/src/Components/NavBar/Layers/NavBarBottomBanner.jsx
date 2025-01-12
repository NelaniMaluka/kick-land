import "./NavBarBottomBanner.css";
import { Link } from "react-router-dom";

export default function NavBarBottomBanner() {
  return (
    <div className="banner">
      <p>
        SHOP ALL SNEAKERS <Link to="/shop/Shop-All">Shop</Link>
      </p>
    </div>
  );
}
