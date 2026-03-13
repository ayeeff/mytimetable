
```markdown
# MYTIMETABLE - OBJECT-ORIENTED DESIGN DOCUMENTATION

**Assignment:** COSC1295 Advanced Programming - Assessment 1  
**Author:** Stephen Fang (s3104442)  
**Date:** 2026  

---

# Table of Contents
1. Class Overview and Hierarchy
2. Object-Oriented Principles Demonstrated
3. Java Collections Framework Usage
4. Class Details
5. Compilation and Execution Instructions

---

# 1. Class Overview and Hierarchy

The system consists of **5 main classes** organized in a hierarchical structure:

```

```
            Course (Abstract)
                 |
    +------------+------------+
    |                         |
```

FaceToFaceCourse           OnlineCourse
(Concrete)               (Concrete)

CourseCatalog - Manages all courses (Composition)
Student       - Manages student enrollments (Composition)
MyTimetable   - Main application class (Composition)

````

---

# 2. Object-Oriented Principles Demonstrated

## A. Encapsulation
- All instance variables are **private or protected**
- Access controlled through **getter/setter methods**
- Internal state hidden from external classes
- Example: `Course` class has private fields accessed via getters

---

## B. Inheritance
- `Course` is an **abstract base class**
- `FaceToFaceCourse` and `OnlineCourse` extend `Course`
- Subclasses inherit common attributes and methods
- Enables **code reuse and hierarchical structure**

---

## C. Polymorphism
- Abstract methods in `Course`:
  - `getDeliveryMode()`
  - `hasCapacityLimit()`
  - `getCapacity()`
- Implemented differently by subclasses
- `Course` references can point to either subclass
- Method behavior determined **at runtime**

---

## D. Abstraction
- `Course` class is **abstract** and cannot be instantiated
- Provides a **common interface** for course types
- Hides complex implementation details
- Exposes only essential features

---

## E. Composition ("Has-A" Relationships)

- `CourseCatalog` **has a** `List<Course>`
- `Student` **has a** `List<Course>` (enrolled courses)
- `MyTimetable` **has a** `CourseCatalog` and `Student`
- Composition used instead of inheritance where appropriate

---

# 3. Java Collections Framework (JCF) Usage

### Requirement
Must use **Java Collections Framework** — **no arrays allowed**

---

## Usage in the System

### 1. `ArrayList<Course>` in `CourseCatalog`
- Stores all available courses loaded from CSV
- Dynamic sizing as courses are added
- Easy iteration and searching

### 2. `ArrayList<Course>` in `Student`
- Stores student's enrolled courses
- Supports dynamic addition and removal
- Enables contains checks and iteration

### 3. `List<Course>` return types
- Methods return the **List interface**
- Allows loose coupling and flexibility
- Implementation could change to `LinkedList` if needed

---

## Benefits of JCF

- Dynamic sizing (no fixed array limits)
- Built-in methods for add, remove, search
- Type safety using **generics**
- Easy iteration using **enhanced for-loop**

---

# 4. Class Details

---

# Course (Abstract)

**Package:** default  

**Purpose:** Base class for all course types

### Instance Variables (private)

- `String courseName`
- `String year`
- `String dayOfLecture`
- `LocalTime timeOfLecture`
- `double durationOfLecture`

### Abstract Methods

- `getDeliveryMode(): String`
- `hasCapacityLimit(): boolean`
- `getCapacity(): int`

### Concrete Methods

- `getEndTime()` – calculates lecture end time
- `getTimeRange()` – returns formatted time range
- `matchesKeyword(String)` – case-insensitive search
- Standard getters
- `equals()` and `hashCode()` based on `courseName`

---

# FaceToFaceCourse (extends Course)

**Purpose:** Represents face-to-face courses with capacity limits

### Additional Instance Variables

- `int capacity`
- `int currentEnrollment`

### Methods

- `getDeliveryMode()` → `"Face-to-face"`
- `hasCapacityLimit()` → `true`
- `getCapacity()` → returns capacity
- `hasAvailableSpace()` → checks `currentEnrollment < capacity`
- `enrollStudent()` → increments enrollment if space available
- `withdrawStudent()` → decrements enrollment

---

# OnlineCourse (extends Course)

**Purpose:** Represents online courses with no capacity limit

### Instance Variables
None

### Methods

- `getDeliveryMode()` → `"Online"`
- `hasCapacityLimit()` → `false`
- `getCapacity()` → `-1` (no limit)
- `hasAvailableSpace()` → always `true`

---

# CourseCatalog

**Purpose:** Manages available courses and loads them from CSV

### Instance Variables

- `List<Course> courses`
- `static final String CSV_FILE = "courses.csv"`

### Methods

- `CourseCatalog()` – constructor, loads CSV
- `loadCoursesFromCSV()` – reads CSV and creates course objects
- `searchByKeyword(String)` – returns matching courses
- `getAllCourses()` – returns copy of all courses
- `findCourseByName(String)` – returns course or `null`
- `getCourseCount()` – number of courses in catalog

---

# Student

**Purpose:** Represents a student and manages enrollments

### Instance Variables

- `String studentId`
- `String name`
- `List<Course> enrolledCourses`

### Methods

- `Student(String, String)` – constructor
- `getEnrolledCourses()` – returns copy of enrolled courses
- `isEnrolled(Course)` – checks enrollment status
- `enroll(Course)` – adds course and handles capacity logic
- `withdraw(Course)` – removes course and updates capacity
- `hasNoCourses()` – checks if student has no enrolled courses

---

# MyTimetable

**Purpose:** Main application class with console interface

### Instance Variables

- `CourseCatalog catalog`
- `Student currentStudent`
- `Scanner scanner`

### Methods

- `main(String[])` – program entry point
- `run()` – main program loop
- `displayMainMenu()` – shows menu options
- `searchAndEnroll()` – handles course search and enrollment
- `showEnrolledCourses()` – displays current enrollments
- `withdrawFromCourse()` – handles withdrawal process

---

# 5. Compilation and Execution Instructions

## Requirements

- Java SE 8 or later
- All `.java` files in the same directory
- `courses.csv` file present

---

## Compilation

```bash
javac Course.java FaceToFaceCourse.java OnlineCourse.java CourseCatalog.java Student.java MyTimetable.java
````

Or compile everything:

```bash
javac *.java
```

---

## Execution

Using **GitHub Codespaces** (free):

1. Click **Code → Codespaces → Create codespace on main**
2. Wait for the environment to load
3. Run:

```bash
javac src/*.java -d bin
java -cp bin MyTimetable
```

---

# File Structure

```
workspace/
├── courses.csv
├── Course.java
├── FaceToFaceCourse.java
├── OnlineCourse.java
├── CourseCatalog.java
├── Student.java
└── MyTimetable.java
```

```

---

If you want, I can also **upgrade this Markdown to a “High Distinction” level design document** (what many COSC1295 graders expect), adding:

- UML class diagram  
- Sequence diagram  
- Design rationale  
- Complexity analysis  
- Testing section  

That usually pushes documentation from **Credit/Distinction → High Distinction**.
```
