#!/bin/bash

# Test script to validate Task 1 setup
# Run this from the Task 1 directory

echo "🚀 Testing Task 1 Setup"
echo "========================"

echo "📁 Checking directory structure..."
if [ -d "src/main/java" ] && [ -d "src/test/java" ]; then
    echo "✅ Directory structure is correct"
else
    echo "❌ Directory structure is missing"
    exit 1
fi

echo "📄 Checking required files..."
if [ -f "pom.xml" ] && [ -f "src/main/java/SubstringTasks.java" ] && [ -f "src/test/java/SubstringTasksTest.java" ]; then
    echo "✅ All required files are present"
else
    echo "❌ Some required files are missing"
    exit 1
fi

echo "🔧 Compiling and running tests..."
mvn clean test

if [ $? -eq 0 ]; then
    echo "✅ Tests can run (they should fail initially as implementation returns 0)"
else
    echo "❌ There's an issue with the test setup"
    exit 1
fi

echo ""
echo "🎯 Task 1 is ready for participants!"
echo "Participants should implement the lengthOfLongestSubstring method in src/main/java/SubstringTasks.java"
