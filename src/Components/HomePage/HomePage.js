import HomePageView1 from "./HomePageView1";
import HomePageView2 from "./HomePageView2";
import FooterBanner from "../Footer/FooterBanner";

function HomePage() {
  return (
    <div>
      <HomePageView1 item="Dunk" />
      <HomePageView2 item1="AirMax" item2="Jordan" />
      <HomePageView1 item="AirForce" />
      <HomePageView2 item1="Jordan" item2="AirMax" />
      <FooterBanner />
    </div>
  );
}

export default HomePage;
