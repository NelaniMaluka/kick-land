import "./HomePage.css";
import { Link } from "react-router-dom";
import ProductsView from "./ProductsView";
function HomePageView1({ item }) {
  return (
    <section>
      <div className="section">
        <Link to={`./shop/${item}`}>
          <div className="banner-section">
            <div>
              <img src={`/Images/HomeImages/back-Banner-${item}.jpg`}></img>
            </div>
            <button>Shop {item}</button>
          </div>
        </Link>
        <div className="items-section">
          <ProductsView item={item} />
        </div>
      </div>
    </section>
  );
}

export default HomePageView1;
