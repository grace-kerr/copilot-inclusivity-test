#!/bin/bash

# Test script to validate Task 2 setup
# Run this from the Task_2 directory

echo "🐛 Testing Task 2 Setup - Debugging Challenge"
echo "=============================================="

echo "📁 Checking directory structure..."
if [ -d "src/main/java" ] && [ -d "src/test/java" ]; then
    echo "✅ Directory structure is correct"
else
    echo "❌ Directory structure is missing"
    exit 1
fi

echo "📄 Checking required files..."
if [ -f "pom.xml" ] && [ -f "src/main/java/UserDataReader.java" ] && [ -f "src/test/java/UserDataReaderTest.java" ] && [ -f "userdata.json" ]; then
    echo "✅ All required files are present"
else
    echo "❌ Some required files are missing"
    exit 1
fi

echo "🔍 Checking for planted bugs..."
if grep -q '"./userdata"' src/main/java/UserDataReader.java; then
    echo "✅ Bug 1 detected: Incorrect file path (missing .json)"
else
    echo "❌ Bug 1 not found: File path issue"
fi

if grep -q '"username"' src/main/java/UserDataReader.java; then
    echo "✅ Bug 2 detected: Wrong JSON key name"
else
    echo "❌ Bug 2 not found: JSON key issue"
fi

if grep -q "// BUG 3: No error handling" src/main/java/UserDataReader.java || grep -q "} catch (IOException e) {" src/main/java/UserDataReader.java; then
    echo "✅ Bug 3 detected: Poor error handling"
else
    echo "❌ Bug 3 not found: Error handling issue"
fi

echo "🧪 Running tests to verify they fail as expected..."
mvn clean test

if [ $? -ne 0 ]; then
    echo "✅ Tests fail as expected (bugs are working correctly)"
    echo ""
    echo "🎯 Task 2 is ready for participants!"
    echo "Participants should debug and fix the issues in src/main/java/UserDataReader.java"
    echo "Expected bugs to fix:"
    echo "  1. File path missing .json extension"
    echo "  2. Wrong JSON key name ('username' should be 'user_name')"
    echo "  3. Silent exception handling"
else
    echo "❌ Tests should fail with the buggy code - something is wrong"
    exit 1
fi
