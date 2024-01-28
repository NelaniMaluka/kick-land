import "./HomePage.css";
import { Link } from "react-router-dom";

function HomePageView1({ item }) {
  return (
    <section>
      <Link to={`./shop/${item}`}>
        <div className="section">
          <div className="banner-section">
            <div>
              <img src={`/Images/HomeImages/back-Banner-${item}.jpg`}></img>
            </div>

            <button>Shop {item}</button>
          </div>
          <div className="items-section">item</div>
        </div>
      </Link>
    </section>
  );
}

export default HomePageView1;
