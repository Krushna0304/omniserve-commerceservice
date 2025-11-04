# OmniServe Commerce Service

This repository contains the core microservices for the OmniServe commerce platform, including the `commerceservice` and the shared `commondblib` library.

## Project Structure

```
commerceservice/   # Main commerce service (Spring Boot)
commondblib/       # Shared library for common DB entities and DTOs
```

## Features

- RESTful APIs for catalog and order management
- Modular architecture with shared data models
- Event-driven order and shipment processing
- Spring Boot, JPA, and Maven-based build

## Getting Started

### Prerequisites

- Java 17 or higher
- Maven 3.6 or higher
- (Optional) Docker for containerization

### Build and Run

1. **Clone the repository:**
   ```sh
   git clone https://github.com/<your-username>/omniserve-commerceservice.git
   cd omniserve-commerceservice
   ```

2. **Build both modules:**
   ```sh
   mvn clean install
   ```

3. **Run the commerce service:**
   ```sh
   cd commerceservice
   mvn spring-boot:run
   ```

### Configuration

- Application configuration is in `src/main/resources/application.yml` for each module.
- Update database and service endpoints as needed.

## API Endpoints

- Catalog: `/api/catalog`
- Orders: `/api/orders`
- (See controller classes for full API details)

## Contributing

1. Fork the repo and create your branch (`git checkout -b feature/your-feature`)
2. Commit your changes (`git commit -am 'Add new feature'`)
3. Push to the branch (`git push origin feature/your-feature`)
4. Open a Pull Request

## License

This project is licensed under the MIT License.
