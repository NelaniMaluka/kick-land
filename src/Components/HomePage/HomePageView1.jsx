import "./HomePage.css";

function HomePageView1({ item }) {
  return (
    <section>
      <div className="section">
        <a href="../shop?item=AirMax">
          <div className="banner-section">
            <div>
              <img src={`/Images/HomeImages/back-Banner-${item}.jpg`}></img>
            </div>
            <button>Shop {item}</button>
          </div>
        </a>
        <div className="items-section">item</div>
      </div>
    </section>
  );
}

export default HomePageView1;
