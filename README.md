# ChâTop - Rental Portal Application

Full-stack web application for rental property management with authentication, image upload, and messaging features.

## Tech Stack

**Frontend:**

- Angular 14.1.3
- Angular Material
- TypeScript 4.7.4

**Backend:**

- Spring Boot 3.5.7
- Java 17
- Spring Security with JWT authentication
- Spring Data JPA / Hibernate
- MySQL 8

**Infrastructure:**

- Cloudinary (image storage)
- Maven (backend build)
- npm (frontend build)

## Prerequisites

Before you begin, ensure you have installed:

- **Node.js** 14.x or higher
- **npm** 6.x or higher
- **Java JDK** 17
- **Maven** 3.8+
- **MySQL** 8.x
- **Git**

## Installation & Setup

### 1. Clone the repository

```bash
git clone https://github.com/MaxLeblc/chatop-app.git
cd chatop-app
```

### 2. Database Setup

**Create MySQL database:**

```bash
mysql -u root -p
```

```sql
CREATE DATABASE chatop;
```

**Import the schema:**

```bash
mysql -u root -p chatop < ressources/sql/script.sql
```

### 3. Backend Configuration

**Navigate to backend directory:**

```bash
cd backend
```

**Create `.env` file** in `backend/` directory:

```env
# Database Configuration
DB_URL=jdbc:mysql://localhost:3306/chatop?serverTimezone=UTC
DB_USERNAME=root
DB_PASSWORD=your_mysql_password

# JWT Configuration
JWT_SECRET=your-super-secret-jwt-key-min-64-characters-for-HS512-algorithm
JWT_EXPIRATION=86400000

# Cloudinary Configuration (get from https://cloudinary.com)
CLOUDINARY_URL=cloudinary://api_key:api_secret@cloud_name
```

**Important:**

- `JWT_SECRET` must be at least 64 characters for HMAC SHA-512
- Create a free Cloudinary account at https://cloudinary.com to get your `CLOUDINARY_URL`

**Install dependencies and run backend:**

```bash
mvn clean install
mvn spring-boot:run
```

Backend will start on **http://localhost:3001**

### 4. Frontend Configuration

**Navigate to frontend directory:**

```bash
cd ../frontend
```

**Install dependencies:**

```bash
npm install
```

**Run frontend:**

```bash
npm start
```

Frontend will start on **http://localhost:4200**

## API Documentation

Interactive API documentation is available via **Swagger UI** when the backend is running:

**http://localhost:3001/swagger-ui/index.html**

### Available Endpoints

**Authentication:**

- `POST /api/auth/register` - Register new user
- `POST /api/auth/login` - Login and receive JWT token
- `GET /api/auth/me` - Get current authenticated user

**Rentals:**

- `GET /api/rentals` - List all rentals
- `GET /api/rentals/{id}` - Get rental by ID
- `POST /api/rentals` - Create new rental (with image upload)
- `PUT /api/rentals/{id}` - Update rental

**Messages:**

- `POST /api/messages` - Send message about a rental

**Users:**

- `GET /api/user/{id}` - Get user by ID

### Authentication

All endpoints except `/api/auth/login` and `/api/auth/register` require JWT authentication.

Include the token in the `Authorization` header:

```
Authorization: Bearer <your-jwt-token>
```

## Development

### Project Structure

```
chatop-app/
├── backend/                    # Spring Boot backend
│   ├── src/main/java/com/chatop/api/
│   │   ├── configuration/     # Security, CORS, OpenAPI config
│   │   ├── controller/        # REST controllers
│   │   ├── dto/               # Data Transfer Objects
│   │   ├── model/             # JPA entities
│   │   ├── repository/        # Database repositories
│   │   ├── security/          # JWT filters and providers
│   │   └── service/           # Business logic
│   ├── src/main/resources/
│   │   └── application.properties
│   ├── .env                   # Environment variables (create this)
│   └── pom.xml
├── frontend/                   # Angular frontend
│   ├── src/app/
│   │   ├── components/        # Shared components
│   │   ├── features/          # Feature modules (auth, rentals)
│   │   ├── guards/            # Route guards
│   │   ├── interceptors/      # HTTP interceptors
│   │   └── services/          # API services
│   └── package.json
└── ressources/
    ├── postman/               # Postman collection for API testing
    └── sql/                   # Database schema
```

### Running Tests

**Backend tests:**

```bash
cd backend
mvn test
```

**Frontend tests:**

```bash
cd frontend
npm test
```

### Building for Production

**Backend:**

```bash
cd backend
mvn clean package
java -jar target/api-chatop-0.0.1-SNAPSHOT.jar
```

**Frontend:**

```bash
cd frontend
npm run build
```

Build artifacts will be in `frontend/dist/`

## Postman Collection

A Postman collection is available for testing the API:

`ressources/postman/rental.postman_collection.json`

**Import it into Postman:**

1. Open Postman
2. File > Import
3. Select `rental.postman_collection.json`

## Security Features

- **JWT Authentication** (HMAC SHA-512, 24h expiration)
- **BCrypt** password hashing
- **CORS** configured for frontend origin
- **Spring Security** with stateless session management
- **Input validation** with Jakarta Bean Validation
- **Environment variables** for sensitive credentials

## Troubleshooting

**Backend doesn't start:**

- Verify MySQL is running: `sudo systemctl status mysql`
- Check `.env` file exists in `backend/` directory
- Verify database credentials in `.env`
- Ensure port 3001 is not in use

**Frontend can't reach backend:**

- Verify backend is running on http://localhost:3001
- Check proxy configuration in `frontend/src/proxy.config.json`
- Clear browser cache and restart Angular dev server

**Image upload fails:**

- Verify `CLOUDINARY_URL` is correctly set in `.env`
- Check Cloudinary account is active
- Ensure image file size is reasonable (<10MB)

**JWT errors:**

- Verify `JWT_SECRET` is at least 64 characters
- Check token hasn't expired (24h lifetime)
- Ensure `Authorization` header format is correct

## License

This project is for educational purposes.

## Author

MaxLeblc - github.com/MaxLeblc
