import { Link } from "react-router-dom";
import "./HomePage.css";

function HomePageView2(item1, item2) {
  return (
    <div className="view2 container2">
      <div className="banner-img">
        <Link to={`./shop/${item1.item1}`}>
          <img src={`/Images/HomeImages/banner-${item1.item1}-1.jpg`}></img>
        </Link>
      </div>
      <div className="banner-img">
        <Link to={`./shop/${item1.item2}`}>
          <img src={`/Images/HomeImages/banner-${item1.item2}-2.jpg`}></img>
        </Link>
      </div>
    </div>
  );
}

export default HomePageView2;
