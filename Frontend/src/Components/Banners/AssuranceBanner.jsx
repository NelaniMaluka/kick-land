import "./AssuranceBanner.css";

const assurances = [
  {
    imgSrc: "/Images/FooterImages/trophy.png",
    alt: "Guarantee",
    heading: "30 Days Guarantee",
    description:
      "We have a 14 day return and refund (R&R) policy that absolutely assures that you get the best value out of the product you ordered. If you are not happy with the fit or the product. Just make the request and we'll handle the rest!",
  },
  {
    imgSrc: "/Images/FooterImages/smile.png",
    alt: "Authenticity",
    heading: "AUTHENTICITY GUARANTEE",
    headingClass: "authenticity-heading",
    description:
      "We only house 100% authentic sneakers with a quality service guarantee. You can be sure of our quality standard!",
  },
  {
    imgSrc: "/Images/FooterImages/delivery.png",
    alt: "Fast Shipping",
    heading: "Fast Shipping",
    description:
      "We offer free and fast shipping nationwide, making it easy for you to have access to your merchandise. As quickly as possible. 3-5 days and you're ready to look good sporting your new kit!",
  },
];

export default function AssuranceBanner() {
  return (
    <div className="footer-banner">
      <div className="container2">
        {assurances.map((item, index) => (
          <div key={index}>
            <img src={item.imgSrc} alt={item.alt} />
            <h2 className={item.headingClass || ""}>{item.heading}</h2>
            <p>{item.description}</p>
          </div>
        ))}
      </div>
    </div>
  );
}
