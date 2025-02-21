# Spring Boot Marketing Campaign and User Segment Management

## Prerequisites

Before starting with the project, ensure you have the following tools installed:

- **IntelliJ IDEA** or any similar IDE (Eclipse, Visual Studio Code, etc.)
- **Java Development Kit (JDK)** version 17 or higher
- **Maven** for project build and dependency management
- **PostgreSQL** database



If you don't have a local PostgreSQL database, you can run one using Docker with the following command:

```bash
docker run --name marketing_campaign_db -e POSTGRES_USER=postgres -e POSTGRES_PASSWORD=password -e POSTGRES_DB=marketing_campaign -p 5432:5432 -d postgres
```
 
# Final thoughts
- I could have integrated a custom response entity with more personalized messages and more validation checks, used swagger for api documentation,
used the @Slf4j annotation from lombok in order to log all the methods, or created html templates for email contents. As for mailchimp, based on their documentations
and some guidelines i read on scientific papers i could have also implemented interfaces and builder methods that they exposed, but stuck with a more simplistic approach 
by creating a campaing for each user to be subscribed and for that campaign i notified all the used with a dummy mail template, but i tried to keep the project as simple as possible.
Also used Liquibase for a faster approach to table creation with some basic data inside. Hoping to be to your liking :)

## Project Overview
This project involves creating a RESTful API using Spring Boot and PostgreSQL for managing marketing campaigns and user segments. It also includes integration with Mailchimp for sending emails to specific user segments.

## Entities
### User
- **ID**
- **Name**
- **Email**

### UserSegment
- **ID**
- **Name**
- **Description**

### UserSegmentAssociation
- **User** (many-to-one)
- **UserSegment** (many-to-one)

## Features
### CRUD Operations
- Create, Read, Update, Delete users and segments.

### Segment Users
- Endpoint to retrieve all users within a specific segment.

### Mailchimp Integration
- Endpoint to send emails to a segment of users using Mailchimp API.

## Project Structure
### Configuration
- Set up the `application.properties` to connect to the PostgreSQL database and configure Mailchimp API properties.

### Entities
- Define `User`, `UserSegment`, and `UserSegmentAssociation` entities.

### Repositories
- Create repositories for interacting with the database.

### Services
- Implement business logic in service classes.

### Controllers
- Define REST controllers for handling HTTP requests.

### Mailchimp Integration
- Integrate Mailchimp API to send emails to specific user segments.

