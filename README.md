# Spring Security OAuth2 – Google Login

A Spring Boot project demonstrating **OAuth 2.0 authentication using Google** with Spring Security.

This project focuses on understanding how OAuth2 Login works, how Spring Security integrates with an OAuth2 provider, and how the authorization code flow authenticates users.

---

## 🚀 Tech Stack

* Java
* Spring Boot
* Spring Security
* Spring Security OAuth2 Client
* Maven
* Google OAuth 2.0
* IntelliJ IDEA / VS Code

---

## 📌 Project Objective

The main objective of this project is to understand and implement:

* OAuth 2.0 authentication
* Google OAuth2 Login
* Spring Security OAuth2 Client
* Authorization Code Flow
* OAuth2 authorization endpoint
* OAuth2 callback/redirect
* Retrieving authenticated user information
* Spring Security's OAuth2 authentication flow

> This project is focused on **OAuth2 Login** only.

### Not included

* JWT authentication
* Refresh tokens
* RBAC
* Custom token generation
* Custom OAuth2 authorization server

---

## 🔐 What is OAuth 2.0?

OAuth 2.0 is an authorization framework that allows an application to obtain limited access to a user's resources without requiring the application to know the user's password.

In this project, Google acts as the **OAuth 2.0 Provider**.

The application redirects the user to Google for authentication.

---

## 🔄 OAuth2 Login Flow

The basic flow is:

```text
User
 │
 │ 1. Click "Login with Google"
 ▼
Spring Boot Application
 │
 │ 2. Redirect to Google
 ▼
Google Authorization Server
 │
 │ 3. User logs in
 │
 │ 4. User grants permission
 ▼
Google
 │
 │ 5. Authorization Code
 ▼
Spring Boot Application
 │
 │ 6. Exchange code for tokens
 ▼
Google
 │
 │ 7. Access Token / ID Token
 ▼
Spring Boot Application
 │
 │ 8. Authenticate user
 ▼
Authenticated User
```

---

## 🏗️ Project Structure

```text
spring-security-oauth2
│
├── src
│   └── main
│       ├── java
│       │   └── com.backend.spring_security_oauth2
│       │       ├── security
│       │       │   └── SecurityConfig.java
│       │       │
│       │       └── SpringSecurityOauth2Application.java
│       │
│       └── resources
│           └── application.properties
│
├── .env
├── .gitignore
├── pom.xml
└── README.md
```

---

## 📦 Maven Dependency

The main dependency used for OAuth2 Client functionality is:

```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-security-oauth2-client</artifactId>
</dependency>
```

This provides the required Spring Security OAuth2 Client functionality.

---

## ⚙️ Configuration

The application uses environment variables for Google credentials.

### `application.properties`

```properties
spring.application.name=spring-security-oauth2

spring.security.oauth2.client.registration.google.client-id=${GOOGLE_CLIENT_ID}
spring.security.oauth2.client.registration.google.client-secret=${GOOGLE_CLIENT_SECRET}

spring.security.oauth2.client.registration.google.scope=openid,profile,email
```

---

## 🔑 Environment Variables

Create a `.env` file in the project root:

```env
GOOGLE_CLIENT_ID=your-google-client-id
GOOGLE_CLIENT_SECRET=your-google-client-secret
```

### ⚠️ Security

Never commit `.env` to GitHub.

Add it to `.gitignore`:

```gitignore
.env
target/
.idea/
.vscode/
*.iml
```

The Google Client Secret should always remain private.

---

## 🌐 Google Cloud Configuration

Create an OAuth 2.0 Client ID in Google Cloud Console.

The application type should be:

```text
Web application
```

Add the following redirect URI:

```text
http://localhost:8080/login/oauth2/code/google
```

Spring Security uses this endpoint to receive the authorization response from Google.

---

## 🔒 Security Configuration

The application uses Spring Security to require authentication for all requests.

Example:

```java
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http)
            throws Exception {

        http
            .authorizeHttpRequests(auth -> auth
                .anyRequest().authenticated()
            )
            .oauth2Login(Customizer.withDefaults())
            .logout(logout -> logout
                .logoutSuccessUrl("/login?logout")
            );

        return http.build();
    }
}
```

### Important

```java
.oauth2Login(Customizer.withDefaults())
```

enables OAuth2 Login through Spring Security.

---

## 🔗 Important OAuth2 Endpoints

### Start Google Login

```text
/oauth2/authorization/google
```

For example:

```text
http://localhost:8080/oauth2/authorization/google
```

Spring Security uses this endpoint to initiate the OAuth2 authentication process.

### Google Callback

```text
/login/oauth2/code/google
```

Google redirects the user back to this endpoint after authentication.

---

## 🧪 Running the Project

### 1. Clone the repository

```bash
git clone https://github.com/YOUR_USERNAME/spring-security-oauth2.git
```

### 2. Navigate into the project

```bash
cd spring-security-oauth2
```

### 3. Configure environment variables

Create:

```text
.env
```

and add:

```env
GOOGLE_CLIENT_ID=your-client-id
GOOGLE_CLIENT_SECRET=your-client-secret
```

### 4. Run the application

Using Maven Wrapper:

```bash
./mvnw spring-boot:run
```

On Windows:

```bash
mvnw.cmd spring-boot:run
```

### 5. Open the OAuth2 login endpoint

```text
http://localhost:8080/oauth2/authorization/google
```

You should be redirected to Google's login page.

---

## 🧠 Key Concepts Learned

### OAuth2 Client

The Spring Boot application acts as an **OAuth2 Client**.

```text
Application → OAuth2 Client
Google      → Authorization Server
User        → Resource Owner
```

### Authorization Server

Google authenticates the user and provides authorization information to the application.

### Authorization Code

After successful authentication, Google sends an authorization code to the application.

The application exchanges this code for tokens.

### ID Token

When using the `openid` scope, Google provides identity information through an ID token.

### Access Token

An access token represents authorization to access permitted resources from the provider.

---

## 🔍 OAuth2 vs JWT

OAuth2 and JWT are not the same thing.

### OAuth2

OAuth2 is an **authorization framework/protocol**.

### JWT

JWT is a **token format**.

OAuth2 can use JWTs, but OAuth2 does not mean JWT.

This project focuses on **OAuth2 Login**, not implementing JWT authentication manually.

---

## ❌ What This Project Does Not Implement

This project intentionally does not implement:

```text
❌ JWT Authentication
❌ Refresh Token implementation
❌ Role-Based Access Control
❌ Custom JWT generation
❌ Custom OAuth2 Authorization Server
❌ Password authentication
```

These topics can be implemented in separate Spring Security projects.

---

## 🛠️ Common Problems

### `Error 401: invalid_client`

Possible causes:

* Incorrect Google Client ID
* Incorrect Google Client Secret
* Client ID and Client Secret belong to different OAuth clients
* OAuth client was deleted
* Incorrect environment variables
* Google Cloud project configuration issue

### Redirect URI mismatch

Make sure Google Cloud contains:

```text
http://localhost:8080/login/oauth2/code/google
```

The redirect URI must exactly match the URI expected by Spring Security.

---

## 📚 Learning Flow

This project follows:

```text
OAuth2 Theory
      ↓
OAuth2 Architecture
      ↓
Google OAuth2 Setup
      ↓
Spring Security Configuration
      ↓
Authorization Code Flow
      ↓
Google Login
      ↓
Authenticated User
      ↓
Testing
      ↓
Interview Preparation
```

---

## 🎯 Interview Questions

### 1. What is OAuth2?

OAuth2 is an authorization framework that allows applications to obtain limited access to protected resources without sharing user credentials.

### 2. What is an OAuth2 Client?

An application that requests authorization from an OAuth2 provider.

### 3. What is an Authorization Server?

A server responsible for authenticating the resource owner and issuing authorization credentials/tokens.

### 4. What is the Authorization Code Flow?

A flow where the client receives an authorization code and exchanges it with the authorization server for tokens.

### 5. What is `oauth2Login()`?

It enables OAuth2-based login functionality in Spring Security.

### 6. Why is the redirect URI important?

It specifies where the OAuth provider should send the authorization response after authentication.

### 7. Is OAuth2 the same as JWT?

No.

OAuth2 is an authorization framework, while JWT is a token format.

### 8. Why should Client Secret not be pushed to GitHub?

Because anyone who obtains the secret could potentially misuse the OAuth client.

---

## 👨‍💻 Author

**Prudhvi**

B.Tech – Computer Science and Engineering

Focused on Java, Spring Boot, Spring Security, React and Software Development.

---

## ⭐ Future Improvements

Possible future extensions:

* Custom OAuth2 success handler
* Display authenticated Google user information
* Custom login page
* OAuth2 failure handling
* Multiple OAuth2 providers
* GitHub OAuth2 Login

---

## 📄 License

This project is created for learning and educational purposes.
