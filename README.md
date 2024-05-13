# Kick Land - Sneaker E-commerce Platform

Kick Land is a full-stack web application that provides a platform for selling sneakers. It is built using React.js for the frontend and Java with Spring Boot for the backend. The application includes features such as user authentication, product browsing, shopping cart management, and profile management.

## Features

- **Frontend**:
  - **Home Page**: Showcase of featured products and promotions.
  - **Login Page**: User authentication and login functionality.
  - **About Us Page**: Information about the company and its mission.
  - **Help Page**: FAQs and support information.
  - **Terms of Service Page**: Legal terms and conditions.
  - **Refund Policy Page**: Details about the refund policy.
  - **Shop Product Page**: Detailed view of a single product with options to select size and quantity.
  - **Shop Products Page**: Display of all available products with filtering options for price or alphabetical order.
  - **Profile Page**: User profile management with details such as name, surname, email, phone number, and order history.
  - **Cart Page**: Display of all items in the shopping cart with total prices.

- **Backend**:
  - Built with Java using Spring Boot framework.
  - Uses Spring Data JPA for data access layer (no database included in this version).
  - RESTful API endpoints for various functionalities:
    - User authentication (login, register).
    - Newsletter subscription.
    - Contact Us form submissions.
    - Update profile details.
    - Update cart details.
   
## Payment Processing

Kick Land uses Stripe to process payments securely. Customers can checkout and pay for their orders using credit or debit cards.

## Location Services

For location details within South Africa, Kick Land utilizes Google Maps API. This enables accurate address lookup and location-based services tailored for South African users.

## Technologies Used

- **Frontend**:
  - React.js
  - React Router
  - Axios (for API calls)
  - HTML/CSS/JavaScript

- **Backend**:
  - Java
  - Spring Boot
  - Spring Data JPA
  - RESTful APIs

## Getting Started

1. Clone the repository:
```bash
git clone https://github.com/your-username/kick-land.git
```

2.Install dependencies for both frontend and backend:
```bash
cd kick-land/frontend
npm install

cd ../backend
# Setup your Java environment and dependencies (e.g., Maven)
```

3.Configure backend API endpoint in the frontend:
```bash
// frontend/src/api/config.js
const API_BASE_URL = 'http://localhost:8080/api'; // Update with your backend API URL
export default API_BASE_URL;
```

4.Start the frontend and backend servers:
```bash
# Start frontend server
cd ../frontend
npm start

# Start backend server (make sure your Java environment is set up)
cd ../backend
# Use your preferred method to start Spring Boot application (e.g., mvn spring-boot:run)
```

## Access the Application

- **Frontend:** [http://localhost:3000](http://localhost:3000)
- **Backend (API):** [http://localhost:8080](http://localhost:8080)

## API Endpoints

The backend exposes the following API endpoints (example URLs):

### Authentication:

- `POST /api/user/login`: User login.
- `POST /api/user/create-account`: User registration.

### Profile Management:

- `PUT /api/user/update-user/:userId`: Update user profile.

### Cart Management:

- `GET /api/user/cart/:email`: Get cart items.
- `POST /api/user/cart`: Add item to cart.
- `PUT /api/user/cart/:UserId/:ProductId/:productQuantity`: Update item quantity in cart.
- `DELETE /api/user/cart/:UserId/:ProductId`: Remove item from cart.

### Product Catalog:

- `GET /api/public/products`: Get all products.

### Contact Us:

- `POST /api/public/contactUs`: Submit contact form.

### Newsletter:

- `POST /api/public/newsletter`: Submit email form.

## Screenshots

![Home Page](https://github.com/NelaniMaluka/kick-land/blob/master/README%20images/Screenshot%20(338).png)
![Home Page](https://github.com/NelaniMaluka/kick-land/blob/master/README%20images/Screenshot%20(339).png)
![Footer](https://github.com/NelaniMaluka/kick-land/blob/master/README%20images/Screenshot%20(340).png)
![Shop All](https://github.com/NelaniMaluka/kick-land/blob/master/README%20images/Screenshot%20(341).png)
![Help](https://github.com/NelaniMaluka/kick-land/blob/master/README%20images/Screenshot%20(342).png)
![About Us](https://github.com/NelaniMaluka/kick-land/blob/master/README%20images/Screenshot%20(343).png)
![Log In](https://github.com/NelaniMaluka/kick-land/blob/master/README%20images/Screenshot%20(344).png)
![Create Account](https://github.com/NelaniMaluka/kick-land/blob/master/README%20images/Screenshot%20(345).png)
![Shop Product](https://github.com/NelaniMaluka/kick-land/blob/master/README%20images/Screenshot%20(346).png)
![Profile Dashboard](https://github.com/NelaniMaluka/kick-land/blob/master/README%20images/Screenshot%20(347).png)
![Cart Dashboard](https://github.com/NelaniMaluka/kick-land/blob/master/README%20images/Screenshot%20(349).png)
![Stripe Checkout](https://github.com/NelaniMaluka/kick-land/blob/master/README%20images/Screenshot%20(350).png)
