import { useSelector } from "react-redux";
import moment from "moment";

export default function OrdersView() {
  const orders = useSelector((state) => state.order.order);
  console.log(orders);

  if (orders.length === 0) {
    return (
      <div className="no-cart-items">
        <span>
          <span class="material-symbols-outlined">playlist_add</span> No cart
          items available.
        </span>
      </div>
    );
  }

  const formatDate = (date) => moment(date).format("MMMM D, YYYY");

  return (
    <div className="parent-container">
      <div className="order-container">
        {orders.map((orderItem) => {
          return (
            <div className="cart-card" key={orderItem.orderId}>
              <span>Order Date: {formatDate(orderItem.orderDate)}</span>
              <span>Order: #{orderItem.orderId}</span>
              <span>Delivery Date: {formatDate(orderItem.deliveryDate)}</span>
              <span>Shipping To: {formatDate(orderItem.deliveryDate)}</span>
            </div>
          );
        })}
      </div>
    </div>
  );
}
