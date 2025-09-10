# Walmart Take Home Assessment - Country Information App

## Description

This Android application displays a list of countries and their relevant information, such as their capital, region, and country code. It serves as a demonstration of modern Android development practices, including fetching data from a remote API, displaying it in a list, and handling loading and error states.

## Features

*   Displays a list of countries.
*   Shows details for each country:
    *   Name
    *   Region
    *   Country Code
    *   Capital
*   Handles loading states while fetching data.
*   Displays error messages if data fetching fails.
*   Uses RecyclerView with DiffUtil for efficient list updates.

## Tech Stack & Architecture

*   **Language:** Kotlin
*   **Architecture:** MVVM (Model-View-ViewModel)
    *   **UI Layer:** Activities/Fragments, ViewModel
    *   **Data Layer:** Repositories, Retrofit for networking, GSON for JSON parsing.
*   **Asynchronous Programming:** Kotlin Coroutines & Flow
*   **Dependency Injection  manual DI via factories.
*   **Networking:**
    *   [Retrofit](https://square.github.io/retrofit/): For making HTTP requests to the API.
*   **UI:**
    *   XML Layouts with ViewBinding.
    *   RecyclerView for displaying lists efficiently.
      
*   **Jetpack Components:**
    *   ViewModel: To store and manage UI-related data in a lifecycle-conscious way.
    *   StateFlow: For observing data changes.
    *   Lifecycle Components: To manage UI component lifecycles.
*   **Testing (if you added any):**
    *   JUnit for unit tests.

## API Used

The application fetches country data from the following public API:
`https://gist.githubusercontent.com/peymano-wmt/32dcb892b06648910ddd40406e37fdab/raw/db25946fd77c5873b0303b858e861ce724e0dcd0/countries.json`

## Setup and Installation

1.  **Clone the repository:**
