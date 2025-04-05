import "./HeroSection.css";
import { Link } from "react-router-dom";

export default function HeroSection() {
  return (
    <section>
      <div className="section">
        <Link to={"/Shop/Shop-All"}>
          <div className="banner-section">
            {/* Background Image Section */}
            <div>
              <img
                src={`/Images/HomeImages/HeroImage1.jpg`}
                alt="Hero Section"
              ></img>
            </div>

            {/* Button Section */}
            <div className="button-div">
              <button>Shop All</button>
            </div>
          </div>
        </Link>
      </div>
    </section>
  );
}
