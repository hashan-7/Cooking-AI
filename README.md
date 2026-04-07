# Cook AI 🍳✨

Turn your leftovers into delicious meals! Cook AI is a smart web application that takes the ingredients you have on hand and generates creative, easy-to-follow recipes. It's designed to reduce food waste, inspire creativity, and provide practical cooking solutions.

This project is built with a production-ready mindset, featuring a robust Java Spring Boot backend, a custom AI engine deployed via Hugging Face Spaces, and a responsive frontend built with vanilla JavaScript.

---

## 🚀 Key Features

* **🔐 Secure Google OAuth2 Login:** Authenticate securely using your Google account with proper session handling and protection against unauthorized access.
* **🧠 Custom AI Recipe Engine:** Recipes are generated through a custom AI backend hosted on Hugging Face Spaces, ensuring full control, stability, and no dependency on expensive external APIs.
* **✨ Surprise Me!** Generate a completely random recipe using a dynamic ingredient selection.
* **🎨 Advanced Filtering:** Customize results using cuisine type, dietary preferences, and cooking time.
* **📚 Personal Recipe Library:** Save, view, and delete recipes with a clean and user-friendly interface.
* **💎 Modern Responsive UI:** Fully responsive dark-themed UI optimized for both desktop and mobile devices.
* **🔄 Stable API Architecture:** Backend communicates with a custom AI service instead of direct third-party APIs, improving reliability and scalability.

---

## 🛠️ Built With

This project uses a modern and scalable technology stack:

**Backend:**
* Java 17
* Spring Boot 3
* Spring Security (OAuth2)
* Spring Data JPA
* Maven

**Frontend:**
* HTML5
* CSS3
* Vanilla JavaScript (ES6+)

**Database:**
* MySQL

**AI Engine:**
* Hugging Face Spaces (Custom FastAPI service)
* Custom prompt-based recipe generation logic

---

## ⚙️ Getting Started

Follow these steps to run the project locally.

### Prerequisites

Make sure you have:

* JDK 17+
* Apache Maven
* MySQL server

---

### Installation & Setup

1. **Clone the repository:**
```bash
git clone https://github.com/hashan-7/cook-ai.git
cd cook-ai
