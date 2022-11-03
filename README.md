# task-app-api

## Endpoints
- /auth
    - POST
        - HTTP Request
            - url: "http://localhost:8080/task-app/auth"
            - header: Content-type: JSON
            - Body
                - { "username": "username", "password": "password"}
        - HTTP Response
            - status code
                - 200: success
                - 400: unable to login
            - body
                - Principal - UserDTO
            - Header:
                - set-cookie: JSessionID

- /users
    - GET
        - /users 
            - HTTP Request
                - url: "http://localhost:8080/task-app/users"
                - headers:
                    - cookie: JSessionID 
                        - has to be ADMIN
            - HTTP Response
                - status code
                    - 200: success
                    - 401: unauthorized
            - body
                - UserDTO[]
            - Header:
                - content-type: JSON