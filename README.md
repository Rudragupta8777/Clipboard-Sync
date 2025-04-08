# 📋 Clipboard Sync (Android ↔ Windows)

This Android app allows seamless clipboard text sharing between your Android device and Windows PC using Firebase Realtime Database. You can view clipboard text copied on your PC and copy it to your Android clipboard. You can also send text from your Android phone and copy it to your PC.

> 🔁 Real-time updates | 🔒 Firebase Secure | 🎯 Minimal UI

---

## ✨ Features

- 🔄 **Real-time Sync** of clipboard text using Firebase.
- 📲 **Copy text** from Windows PC and view/copy on Android.
- 📤 **Send text** from Android to Windows and copy on PC.
- 🧭 **Scroll support** for long text messages.
- ✅ Uses ConstraintLayout for responsive UI.

---

## 🚀 Getting Started

### Prerequisites

- Android Studio
- Firebase Realtime Database setup
- A connected Android device or emulator

---

## 🔧 Firebase Setup & Integration

### Step 1: Firebase Project

1. Go to [Firebase Console](https://console.firebase.google.com/)
2. Create a new Firebase project (or use an existing one).
3. Click **"Add App"** → choose **Android** and register your package name (same as in your app, e.g. `com.example.clipboardsync`).
4. Download the generated `google-services.json` file.
5. In Android Studio:
   - Place the `google-services.json` file **inside** the `app/` directory (not in the root project folder).
   - Your structure should look like:
     ```
     📁 ClipboardSync
     ┣ 📁 app
     ┃ ┣ 📜 google-services.json ✅
     ┃ ┣ 📜 build.gradle
     ┗ 📜 build.gradle (Project level)
     ```


### Step 2: Enable Realtime Database

1. Go to the Firebase Console → your project.
2. From the left sidebar, click **"Build" > Realtime Database**.
3. Click **Create Database** and select the region.
4. Choose **Start in test mode** for easy development.

   It will automatically set rules like:
   ```json
   {
     "rules": {
       ".read": true,
       ".write": true
     }
   }
