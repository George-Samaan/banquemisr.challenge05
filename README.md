# Movie Database

An Android application built using **Jetpack Compose** and **MVVM architecture** to fetch and display movie information using **The Movie Database (TMDb) API**.

## Features

- 🎬 **Splash Screen**: Displays an engaging animation while the app is loading.
- 🏠 **Home Screen**: Displays the latest movies (Now Playing, Popular, and Upcoming) in a tab layout.
- 📄 **Movie Details**: Displays detailed information about the selected movie, including genres, ratings, tagline, duration, language, release date and overview.
- 📶 **Network Connectivity Monitoring**: Uses a **BroadcastReceiver** to detect changes in network availability and shows an appropriate error screen when the network is unavailable.
- 🔍 **Pagination**: Efficiently loads movies with pagination, using the Paging library.
- 🎥 **Animated UI**: Uses animations, including Lottie for loading states and animated movie cards.
  
## Screenshots

| Splash Screen | Home Screen | Movie Details | No Network |
|---------------|-------------|---------------|------------|
| ![Screenshot_1729599577](https://github.com/user-attachments/assets/d797e7af-6959-4734-84c1-fa686c10828a) | ![Screenshot_1729599335](https://github.com/user-attachments/assets/677b40e1-b760-4dcb-8588-180d2e4768ea) | ![Screenshot_1729599415](https://github.com/user-attachments/assets/787403a8-8563-4dae-ab09-017c61d1c597) | ![Screenshot_1729599488](https://github.com/user-attachments/assets/536c6ab3-4f66-4de3-8c02-256a9a40db30) |

## Architecture

The app follows **MVVM (Model-View-ViewModel)** architecture for separation of concerns and to make the code modular, maintainable, and testable. Here's a breakdown of the components:

- **Model**: Handles the data layer, including API services and data models.
- **ViewModel**: Contains the business logic and prepares data for the UI.
- **View**: The Jetpack Compose UI that observes ViewModel states and renders the UI.

## Network Connectivity

The app uses a **BroadcastReceiver** to monitor the network status and handle connectivity changes:

- When the network is available, the app fetches data from the TMDb API.
- If the network is unavailable, a custom **No Network** screen is displayed, informing the user to reconnect and retry.
  
## Tech Stack

- **Kotlin**: The programming language used for the app.
- **Jetpack Compose**: Modern Android UI toolkit for building native UI.
- **Retrofit + OkHttp**: For network calls to the TMDb API.
- **Paging**: For efficient pagination of movie data.
- **Coroutines + Flow**: For asynchronous programming and managing streams of data.
- **Lottie**: For animations and loading states.

## Installation

1. Clone the repository:
   ```bash
   git clone https://github.com/George-Samaan/banquemisr.challenge05.git
