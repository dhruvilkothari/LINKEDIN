# LinkedIn-like Application with Microservices Architecture

A scalable, distributed social networking platform built with Spring Boot microservices that provides core LinkedIn-like functionality including user management, posts, connections, and notifications.

This application implements a modern microservices architecture using Spring Cloud for service discovery, API Gateway for request routing, Kafka for event-driven communication, and multiple databases (PostgreSQL and Neo4j) for different data storage needs. The system provides robust user authentication, post management, connection handling, and real-time notifications while maintaining loose coupling between services.

## Repository Structure
```
.
├── api-gateway/                 # API Gateway service for routing and authentication
├── connections-service/         # Manages user connections using Neo4j
├── discovery-server/           # Eureka server for service discovery
├── docs/                      # Documentation and infrastructure diagrams
├── k8s/                       # Kubernetes deployment configurations
├── notification_service/      # Handles system notifications
├── post-service/             # Manages user posts and interactions
├── user-service/             # Handles user authentication and profiles
└── docker-compose.yml        # Docker composition for local deployment
```

## Usage Instructions
### Prerequisites
- JDK 21
- Docker and Docker Compose
- Maven 3.9.x
- Kubernetes (optional, for k8s deployment)

### Installation

1. Clone the repository:
```bash
git clone <repository-url>
cd linkedin-clone
```

2. Build all services:
```bash
# Build each service
cd api-gateway && ./mvnw clean package
cd ../connections-service && ./mvnw clean package
cd ../discovery-server && ./mvnw clean package
cd ../notification_service && ./mvnw clean package
cd ../post-service && ./mvnw clean package
cd ../user-service && ./mvnw clean package
```

3. Start the application using Docker Compose:
```bash
docker-compose up -d
```

### Quick Start
1. Access the application at http://localhost:8080

2. Create a new user account:
```bash
curl -X POST http://localhost:8080/api/v1/users/signup \
  -H "Content-Type: application/json" \
  -d '{"name":"John Doe","email":"john@example.com","password":"password123"}'
```

3. Login to get an authentication token:
```bash
curl -X POST http://localhost:8080/api/v1/users/login \
  -H "Content-Type: application/json" \
  -d '{"email":"john@example.com","password":"password123"}'
```

### More Detailed Examples

1. Create a post:
```bash
curl -X POST http://localhost:8080/api/v1/posts \
  -H "Authorization: Bearer <your-token>" \
  -H "Content-Type: application/json" \
  -d '{"content":"Hello LinkedIn!"}'
```

2. Send a connection request:
```bash
curl -X POST http://localhost:8080/api/v1/connections/request/{userId} \
  -H "Authorization: Bearer <your-token>"
```

### Troubleshooting

1. Service Discovery Issues
- Check if Eureka server is running: http://localhost:8761
- Verify service registration in Eureka dashboard
- Check application.properties for correct Eureka client configuration

2. Database Connection Issues
- Verify database containers are running:
```bash
docker ps | grep db
```
- Check database logs:
```bash
docker logs <container-name>
```

3. Kafka Issues
- Verify Kafka broker is running:
```bash
docker logs kafka
```
- Check Kafka UI at http://localhost:8090

## Data Flow
The application follows an event-driven architecture where services communicate through both REST APIs and Kafka events.

```ascii
User Request → API Gateway → Service → Database
     ↑          |
     |          ↓
Notification ← Kafka Events
```

Key interactions:
- API Gateway authenticates requests and routes to appropriate services
- Services publish events to Kafka topics for asynchronous processing
- Notification service consumes events and sends notifications
- Neo4j manages social connections graph
- PostgreSQL stores user data, posts, and notifications

## Infrastructure

![Infrastructure diagram](./docs/infra.svg)

### Databases
- PostgreSQL Instances:
  - notification-db: Stores notification data
  - posts-db: Stores user posts and interactions
  - user-db: Stores user account information
- Neo4j Instance:
  - connections-db: Stores user connection graph

### Message Broker
- Kafka: Handles event-driven communication between services
  - Topics: post-created-topic, post-liked-topic, send-connection-request-topic

### Service Discovery
- Eureka Server: Manages service registry and discovery

### API Gateway
- Spring Cloud Gateway: Routes requests and handles authentication

### Kubernetes Resources (k8s/)
- Deployments for each service
- StatefulSets for databases
- Services for network communication
- ConfigMaps and Secrets for configuration

## Deployment

### Prerequisites
- Kubernetes cluster
- kubectl configured
- Docker registry access

### Deployment Steps
1. Deploy infrastructure:
```bash
kubectl apply -f k8s/connections-db.yml
kubectl apply -f k8s/notification-db.yml
kubectl apply -f k8s/posts-db.yml
kubectl apply -f k8s/user-db.yml
```

2. Deploy services:
```bash
kubectl apply -f k8s/posts-service.yml
# Apply remaining service configurations
```

3. Verify deployment:
```bash
kubectl get pods
kubectl get services
```