import { useSelector } from "react-redux";
import moment from "moment";
import { useAuth } from "../../../Context/AuthContext";
import "./OrdersView.css";

export default function OrdersView() {
  const orders = useSelector((state) => state.order.order);
  const authContext = useAuth();
  const isProducts = authContext.isProducts;

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
    <div className="parent-container-1">
      <div className="order-container">
        {orders.map((orderItem) => {
          return (
            <div className="order-card" key={orderItem.orderId}>
              <div className="table-container">
                <table>
                  <thead>
                    <tr>
                      <th></th>
                      <th></th>
                      <th>Quantity</th>
                      <th>Price</th>
                    </tr>
                  </thead>
                  <tbody>
                    {orderItem.orders.map((orderProoduct, index) => {
                      const product = isProducts.find(
                        (p) => p.id === orderProoduct.productId
                      );
                      return (
                        <tr
                          key={`${orderItem.orderId}-${orderProoduct.productId}-${index}`}
                        >
                          <td>{product.name}</td>
                          <td>{orderProoduct.size}</td>
                          <td>{orderProoduct.quantity}</td>
                          <td>{product.price}</td>
                        </tr>
                      );
                    })}
                  </tbody>
                </table>
              </div>
              <div className="details-container">
                <span>Order Date: {formatDate(orderItem.orderDate)}</span>
                <span>Order: #{orderItem.orderId}</span>
                <span>Delivery Date: {formatDate(orderItem.deliveryDate)}</span>
                <span>
                  Shipping To: {orderItem.address} {orderItem.province}
                </span>
              </div>
            </div>
          );
        })}
      </div>
    </div>
  );
}
