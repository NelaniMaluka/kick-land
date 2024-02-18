import "./FooterBanner.css";

function FooterBanner() {
  return (
    <div className="footer-banner">
      <div className="container2">
        <div>
          <img src="/Images/FooterImages/trophy.png" />
          <h2>30 Days Guarantee</h2>
          <p>
            We have a 14 day return and refund (R&R) policy that absolutely
            assures that you get the best value out of the product you ordered.
            If you are not happy with the fit or the product. Just make the
            request and we'll handle the rest!
          </p>
        </div>
        <div>
          <img src="/Images/FooterImages/smile.png" />
          <h2 className="authenticity-heading">AUTHENTICITY GUARANTEE</h2>
          <p>
            We only house 100% authentic sneakers with a quality service
            guarantee. You can be sure of our quality standard!
          </p>
        </div>
        <div>
          <img src="/Images/FooterImages/delivery.png" />
          <h2>Fast Shipping</h2>
          <p>
            We offer free and fast shipping nationwide, making it easy for you
            to have access to your merchandise. As quickly as possible. 3-5 days
            and you're ready to look good sporting your new kit!
          </p>
        </div>
      </div>
    </div>
  );
}

export default FooterBanner;
