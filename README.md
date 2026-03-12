# 💰 Expense Tracker App

An Android-based Expense Tracker app built using Kotlin and Firebase for tracking income, expenses, and visualizing data with charts.

This app helps users manage their finances by logging daily expenses and income, categorizing them, and analyzing spending habits through interactive graphs. It supports user login, real-time database storage, and a clean UI built with Material Components.

---

## ✨ Features

- 📥 Add, edit, and delete income & expense entries  
- 📊 Visualize spending via pie & bar charts (using MPAndroidChart)  
- 🔐 Firebase Authentication (Google & Email login)  
- ☁️ Firebase Realtime Database for data storage  
- ⚙️ Settings tab with logout & UI preference options  
- 📱 Clean and user-friendly Android UI  
- 📦 Includes a release-ready APK (ExpenseTrackerApp.apk)

---

## 🛠️ Technologies Used

- Kotlin  
- Android Studio  
- Firebase Realtime Database  
- Firebase Authentication  
- MPAndroidChart library  
- Material Design Components  
- XML (layouts & UI)

---

## ⚙️ Requirements

- Android Studio (Electric Eel or higher recommended)  
- Gradle version 7.3.3 or compatible  
- Android device or emulator (API 24 or higher)  
- Google Firebase account (for testing with your own DB)

---

## 📦 Setup Instructions

### 1. Clone the repository
```bash
git clone https://github.com/aaminansariofficial/Expense-Tracker-App.git
```

### 2. Open in Android Studio
- File → Open → Select the project folder

### 3. Sync Gradle & Build the App
- Wait for Gradle sync to complete  
- Clean Project → Rebuild Project

### 4. Connect Firebase (Optional if testing with your own)
- Tools → Firebase → Connect to Firebase  
- Replace google-services.json with your own (if needed)

---

## ▶️ Run the App

- Select an emulator or connected Android device  
- Click ▶️ Run (or Shift + F10) in Android Studio

✅ You can also download and install the APK directly:

📦 [Download Expense Tracker App APK](./Expense%20Tracker%20App.apk)

---

## 📊 Charts & Analytics

This app uses the MPAndroidChart library to display:
- 🟠 Pie charts for category-wise spending
- 🔵 Bar charts for date-wise income & expenses

---

## 👨‍💻 Author

- Aamin Ansari  
  🔗 GitHub: https://github.com/aaminansariofficial  
  🔗 LinkedIn: https://linkedin.com/in/aaminansari  


---

## 📄 License

This project is licensed under the MIT License.  
You are free to use, modify, and share it with proper credit.

---

## 🙏 Acknowledgments

This project was built as a **learning exercise** by studying and adapting an open-source Android expense tracker application. The original codebase served as a foundation, which I explored, modified, and extended to deepen my understanding of Kotlin, Firebase, and Android development.

**Key modifications and contributions:**
- Integrated Firebase Authentication (Google & Email login) on top of the original structure
- Customized the UI to use Material Design Components
- Extended chart analytics using MPAndroidChart
- Refactored and restructured code with assistance from AI tools (ChatGPT, GitHub Copilot)
- Added release APK and setup documentation

**References & tools used:**
- [Firebase Documentation](https://firebase.google.com/docs)
- [MPAndroidChart by PhilJay](https://github.com/PhilJay/MPAndroidChart)
- [Android Developers Guide](https://developer.android.com/docs)
- AI coding assistants (ChatGPT, GitHub Copilot) for code understanding and refactoring
