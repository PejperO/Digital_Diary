# Digital_Diary
This repository contains the source code for the Digital Diary mobile application. The application allows users to create and store personalized entries in written form, along with the ability to add photos and voice recordings.

## Overview
The Digital Diary application is designed to offer a comprehensive and user-friendly platform for users to document their thoughts, experiences, and memories in a structured manner. This app is particularly useful for individuals who enjoy journaling and want to enhance their entries with multimedia elements like photos and voice recordings.

The core functionality includes creating entries with text, location tags (automatically retrieved from GPS), photos, and voice recordings. Users can also edit existing entries, add captions directly on images, and secure the app with a password or PIN. The data is stored locally using SQLite, ensuring privacy and accessibility without the need for an internet connection.

This project leverages the Model-View-ViewModel (MVVM) or Model-View-Intent (MVI) architectural pattern to ensure clean and maintainable code. Additionally, all literal values are stored in resource files to facilitate easy translation and localization.

## Features
- **Create Entries:** Users can create diary entries with text, location tags, photos, and voice recordings.
  - Location tags are automatically generated using the device's GPS and added to the note.
  - Photos can be captured and added to the entry.
  - Voice recordings can be made and attached to the entry.
  - Edit Entries: Existing entries can be edited to update text, replace photos, or modify voice recordings.
  - 
- **Save Data Locally:** All entries are stored locally on the device using SQLite, ensuring that user data remains private and accessible offline.

- **Add Captions to Photos:** Users can draw text directly on photos before saving them to an entry, adding a personalized touch to their images.

- **Browse Entries:** Users can browse through all their diary entries in the app, making it easy to revisit past memories.

- **Secure Access:** The app can be protected with a password or PIN, ensuring that only authorized users can access the diary entries.

- **Architectural Patterns:** The project is built using the MVVM or MVI pattern to promote a clean separation of concerns and improve code maintainability.

- **Localization Support:** All literal values are stored in resource files, making it easy to translate the app into different languages.

## Installation
1. Clone the repository:  ```git clone https://github.com/PejperO/Digital_Diary.git```
2. Open the project in Android Studio.
3. Build and run the project on an Android device or emulator.

## Usage
1. Launch the application on your Android device.
2. Secure the app by setting up a password or PIN on the first launch.
3. Use the main screen to view and manage your diary entries.
4. Click on the add button to create a new entry with text, photo, and voice recording.
5. Select an existing entry to view or edit its details.
6. Draw captions on photos before adding them to your entries.
7. Browse through your entries to revisit past memories.

## What I Learned
- **Android Development:** Enhanced my skills in developing Android applications using native Android SDK tools.
- **Kotlin Programming:** Improved my proficiency in Kotlin, including best practices for clean and readable code.
- **User Interface Design:** Learned how to design and implement user interfaces using Android Views and Jetpack Compose.
- **Data Management:** Gained knowledge in implementing local data storage solutions using SQLite.
- **Multimedia Handling:** Acquired experience in integrating multimedia elements such as photos and voice recordings into Android applications.
- **Security Implementation:** Learned how to implement basic security measures, such as password/PIN protection, to safeguard user data.
- **Project Management:** Developed better project management skills, including planning, implementing, and testing application features.
- **Architectural Patterns:** Gained practical experience in using architectural patterns like MVVM or MVI to build maintainable and scalable applications.
- **Localization:** Understood the importance of localization and how to implement it in an Android project.
