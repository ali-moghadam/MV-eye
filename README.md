
# MV Eye  👁️

A lightweight, opinionated library for building Android apps using the Model-View-Intent (MVI) architecture.

<br>

## 🚀 Overview
**MV Eye** is a streamlined and efficient library designed to simplify the development of Android applications using the MVI architecture. It provides a robust framework for managing state, handling events, and maintaining a unidirectional data flow, ensuring a more predictable and maintainable codebase.

![Logo](/upload/mv-eye-banner.png)

<br>

## 🧠 What is MVI (Model-View-Intent)?

MVI (Model-View-Intent) is a modern architecture pattern for building Android applications with a unidirectional data flow. It emphasizes clear separation of concerns, making your codebase more maintainable, testable, and scalable.

### 💡 Why Choose MVI?
- **Reactive UI:** The UI automatically updates based on state changes, reducing the risk of inconsistencies.
- **Separation of Concerns:** Each component has a clear responsibility, leading to a modular and maintainable codebase.
- **Predictable State Management:** With a single source of truth, debugging and testing become significantly easier.

<br>

## ✨ Key Features
- **Unidirectional Data Flow:** Enforces a clear flow of data from the state to the UI.
- **Simplified State** Management: Centralizes UI state with StateFlow for reactive updates.
- **Event Handling:** Encapsulates user interactions and UI events with a robust event system.
- **Easy Integration:** Minimal setup required, allowing quick adoption in existing projects.

<br>

## 📦 Installation

Add the dependency to your build.gradle.kts file:
```
dependencies {
    implementation("com.github.ali-moghadam:MV-eye:latest-version")
}
```

Make sure to replace "latest-version" with the actual version from the releases page.

<br>

## 🛠️  Usage


Here’s a quick example of how to use MV-eye in your project:

1. Define a class which extends **UiState**

```
data class ProductUiState(
    val count: Int = 0,
) : UiState
```

2. Define a class which extends **Event**

```
sealed class ProductEvent : Event {
    object IncreaseCount : ProductEvent()
    object DecreaseCount : ProductEvent()
}
```

<br>

## 🏢 Used By

This project is used by the following companies:

- [Snapp](http://snapp.ir)

<br>

## 👨‍💻 Authors

- [@ali-moghadam](https://github.com/ali-moghadam/)

<br>

## 💬 Feedback

If you have any feedback, please reach out to us at ali.moghadam.developer@gmail.com

