# 🌟 Hotel Ratings Service - Microservices Application

This is a Microservices-based Java application built with **Spring Boot**, designed to manage a hotel rating system. It consists of multiple independently deployable services that communicate via **REST APIs** and **Spring Cloud** components.

---

## 🧩 Microservices Included

### 1. 🔐 User Service
- Manages hotel users.
- Provides REST APIs to create, read, update, and delete users.
- Communicates with Rating Service and Hotel Service to fetch user-specific data.

### 2. 🏨 Hotel Service
- Manages hotel data.
- Provides APIs to create, update, fetch, and delete hotel details.
- Shares hotel details when requested by Rating or User Service.

### 3. ⭐ Rating Service
- Handles user ratings for hotels.
- Stores and fetches ratings given by users to different hotels.
- Interacts with both User and Hotel services.

---
