# 🚚 MediSwift Partner App

**MediSwift Partner** is a dedicated Android application for delivery agents to manage, track, and complete medicine deliveries efficiently. It streamlines the delivery process with real-time order updates, FCM-based notifications, GPS tracking, and quick support—ensuring timely service and a seamless delivery experience.

---

## 📱 Features

- 🔐 **Authentication** – Sign up and log in using Firebase Authentication.
- 📦 **New Deliveries** – View and accept available delivery requests in real-time.
- 🧭 **Track Deliveries** – Monitor the current status and location of active deliveries.
- 📋 **All Deliveries** – View a complete list of current and past delivery orders.
- 🆘 **Support Access** – Connect with support for delivery issues or queries.
- 🔔 **Push Notifications (FCM)** – Get notified of new orders or delivery updates via Firebase Cloud Messaging.
- 🧾 **Order Details Page** – View complete order information and status history.
- 📡 **Realtime Firebase Integration** – Sync delivery data instantly using Firebase Realtime Database.
- 📂 **MVVM Architecture** – Clean, maintainable, and scalable codebase.
- ✨ **Jetpack Compose UI** – Built entirely using modern declarative UI toolkit.

---

## 🧱 Tech Stack

| Layer        | Technology                  |
|--------------|-----------------------------|
| UI           | Jetpack Compose             |
| Architecture | MVVM                        |
| Database     | Firebase Realtime Database  |
| Auth         | Firebase Authentication     |
| Push         | Firebase Cloud Messaging    |
| Language     | Kotlin                      |
| IDE          | Android Studio              |

---

## 📸 Screenshots

| Home Page | Orders Page | Notifications |
|-----------|-------------|----------------|
| ![Home](https://github.com/user-attachments/assets/1cd2b9dc-a18b-495d-8461-1906bff4055e) | ![Orders](https://github.com/user-attachments/assets/e0615861-5260-45a4-8db5-2abfe177983e) | ![Details](https://github.com/user-attachments/assets/5c0e864f-6366-49c6-88c9-b42079158e6f) |

---

## 🔧 Installation

1. Clone the repository:
   ```bash
   git clone https://github.com/dev-sec-it/mediswift-partner.git
   ```

2. Open the project in Android Studio.
3. Connect your Firebase project:
     - Add google-services.json to the app/ directory.
     - Enable Authentication, Realtime Database, and FCM from the Firebase Console.

4. Add the following to your build.gradle (app-level):
   ```bash
   implementation(platform("com.google.firebase:firebase-bom:33.16.0"))
   implementation("com.google.firebase:firebase-auth")
   val nav_version = "2.9.0"
   implementation("androidx.navigation:navigation-compose:$nav_version")
   val lifecycle_version = "2.9.1"
   implementation("androidx.lifecycle:lifecycle-runtime-ktx:$lifecycle_version")
   implementation (platform("com.google.firebase:firebase-bom:32.7.0"))
   implementation ("com.google.firebase:firebase-database-ktx")
   implementation ("androidx.navigation:navigation-compose:2.7.7")
   ```
5. Initialize Firebase Messaging in your app
6. Run the app

### 📂 Project Structure
```bash
com.example.meddeliveryapp
├── model         # Data classes (e.g., OrderData.kt)
├── view          # Jetpack Compose screens
│   └── pages     # HomePage, Orders, Tracking, etc.
├── viewmodel     # ViewModels using StateFlow/LiveData
├── repository    # Firebase & business logic
├── fcm           # FCM-related message services
└── navigation    # Navigation graph
````
