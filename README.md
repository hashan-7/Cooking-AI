Cook AI 🍳✨
by h7 (synapse AI)
Cook AI is an intelligent web application designed to transform your kitchen experience. By leveraging the power of the Google Gemini API, this application takes the ingredients you have on hand and generates creative, delicious, and easy-to-follow recipes. It's the perfect tool to combat food waste, inspire culinary creativity, and answer the age-old question: "What should I cook today?"

This project is built with a professional, production-ready mindset, focusing on a robust backend and a dynamic, modern frontend.

🚀 Features
This application is packed with features designed for a seamless and engaging user experience:

🔐 Secure Google OAuth2 Login: Easy and secure authentication using your Google account. User sessions are managed securely on the backend, with user data saved to a persistent MySQL database.

🧠 AI-Powered Recipe Generation: Enter the ingredients you have, and our AI, powered by the Google Gemini API, will generate a unique and creative recipe for you.

✨ Surprise Me! Don't know what to cook? Let our AI pick a random combination of popular ingredients and generate a surprise recipe for you!

🎨 Advanced Filtering: Refine your recipe search with advanced filters for:

Cuisine: (e.g., Italian, Chinese, Sri Lankan)

Dietary Needs: (e.g., Vegetarian, Vegan, Gluten-Free)

Cook Time: (e.g., Under 30 minutes)

📚 Personal Recipe Library:

Save Recipes: Liked a recipe? Save it to your personal library with a single click.

View & Manage: Browse your saved recipes in a clean, organized library.

Delete Recipes: Easily remove recipes you no longer need with a confirmation step.


💡 Ingredient Auto-Suggestions: As you type your ingredients, the app provides helpful suggestions to speed up the process.

💎 Professional & Modern UI: A stunning, fully responsive user interface with a dark theme, glassmorphism effects, and smooth animations, built entirely with vanilla HTML, CSS, and JavaScript.

🛠️ Built With
This project was built using a modern, robust technology stack:

Backend:

Java 17

Spring Boot 3 - For building the robust and scalable backend server.

Spring Security - For handling authentication and security.

Spring Data JPA - For database interaction.

Maven - For dependency management.

Database:

MySQL - As the persistent relational database.

Frontend:

HTML5

CSS3 (with Custom Properties for theming)

Vanilla JavaScript (ES6+)

APIs:

Google Gemini API - For intelligent recipe generation.

⚙️ Getting Started
To get a local copy up and running, follow these simple steps.

Prerequisites
Make sure you have the following installed on your machine:

Java Development Kit (JDK) 17 or later.

Apache Maven.

A running MySQL server instance.

Navigate to the project directory

cd cook-ai

Create your MySQL Database

Log in to your MySQL server.

Create a new database named cook_ai_db.

CREATE DATABASE cook_ai_db;


# Server Port
server.port=8080

# Database Configuration
spring.datasource.url=jdbc:mysql://localhost:3306/cook_ai_db
spring.datasource.username=root
spring.datasource.password=${DB_PASSWORD}

# Google OAuth2 Client Properties
spring.security.oauth2.client.registration.google.client-id=${GOOGLE_CLIENT_ID}
spring.security.oauth2.client.registration.google.client-secret=${GOOGLE_CLIENT_SECRET}

# Gemini API Key
gemini.api.key=${GEMINI_API_KEY}

Set up Environment Variables in IntelliJ IDEA

This step ensures your application can find your secret keys on your local machine without them being written in the code.

In IntelliJ, go to Run -> Edit Configurations....

Find the "Environment variables" field.

Click the icon to add new variables. Add the following, replacing the placeholder text with your actual secret keys:

DB_PASSWORD=your_actual_database_password
GOOGLE_CLIENT_ID=your_actual_google_client_id
GOOGLE_CLIENT_SECRET=your_actual_google_client_secret
GEMINI_API_KEY=your_actual_gemini_api_key

Click Apply, then OK.

Run the application

You can now run the application using your IDE or by using the Maven wrapper:

./mvnw spring-boot:run

Open the application

Open your browser and go to http://localhost:8080.