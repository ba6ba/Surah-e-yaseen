<h1 align="center">Surah-e-Yaseen</h1>

<p align="center">
  <a href="https://opensource.org/licenses/Apache-2.0"><img alt="License" src="https://img.shields.io/badge/License-Apache%202.0-blue.svg"/></a>
  <a href="https://android-arsenal.com/api?level=23"><img alt="API" src="https://img.shields.io/badge/API-23%2B-brightgreen.svg?style=flat"/></a>
</p>

<p align="center">  
Surah-e-yaseen is a small application based on modern Android application tech-stacks and MVVM architecture.<br>This project is using Koin as dependency Injection,
coroutines and repostiory pattern for data managment from Network and local database. This application has been using Room for local data managment. This app also has
media player which plays Ayaah of surah even app gets backgrounded. 
</p>
</br>

<p align="center">
<img src="https://user-images.githubusercontent.com/26377145/98157078-9ecfae00-1efa-11eb-83f1-7dca3e4bb342.png"/>
</p>

## Download
Go to the [Releases](https://github.com/ba6ba/Surah-e-yaseen/releases) to download the latest APK.

## Tech stack & Open-source libraries
- Minimum SDK level 23
- [Kotlin](https://kotlinlang.org/) based, [Coroutines](https://github.com/Kotlin/kotlinx.coroutines) for asynchronous.
- [Koin](https://github.com/InsertKoinIO/koin) for dependency injection.
- JetPack
  - LiveData - notify domain layer data to views.
  - Lifecycle - dispose of observing data when lifecycle state changes.
  - ViewModel - UI related data holder, lifecycle aware.
  - Room Persistence - construct a database using the abstract layer.
- Architecture
  - MVVM Architecture (View - ViewModel - Model)
  - Repository pattern
- [Retrofit2 & OkHttp3](https://github.com/square/retrofit) - construct the REST APIs and paging network data.
- [Moshi](https://github.com/square/moshi/) - A modern JSON library for Kotlin and Java.
- [Timber](https://github.com/JakeWharton/timber) - logging.
- [Material-Components](https://github.com/material-components/material-components-android) - Material design components like ripple animation, cardView.
- Custom Views


## Gradle Build approach
Surah-e-yaseen gradle build completely based on Kotlin Gradle DSL.

## Architecture
Surah-e-yaseen is based on MVVM architecture and a repository pattern.

![architecture](https://user-images.githubusercontent.com/26377145/98156413-96c33e80-1ef9-11eb-8875-dd89e1542eef.png)

## Quran API (open source)

Surah-e-yaseen using the [Quran API](https://quran.api-docs.io/) for constructing RESTful API.<br>
Quran API provides a RESTful API interface to highly detailed objects built from thousands of lines of data related to Quran.

## Find this repository useful? :heart:

## Want to find design of this on Dribbble? 
Here it is : [Dribbble Link](https://dribbble.com/shots/14514256-Sura-ah-Yaseen) :thumbsup:

## What to do next?
Make this app configurable from remote and based on surah number whole app can be dynamically changed to that specific Surah.

# License
```xml
Designed and developed by 2020 ba6ba (Abdul Basit Malik)

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

   http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```
