# Rick and Morty App

Android app that lists Rick and Morty characters and allows users to view character details using the public API: https://rickandmortyapi.com/

## Architecture
This Android app follows a simplified clean MVVM architecture with the following layers:

- UI with Jetpack Compose
- ViewModel for state management
- Repository as data abstraction layer
- Remote data source (API)

Data flow:

UI -> ViewModel -> Repository -> API

## Tech Stack
- Kotlin
- Jetpack Compose
- Material 3
- MVVM
- Repository pattern
- Coroutines / Flow
- Retrofit
- Paging 3

## Features
- Characters list
- Character detail screen
- State handling (loading / error)
- Pagination

## API
Rick and Morty API  
https://rickandmortyapi.com/

## Requirements
- Android 10 and above

## Future Improvements
- Search characters by name
- Filter characters (status, species, etc.)
- ViewModel unit testing
- Offline caching (Room)
- Error handling improvements
