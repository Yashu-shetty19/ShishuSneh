# Shishu-Sneh – Baby’s First Year Guide

Shishu-Sneh is a healthcare Android application developed to help new mothers track their baby’s growth, vaccination schedules, feeding guidance, and developmental milestones during the first year.

The app is specially designed for rural mothers with a simple and friendly interface that works completely offline.

---

## Features

### 👶 Baby Profile
- Add baby name, date of birth, and gender
- Automatically calculates baby age

### 📈 Growth Tracker
- Track baby weight and height monthly
- View progress using a growth chart

### 💉 Vaccination Alerts
- Automatic reminders for upcoming vaccines
- Includes Polio, DPT, Hepatitis, and other important vaccines
- Shows vaccine due date and disease it prevents

### 📅 Immunization Calendar
- Displays complete vaccination schedule
- Easy-to-understand timeline view

### 🍼 Feeding Guide
- Daily feeding and nutrition tips for mother and baby

### ✅ Milestone Tracker
- Track developmental milestones like smiling, sitting, and head control

### 🔔 Notifications
- Vaccination reminders using AlarmManager
- Notifications continue after device reboot

---

## Technologies Used

- Kotlin
- XML
- Android Studio
- Room Database
- WorkManager
- MPAndroidChart
- MVVM Architecture

---

## App Design

The UI is designed with:
- Soft pastel colors
- Rounded cards and buttons
- Mother-friendly layout
- Simple navigation
- Clean healthcare theme

Color Palette:
- Soft Pink
- Light Blue
- Off White
- Light Green

---

## How to Run the Project

1. Open Android Studio
2. Click File → Open
3. Select the ShishuSneh project folder
4. Wait for Gradle Sync
5. Run the app on emulator or Android device

---

## Purpose of the Project

This project aims to:
- Improve child healthcare awareness
- Reduce missed vaccinations
- Help mothers monitor baby growth properly
- Support healthcare access in rural areas

---

## Project structure

ShishuSneh/                                                                                                                                                                  
├── build.gradle.kts                 (root)

├── settings.gradle.kts

├── gradle.properties

├── gradle/wrapper/gradle-wrapper.properties

└── app/

    ├── build.gradle.kts
    
    ├── proguard-rules.pro
    
    └── src/main/
    
        ├── AndroidManifest.xml
        
        ├── java/com/shishusneh/app/
        
        │   ├── ShishuSnehApp.kt           (Application — creates notif channel)
        
        │   ├── data/                      (Repository + Vaccine/Milestone catalogs)
        
        │   ├── db/                        (Room DB, entities, DAOs)
        
        │   ├── notification/              (NotificationHelper)
        
        │   ├── worker/                    (VaccinationReminderWorker, BootReceiver)
        
        │   ├── util/DateUtils.kt
        
        │   └── ui/
        
        │       ├── onboarding/            (OnboardingActivity + ViewModel)
        
        │       ├── dashboard/             (MainActivity + DashboardFragment)
        
        │       ├── growth/                (Fragment + ViewModel + Adapter)
        
        │       ├── vaccines/              (Fragment + ViewModel + Adapter)
        
        │       ├── feeding/               (Fragment + Adapter, swipeable cards)
        
        │       └── milestones/            (Fragment + ViewModel + Adapter)
        
        └── res/
        
            ├── layout/                    (activity & fragment & item XMLs)
            
            ├── menu/bottom_nav.xml
            
            ├── navigation/nav_graph.xml
            
            ├── drawable/                  (bg_card_, ic_)
            
            ├── values/                    (colors.xml, strings.xml, themes.xml)
            
            └── mipmap-anydpi-v26/         (launcher icon)

---


## App Screenshots

### Splash Screen
<img width="311" height="499" alt="splash screen" src="https://github.com/user-attachments/assets/8c4020d5-7f40-442a-9685-2c2589fb116b" />



---

## How to Install the App on Mobile

1. Connect your Android phone to the computer using USB.
2. Enable Developer Options and USB Debugging on your phone.
3. Open the project in Android Studio.
4. Click the ▶ Run button.
5. Select your mobile device.
6. The app will install and open automatically on your phone.

---
## Minimum Requirements

- minSdk 21
- targetSdk 34
- Android Studio Hedgehog or later

---
## Future Enhancements

- Multi-language support
- AI-based baby health suggestions
- Cloud backup support
- Doctor consultation feature
- Dark mode support

---

## Main Modules

- Baby Profile
- Growth Tracker
- Vaccination Alerts
- Immunization Calendar
- Feeding Guide
- Milestone Tracker

---

## App Highlights

- Simple and mother-friendly UI
- Works fully offline
- Automatic vaccination reminders
- Secure local data storage
- Soft pastel healthcare design

---

## Developed For

Healthcare support for new mothers and child wellness monitoring during the first year.
