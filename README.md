# browser-history-manager
# 🌐 Browser History Manager

## 📌 Description

The **Browser History Manager** is a console-based Java application that simulates the basic functionality of a web browser's history management system.

It allows users to visit websites, navigate between previously visited and recently opened pages using Back and Forward operations, view their complete browsing history, and track the number of times each website has been visited.

The application uses Java Collection Framework data structures such as LinkedList, Stack, and HashMap to manage browsing history efficiently. It also implements file handling to store browsing history permanently and restore it when the application is started again.

## ✨ Features

- **Visit Page:** Allows users to enter and visit a website after validating its URL.
- **Back Navigation:** Moves to the previously visited website using a Stack.
- **Forward Navigation:** Moves to the next website using a separate Stack.
- **Show Full History:** Displays all visited websites in the order they were visited.
- **Visit Count:** Tracks and displays the number of visits for each website using HashMap.
- **URL Validation:** Prevents empty or invalid URLs from being added to browsing history.
- **File Handling:** Saves browsing history in a text file and loads previously stored history when the application starts.
- **Forward History Management:** Clears forward navigation history whenever a new website is visited.

## 🛠️ Data Structures Used

| Data Structure | Purpose |
|---|---|
| LinkedList | Stores all visited websites in browsing order. |
| Stack (Back Stack) | Maintains previously visited pages for backward navigation. |
| Stack (Forward Stack) | Maintains pages available for forward navigation. |
| HashMap | Stores website URLs and their corresponding visit counts. |

### Working of Data Structures

**1. LinkedList**

Stores all visited websites and maintains their insertion order. It is used to display the complete browsing history.

**2. Stack**

Two stacks are implemented to manage browser navigation.

- Back Stack stores previously visited pages.
- Forward Stack stores pages that can be accessed using the Forward option.

**3. HashMap**

Stores website URLs as keys and their visit counts as values.

Example:

```text
google.com -> 2 visits
youtube.com -> 1 visit
github.com -> 3 visits
```

## ⚙️ Implementation

The application is implemented using a single Java class named `BrowserHistoryManager`.

The program is divided into different methods, each responsible for a specific operation.

| Method | Functionality |
|---|---|
| `main()` | Displays the menu and handles user input. |
| `isValidUrl()` | Validates the entered website URL. |
| `visitPage()` | Visits a new website and updates browsing history. |
| `goBack()` | Navigates to the previously visited website. |
| `goForward()` | Navigates to the next available website. |
| `showHistory()` | Displays all visited websites. |
| `showVisitCounts()` | Displays the visit count of each website. |
| `loadHistoryFromFile()` | Loads previously saved browsing history. |
| `saveHistoryToFile()` | Saves browsing history into a text file. |

## 📂 Project Structure

```text
Browser-History-Manager/
│
├── BrowserHistoryManager.java
├── history.txt
└── README.md
```

**Note:** The `history.txt` file is automatically created when browsing history is saved for the first time.

## ▶️ How to Run

### Prerequisites

- Java Development Kit (JDK 8 or above).
- A Java-compatible IDE or terminal.

### Step 1: Clone the Repository

```bash
git clone <your-repository-link>
```

### Step 2: Open the Project Directory

```bash
cd Browser-History-Manager
```

### Step 3: Compile the Program

```bash
javac BrowserHistoryManager.java
```

### Step 4: Run the Application

```bash
java BrowserHistoryManager
```

## 🖥️ Application Menu

```text
================================
      BROWSER HISTORY MANAGER
================================
1. Visit Page
2. Back
3. Forward
4. Show Full History
5. Show Visit Count
6. Save & Exit
--------------------------------
Enter your choice:
```

## 💾 File Handling

The application uses Java File Handling to maintain browsing history even after the program is closed.

- `FileReader` and `BufferedReader` are used to read saved history.
- `FileWriter` and `BufferedWriter` are used to save browsing history.
- Previously saved websites are loaded automatically when the application starts.
- Browsing history is saved when the user selects the Save & Exit option.

All browsing history is stored in a file named `history.txt`.

## 📚 Java Concepts Applied

- Object-Oriented Programming
- Java Collection Framework
- LinkedList
- Stack
- HashMap
- Methods and Encapsulation
- Conditional Statements
- Loops and Switch Statements
- Exception Handling
- File Handling
- Input Validation

## 🚀 Future Enhancements

- Implement advanced URL validation.
- Add a search option for browsing history.
- Allow users to delete individual history entries.
- Display the most frequently visited websites.
- Save and restore complete navigation states.
- Develop a graphical user interface (GUI).

## 📝 Conclusion

The Browser History Manager demonstrates how fundamental Java data structures can be applied to solve a real-world problem.

By combining LinkedList, Stack, and HashMap, the application provides an organized way to manage browsing history, navigate between websites, and maintain visit statistics.

The implementation of file handling further improves the application by allowing browsing history to persist between program executions.

This project provides practical experience with Java programming, data structures, collection handling, and file operations.
