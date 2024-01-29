import FooterBanner from "../Footer/FooterBanner";
import { useParams } from "react-router-dom";

function Shop() {
  const { item } = useParams();

  return (
    <div>
      {item}
      <FooterBanner />
    </div>
  );
}

export default Shop;
