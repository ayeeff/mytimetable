
---

MYTIMETABLE – OBJECT-ORIENTED DESIGN DOCUMENTATION

Assignment: COSC1295 Advanced Programming – Assessment 1
Author: Stephen Fang (s3104442)
Date: April 2026

---

TABLE OF CONTENTS

1. Class Overview and Hierarchy
2. Object-Oriented Principles Demonstrated
3. Java Collections Framework Usage
4. Class Details
5. Compilation and Execution Instructions
6. File Structure

---

1. CLASS OVERVIEW AND HIERARCHY

The system consists of five main classes organised into an object-oriented structure.

```
                    Course (Abstract)
                         |
           +-------------+-------------+
           |                           |
    FaceToFaceCourse             OnlineCourse
       (Concrete)                 (Concrete)
```

CourseCatalog – manages all available courses
Student – manages student information and enrollments
MyTimetable – main application controller

---

2. OBJECT-ORIENTED PRINCIPLES DEMONSTRATED

ENCAPSULATION

All instance variables are private or protected.
External classes access data through getter and setter methods.
Internal class state is protected from direct modification.

Example: The Course class stores its attributes privately and exposes them through getter methods.

INHERITANCE

Course is an abstract base class.

FaceToFaceCourse and OnlineCourse extend Course.

This allows subclasses to reuse common properties while implementing their own behaviour.

POLYMORPHISM

Abstract methods defined in Course include:

getDeliveryMode()
hasCapacityLimit()
getCapacity()

Each subclass provides its own implementation.
A Course reference can point to either subclass at runtime.

ABSTRACTION

The Course class is abstract and cannot be instantiated directly.

It defines the common structure and behaviour shared by all course types.

COMPOSITION

CourseCatalog contains a List<Course>.
Student contains a List<Course> for enrollments.
MyTimetable contains a CourseCatalog and Student.

These relationships represent “has-a” design relationships.

---

3. JAVA COLLECTIONS FRAMEWORK USAGE

Requirement: Use the Java Collections Framework (JCF) instead of arrays.

Usage in the system:

CourseCatalog uses ArrayList<Course> to store all courses loaded from the CSV file.

Student uses ArrayList<Course> to store enrolled courses.

Methods return the List interface type rather than a concrete class, which allows flexible implementations.

Benefits of JCF:

Dynamic sizing (no fixed array length)
Built-in methods for add, remove and search
Type safety through generics
Simple iteration using enhanced for loops

---

4. CLASS DETAILS

CLASS: Course (Abstract)

Purpose: Base class representing a generic course.

Instance Variables

courseName : String
year : String
dayOfLecture : String
timeOfLecture : LocalTime
durationOfLecture : double

Abstract Methods

getDeliveryMode()
hasCapacityLimit()
getCapacity()

Concrete Methods

getEndTime()
getTimeRange()
matchesKeyword(String)
standard getter methods
equals() and hashCode() based on courseName

---

CLASS: FaceToFaceCourse

Extends Course.

Represents a classroom-based course with capacity limits.

Additional Variables

capacity : int
currentEnrollment : int

Methods

getDeliveryMode() returns "Face-to-face"
hasCapacityLimit() returns true
getCapacity() returns capacity value
hasAvailableSpace() checks available seats
enrollStudent() increments enrollment
withdrawStudent() decrements enrollment

---

CLASS: OnlineCourse

Extends Course.

Represents an online course with unlimited capacity.

Methods

getDeliveryMode() returns "Online"
hasCapacityLimit() returns false
getCapacity() returns -1
hasAvailableSpace() always returns true

---

CLASS: CourseCatalog

Manages all available courses.

Variables

courses : List<Course>
CSV_FILE : String

Methods

loadCoursesFromCSV()
searchByKeyword(String)
getAllCourses()
findCourseByName(String)
getCourseCount()

---

CLASS: Student

Represents a student and manages enrollments.

Variables

studentId : String
name : String
enrolledCourses : List<Course>

Methods

getEnrolledCourses()
isEnrolled(Course)
enroll(Course)
withdraw(Course)
hasNoCourses()

---

CLASS: MyTimetable

Main application controller with console interface.

Variables

catalog : CourseCatalog
currentStudent : Student
scanner : Scanner

Methods

main()
run()
displayMainMenu()
searchAndEnroll()
showEnrolledCourses()
withdrawFromCourse()

---

5. COMPILATION AND EXECUTION INSTRUCTIONS

Requirements

Java SE 8 or later
All .java files in the same directory
courses.csv file in the same directory

Compilation

javac Course.java FaceToFaceCourse.java OnlineCourse.java CourseCatalog.java Student.java MyTimetable.java

Or compile everything:

javac *.java

---

Execution

Using GitHub Codespaces (free):

Click Code → Codespaces → Create codespace on main

Wait for the environment to load

Run:

javac src/*.java -d bin
java -cp bin MyTimetable

---

6. FILE STRUCTURE

workspace/

courses.csv
Course.java
FaceToFaceCourse.java
OnlineCourse.java
CourseCatalog.java
Student.java
MyTimetable.java

---
