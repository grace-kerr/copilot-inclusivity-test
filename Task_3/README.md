# 🧩 Task 3 – Code Refactoring: Process Transactions

## 📍Objective
You are given a Java method that processes a file containing bank transactions and prints out a running balance.

The current implementation handles too many responsibilities in one method. Your goal is to **refactor** it into a cleaner, more modular version.

---

## ✅ What You Need to Do

### Step 1: Refactor the Code
Open `TransactionProcessor.java` and break it down into smaller, more focused methods.

---

### Step 2: Test Your Code
Use the provided `TransactionProcessorTest.java` file to test your changes.

This file:
- Creates a temporary CSV file with transactions
- Calls your `processTransactions()` method

You can run the test as-is, or extend it with assertions if your refactored method returns data.

---

## 💡 Optional Challenge
- Add support for additional transaction types like `"transfer"` or `"interest"` using design patterns like **strategy** or **polymorphism**.
- Make the code more testable by returning results instead of printing only.

---

## 📂 Files Provided
- `TransactionProcessor.java`
- `TransactionProcessorTest.java`