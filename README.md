# hotelAPI

## Table of contents

- [Description](#description)
- [Installation](#installation)
    - [Prerequisites](#prerequisites)
    - [Steps](#steps)
- [Endpoints](#endpoints)
    - [Rooms](#rooms)
    - [Guests](#guests)
- [Technologies](#technologies)

## Description

This is the API portion of the hotel management system.
It is a RESTful API that allows hotel staff to:

- Create, Read, Update, and Delete (CRUD) hotel rooms
- Create, Read, Update, and Delete (CRUD) hotel guests

## Installation

### Prerequisites

- Java 8 or higher

### Steps

1. Clone the repository
2. Open the project in your IDE of choice
3. Run the [HotelApiApplication class](src/main/java/com/machapipo/hotelAPI/HotelApiApplication.java)

## Endpoints

All endpoints are prefixed with `/hotel/api`.

### Rooms

- `GET /rooms` - Returns a list of all rooms
- `GET /rooms/{id}` - Returns the room with the given id
- `POST /rooms` - Creates a new room
- `PUT /rooms/{id}` - Updates the room with the given id
- `DELETE /rooms/{id}` - Deletes the room with the given id

(rooms or room, both do the same thing)

### Guests

- `GET /guests` - Returns a list of all guests
- `GET /guests/{id}` - Returns the guest with the given id
- `POST /guests` - Creates a new guest
- `PUT /guests/{id}` - Updates the guest with the given id
- `DELETE /guests/{id}` - Deletes the guest with the given id

(guests or guest, both do the same thing)

## Technologies

- Java 8
- Maven
- Spring Boot
- Spring Web
- Spring Data JPA
- MySQL

## Author

- [machadinhos](https://github.com/machadinhos)