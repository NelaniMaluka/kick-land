# Manar Facility Solutions – React + Vite Website

A responsive, modern business website built using **React**, **Vite**, and **React Router**. This application showcases services, company information, and allows users to get in touch or book a service online.

[![Live Site](https://img.shields.io/badge/Live_Demo-Visit-brightgreen)](https://www.manarfacilitysolutions.com)
[![License](https://img.shields.io/badge/License-MIT-blue.svg)](https://opensource.org/licenses/MIT)

---

## 📁 Folder Structure

```txt
manar-facility/
├── public/
│   ├── Images/                # Static assets like logos, icons, backgrounds
│   └── favicon.ico            # Site icon
├── src/
│   ├── Components/            # All reusable UI components
│   │   ├── GlobalSection/        # Global elements 
│   │   ├── HomePageSections/     # Home Page elements
│   │   ├── AboutPageSections/    # About Page elements
│   │   └── ServicesPageSections/ # Services Page elements
│   ├── Pages/                 # Top-level pages (HomePage, AboutPage, ServicesPage)
│   ├── App.jsx                # App layout and route setup
│   └── main.jsx               # React/Vite entry point
├── index.html                 # HTML template
├── package.json               # Project dependencies and scripts
├── vite.config.js             # Vite configuration
└── README.md                  # Project documentation
```

## 🎨 Figma Design

The website was first designed in Figma before development. You can view the wireframes and design system below:

[![Figma](https://img.shields.io/badge/View%20on-Figma-blue?logo=figma)](https://www.figma.com/design/aEWDoevHS1s6cMK9Tdl6zz/Manar-Facility-Solutions---Designs?node-id=530-887&t=yfV2ZlNORORvevKH-0)

> This design guided layout decisions for responsiveness, branding, and user experience across pages.

## 🚀 Getting Started Locally

### Prerequisites

Ensure you have the following installed:

- [Node.js](https://nodejs.org/en/) (v16 or higher)
- [Git](https://git-scm.com/)

### Installation

1. **Clone the repository**

   git clone https://github.com/your-username/manar-facility.git
   cd manar-facility

2. **Install dependencies**

   npm install

3. **Start development server**

   npm run dev

4. **Open in your browser**

   http://localhost:5173

## 🛠️ Technologies Used

- **React** – Component-based UI
- **Vite** – Fast build tool
- **React Router** – Routing & navigation
- **JSX** – Declarative UI
- **CSS Modules** – Scoped component styling
- **Helmet Async** – SEO & meta tags
- **Material UI Icons** – For navbar mobile icons

## 🌐 SEO & Performance

- Responsive design (desktop, tablet, mobile)
- Meta tags via `react-helmet-async`
- Image preloading for hero backgrounds
- Schema markup (JSON-LD) for LocalBusiness

## 📦 Build for Production

1. Build the project:

   npm run build

2. Preview the production build locally:

   npm run preview

3. Deploy using a service like:

   - [Namecheap](https://www.namecheap.com/)


## 🙌 Contributing

Pull requests are welcome! For major changes, please open an issue first to discuss what you would like to change.

## 📄 License

This project is licensed under the [MIT License](https://opensource.org/licenses/MIT).

## ✨ Credits

| Role      | Name                       | GitHub / LinkedIn                                  |
|-----------|----------------------------|-----------------------------------------------------|
| Designer  | Marina Au                  | [LinkedIn](https://www.linkedin.com/in/aumarina/)  |
| Developer | Nelani Maluka              | [GitHub](https://github.com/NelaniMaluka)          |
| Developer | Godfred                    | [GitHub](https://github.com/lux-mundi)             |
| Developer | Sairam Soundararajan       | [GitHub](https://github.com/ssoundarararajan)      |
| Developer | Divine Omale               | [GitHub](https://github.com/DivineOmale)           |

> Built for **Manar Facility Solutions** – Gainesville, Alachua County, FL
