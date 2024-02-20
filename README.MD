# Contact Manager Application

This is a full-stack contact manager application built with React for the frontend and Spring Boot for the backend. The implementation of this application is based on the tutorial provided in the YouTube video [Building a Full Stack Contact Manager Application with React and Spring Boot](https://www.youtube.com/watch?v=-LUA-LHXobE). It allows users to manage their contacts efficiently, including CRUD operations and photo uploads. This README provides an overview of the application's structure and functionality.

## Backend (Spring Boot)

The backend of the application is developed using Spring Boot, providing RESTful APIs for managing contacts and handling file uploads. Here's a breakdown of the backend components:

1. **Application.java**: Main entry point for the Spring Boot application.
2. **TestDataInitializer.java**: Initializes test data for contacts using the `ContactRepository`.
3. **CorsConfig.java**: Configures Cross-Origin Resource Sharing (CORS) for the application.
4. **Constant.java**: Defines constants, including the directory for storing contact photos.
5. **ContactController.java**: Defines REST endpoints for CRUD operations on contacts and handles file uploads.
6. **Contact.java**: Defines the `Contact` entity with attributes representing contact information, mapped to a database table.
7. **ContactRepository.java**: Provides methods for CRUD operations on contacts using Spring Data JPA.
8. **ContactService.java**: Implements business logic for managing contacts and handles file uploads.

## Frontend (React)

The frontend of the application is developed using React, providing a user-friendly interface for managing contacts. Here's an overview of the frontend files:

- **index.js**: Entry point of the React application, rendering the `App` component.
- **App.js**: Main container for the application, including routing functionality.
- **ContactList.js**: Displays a list of contacts.
- **Contact.js**: Represents a single contact item in the list.
- **ContactDetail.js**: Displays detailed information about a single contact.
- **Header.js**: Renders the header section of the application.
- **ContactService.js**: Contains functions for interacting with the backend API.
- **ToastService.js**: Provides functions for displaying toast notifications.

## Functionality

The application allows users to perform the following actions:

- View a list of contacts with basic information.
- View detailed information about a specific contact.
- Add new contacts.
- Edit existing contacts.
- Delete contacts.
- Upload and update contact photos.

## Setup

To run the application locally, follow these steps:

1. Clone this repository.
2. Navigate to the `backend` directory and run the Spring Boot application.
3. Navigate to the `frontend` directory and run the React application.
4. Access the application in your web browser at `http://localhost:3000`.

## Dependencies

- Backend:
  - Spring Boot
  - Spring Data JPA
  - H2 Database (for development)
  - CORS
- Frontend:
  - React
  - react-router-dom
  - react-toastify

## Conclusion

This contact manager application provides a reference for creating a simple full stack application, with a backend developed in Spring Boot and a  frontend developed in React, following the tutorial provided in the YouTube video mentioned above. 


