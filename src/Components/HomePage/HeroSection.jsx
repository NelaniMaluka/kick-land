import "./HeroSection.css";
import { Link } from "react-router-dom";

function HeroSection() {
  return (
    <section>
      <div className="section">
        <Link to={"/Shop/Shop-All"}>
          <div className="banner-section">
            <div>
              <img src={`/Images/HomeImages/HeroImage1.jpg`}></img>
            </div>
            <div className="button-div">
              <button>Shop All</button>
            </div>
          </div>
        </Link>
      </div>
    </section>
  );
}

export default HeroSection;
