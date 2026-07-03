Pokemon Team Manager

Project Overview

Pokemon Team Manager is a full-stack web application designed to help users curate, manage, and explore their favorite Pokemon teams. This project integrates a robust Spring Boot backend with a responsive React frontend, allowing users to search for Pokemon, filter by elemental types, and save their customized teams to a MySQL database.

Features

User authentication and session management.  
Asynchronous Pokemon searching and data synchronization using the PokeAPI.  
Advanced filtering by dual elemental types .  
Interactive team builder with drag-and-drop-like selection, highlighting, and team switching.  
Duplicate Pokemon validation to ensure team uniqueness.  
Responsive design for seamless use across different devices.

Technology Stack

Backend: Kotlin, Spring Boot, Hibernate, REST API.  
Frontend: React.js, Axios, CSS.  
Database: MySQL.  
Communication: Asynchronous AJAX/Fetch API integration.  

Getting Started

PrerequisitesJDK 17 or higher
Node.js (v16+) and npm
MySQL Server

Installation

Clone the repository: git clone [your-repository-link]
Database Setup: Create a database named pokemon_db in MySQL and import the provided database.sql file.
Backend Setup: Navigate to the backend directory and run: 
./mvnw spring-boot:run
Frontend Setup: Navigate to the frontend directory and run: 
npm install
npm start

API Documentation

GET /api/pokemons: Retrieve all Pokemon or filter by type.
GET /api/pokemons/search?query={name}: Search for Pokemon by name and sync with local database.  
POST /api/teams/save: Save a user's customized team.  

Project Structure
/backend: Contains the Kotlin Spring Boot source code, controllers, services, and repositories.  
/frontend: Contains the React application components and styling.  
database.sql: The SQL schema file for initializing the MySQL database.