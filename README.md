# Flickr Finder:


With this little and minimal application, you can fetch 25 recent photo, search 25 flickr photo, view photo detail and bookmark it.


## Project Structure ⚙️

This Application is based on **CLEAN** architecture combined with **MVVM** architecture and is written in **Kotlin**
Use IntentFilter for navigate between activities to independent activities from each other.
every module can be a single activity.


The project contains four main module:



**Application**

The application contains these packages:

* `base/` : contains application class
* `presentation/` : contains all UI related classes (ViewModel, Activity and Fragment)



**CoreModule**

The core module contains these packages:

* `data/` : contain all base data models
* `di/` : contains base dependency injection class
* `domain/` : contains base domain model and base UseCase classes
* `extensions/` : contains extension functions
* `presentation/` : contains all base classes include custom base android component
* `tools/` : contains Constants, Recourse, and helper classes
* `view/` : contains all custom views


**pictureModule**

The picture module contains these packages:

* `data/` : contains all remote and local data and repository classes
* `di/` : contains dependency injection class
* `domain/` : contains domain models and usecase classes
* `presentation/` : all UI related classes (ViewModel, Activity and Fragment)




**Tests**
* `unit tests` automated tests to check api calls
* `Functional tests` automated tests to check UI components (used Espresso)


## Screenshots

Recent Photo                |  Search History
:-------------------------:|:-------------------------:
<img src="https://github.com/alireza-khosroabadi/FlickrFinderApp/blob/master/screenshots/1.jpg" width="280">  | <img src="https://github.com/alireza-khosroabadi/FlickrFinderApp/blob/master/screenshots/2.jpg" width="280">

Search Photo                |  Favorite Photo
:-------------------------:|:-------------------------:
<img src="https://github.com/alireza-khosroabadi/FlickrFinderApp/blob/master/screenshots/3.jpg" width="280">  | <img src="https://github.com/alireza-khosroabadi/FlickrFinderApp/blob/master/screenshots/4.jpg" width="280">



## Libraries and tools 🛠

- Retrofit
- Kotlin coroutines
- coroutines flow
- room
- Dagger Hilt
- View Binding
- Glide
- Espresso



## Appendix

Contact address

* Email  : khosroabadi.alireza@gmail.com
