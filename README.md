# Cook AI 🍳✨

Turn your leftovers into delicious meals! Cook AI is a smart web application that takes the ingredients you have on hand and generates creative, easy-to-follow recipes. It's the perfect tool to combat food waste, inspire culinary creativity, and answer the age-old question: "What should I cook today?"

This project is built with a production-ready mindset, featuring a robust Java Spring Boot backend and a dynamic, vanilla JavaScript frontend.

---

## 🚀 Key Features

* **🔐 Secure Google OAuth2 Login:** Authenticate easily and securely with your Google account. User data is saved to a persistent MySQL database.
* **🧠 Hybrid AI Recipe Generation:** Enter your ingredients, and our intelligent system—powered by a primary Gemini API with a Hugging Face fallback—finds the perfect recipe for you, ensuring high availability.
* **✨ Surprise Me!** Feeling adventurous? Let our AI pick a random combination of popular ingredients and generate a surprise recipe.
* **🎨 Advanced Filtering:** Refine your search with advanced filters for cuisine, dietary needs (e.g., Vegetarian, Gluten-Free), and cook time.
* **📚 Personal Recipe Library:** Save your favorite recipes to a personal library. Easily view, manage, and delete them with a single click.
* **💎 Modern & Responsive UI:** A stunning, fully responsive user interface with a dark theme, built entirely with vanilla HTML, CSS, and JavaScript.

---

## 🛠️ Built With

This project uses a modern and robust technology stack:

**Backend:**
* [Java 17](https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html)
* [Spring Boot 3](https://spring.io/projects/spring-boot)
* [Spring Security (OAuth2)](https://spring.io/projects/spring-security)
* [Spring Data JPA](https://spring.io/projects/spring-data-jpa)
* [Maven](https://maven.apache.org/)

**Frontend:**
* HTML5
* CSS3 (with Custom Properties)
* Vanilla JavaScript (ES6+)

**Database:**
* [MySQL](https://www.mysql.com/)

**APIs:**
* Google Gemini API
* Hugging Face Inference API

---

## ⚙️ Getting Started

To get a local copy up and running, follow these simple steps.

### Prerequisites

Make sure you have the following installed on your machine:
* JDK 17 or later
* Apache Maven
* A running MySQL server instance

### Installation & Configuration

1.  **Clone the repository:**
    ```sh
    git clone [https://github.com/hashan-7/cook-ai.git](https://github.com/hashan-7/cook-ai.git)
    cd cook-ai
    ```

2.  **Create the MySQL Database:**
    Log in to your MySQL server and create a new database.
    ```sql
    CREATE DATABASE cook_ai_db;
    ```

3.  **Create and Configure `application.properties`:**

    **⚠️ Important:** This file is intentionally excluded from the repository (`.gitignore`) to keep sensitive information like API keys and database passwords secure. **You must create this file yourself.**

    * Navigate to `src/main/resources/`.
    * Create a new file named `application.properties`.
    * Copy the content below into your new file and replace the placeholder values (`YOUR_...`) with your actual credentials.

    ```properties
    # Server Port
    server.port=8080

    # Database Configuration
    spring.datasource.url=jdbc:mysql://localhost:3306/cook_ai_db
    spring.datasource.username=YOUR_DB_USERNAME
    spring.datasource.password=YOUR_DB_PASSWORD

    # Google OAuth2 Client Properties
    spring.security.oauth2.client.registration.google.client-id=YOUR_GOOGLE_CLIENT_ID
    spring.security.oauth2.client.registration.google.client-secret=YOUR_GOOGLE_CLIENT_SECRET

    # Gemini API Key
    gemini.api.key=YOUR_GEMINI_API_KEY

    # Hugging Face API Configuration
    huggingface.api.key=YOUR_HUGGING_FACE_API_KEY
    huggingface.api.url=[https://api-inference.huggingface.co/models/gpt2](https://api-inference.huggingface.co/models/gpt2)
    ```

4.  **Run the application:**
    Open the project in your favorite IDE (like IntelliJ IDEA) and let Maven download the dependencies. Then, find the `CookAiApplication.java` file and run the `main` method.

    The application will be available at `http://localhost:8080`.

---

## 🤝 Contributing

Contributions are what make the open-source community such an amazing place to learn, inspire, and create. Any contributions you make are **greatly appreciated**.

1.  Fork the Project
2.  Create your Feature Branch (`git checkout -b feature/AmazingFeature`)
3.  Commit your Changes (`git commit -m 'Add some AmazingFeature'`)
4.  Push to the Branch (`git push origin feature/AmazingFeature`)
5.  Open a Pull Request

---

## 📄 License

Distributed under the MIT License.

---

<p align="center">
  Made with ❤️ by h7
</p>
