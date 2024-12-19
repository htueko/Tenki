# Tenki Weather App

A sophisticated Android weather application built with modern Android development practices, emphasizing clean architecture, MVVM pattern, and robust data handling.

## Architecture & Design

The app follows Clean Architecture principles with MVVM pattern:
- Presentation Layer: MVVM with ViewModels and UI States
- Domain Layer: Use Cases and Business Logic
- Data Layer: Repository Pattern with Local and Remote Data Sources

## Key Technical Features

### Clean Architecture Implementation
- Clear separation of concerns
- Independent layers with defined boundaries
- Dependency injection for better testability
- Use cases for business logic encapsulation

### Local Storage
- Room Database for persistent storage
- Efficient caching of weather data
- Reliable city selection persistence
- Offline-first approach

### Network Layer
- Retrofit integration with WeatherAPI.com
- Coroutines for async operations
- Comprehensive error handling
- Clean API response mapping

### Weather Details
- Current weather conditions
- Temperature and "feels like" metrics
- Humidity levels
- UV index tracking
- Weather condition icons
- Extended forecasts

### Modern Android Stack
- Kotlin
- Coroutines for async operations
- Flow for reactive programming
- Hilt for dependency injection
- Room for local storage
- Retrofit for API communication
- ViewModel and LiveData
- Material Design components

## Setup

1. Clone the repository:
   git clone https://github.com/yourusername/tenki.git

2. API Configuration:
- Obtain API key from WeatherAPI.com
- Navigate to app/src/main/java/com/htueko/tenki/core/constant/RemoteApiConstant.kt
- Replace PLACE_YOUR_KEY_HERE with your API key
  Base URL: https://api.weatherapi.com/v1/

## Build & Testing

- Comprehensive unit tests for each layer
- Integration tests for data flow
- UI tests for critical user journeys
- Gradle-based build system
- Windows: gradlew.bat
- macOS/Linux: ./gradlew

## Code Quality

- Kotlin coding conventions
- Clean Architecture principles
- SOLID principles
- Dependency injection
- Comprehensive error handling
- Proper documentation

Licensed under Apache License, Version 2.0

Contributions welcome through pull requests.