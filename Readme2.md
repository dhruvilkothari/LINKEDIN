# LinkedIn Clone API Documentation

## Authentication APIs (User Service - Port 9020)
- POST `/api/v1/users/signup` - Register new user
    - Request Body:
      ```json
      {
          "name": "string",
          "email": "string",
          "password": "string"
      }
      ```
- POST `/api/v1/users/login` - User login
    - Request Body:
      ```json
      {
          "email": "string",
          "password": "string"
      }
      ```

## Post Management APIs (Post Service - Port 9010)
- POST `/api/v1/posts` - Create new post
    - Requires: Authorization Bearer Token
    - Request Body:
      ```json
      {
          "content": "string"
      }
      ```
- GET `/api/v1/posts/user/{userId}` - Get posts by user
    - Requires: Authorization Bearer Token
- POST `/api/v1/posts/likes/{postId}` - Like a post
    - Requires: Authorization Bearer Token

## Connection Management APIs (Connections Service - Port 9030)
Based on the Neo4j database mention, likely endpoints include:
- POST `/api/v1/connections/connect` - Send connection request
- PUT `/api/v1/connections/accept` - Accept connection request
- GET `/api/v1/connections/{userId}` - Get user connections
- GET `/api/v1/connections/suggestions` - Get connection suggestions

## Notification Service APIs (Port not specified)
Likely endpoints include:
- GET `/api/v1/notifications` - Get user notifications
- PUT `/api/v1/notifications/read` - Mark notifications as read

## API Gateway Routes (Port 8080)
All requests are routed through the API Gateway:
- User Service: `/api/v1/users/**`
- Posts Service: `/api/v1/posts/**`
- Connections Service: `/api/v1/connections/**`

## Authentication
- All protected endpoints require JWT token in Authorization header
- Format: `Authorization: Bearer <your-jwt-token>`

## Error Responses
Standard error response format:
```json
{
    "status": "error",
    "message": "Error description",
    "code": "ERROR_CODE"
}
