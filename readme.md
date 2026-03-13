MYTIMETABLE - OBJECT-ORIENTED DESIGN DOCUMENTATION
==================================================

ASSIGNMENT: COSC1295 Advanced Programming - Assessment 1
AUTHOR: Stephen Fang s3104442
DATE: 2026

TABLE OF CONTENTS:
1. Class Overview and Hierarchy
2. Object-Oriented Principles Demonstrated
3. Java Collections Framework Usage
4. Class Details
5. Compilation and Execution Instructions

==================================================
1. CLASS OVERVIEW AND HIERARCHY
==================================================

The system consists of 5 main classes organized in a hierarchical structure:

                    [Course] (Abstract)
                         |
            +------------+------------+
            |                         |
    [FaceToFaceCourse]      [OnlineCourse]
    (Concrete)                (Concrete)

    [CourseCatalog] - Manages all courses (Composition)
    [Student] - Manages student and enrollments (Composition)
    [MyTimetable] - Main application class (Composition)

==================================================
2. OBJECT-ORIENTED PRINCIPLES DEMONSTRATED
==================================================

A. ENCAPSULATION
   - All instance variables are PRIVATE or PROTECTED
   - Access controlled through getter/setter methods
   - Internal state hidden from external classes
   - Example: Course class has private fields accessed via getters

B. INHERITANCE
   - Course is an abstract base class
   - FaceToFaceCourse and OnlineCourse extend Course
   - Subclasses inherit common attributes and methods
   - Code reuse and hierarchical organization

C. POLYMORPHISM
   - Abstract methods in Course: getDeliveryMode(), hasCapacityLimit(), getCapacity()
   - Different implementations in subclasses
   - Course references can point to either FaceToFaceCourse or OnlineCourse
   - Runtime determination of method behavior

D. ABSTRACTION
   - Course class is abstract - cannot be instantiated directly
   - Common interface for all course types
   - Hides complex implementation details
   - Exposes only essential features

E. COMPOSITION ("Has-A" Relationships)
   - CourseCatalog "has" a List<Course>
   - Student "has" a List<Course> (enrolled courses)
   - MyTimetable "has" CourseCatalog and Student
   - Better design than inheritance for these relationships

==================================================
3. JAVA COLLECTIONS FRAMEWORK (JCF) USAGE
==================================================

REQUIREMENT: Must use JCF - no arrays allowed

USAGE IN THE SYSTEM:

1. ArrayList<Course> in CourseCatalog
   - Stores all available courses from CSV
   - Dynamic sizing as courses loaded
   - Easy iteration and searching

2. ArrayList<Course> in Student
   - Stores student's enrolled courses
   - Dynamic addition/removal
   - Contains checking and iteration

3. List<Course> return types
   - Methods return List interface type
   - Loose coupling, better flexibility
   - Could swap ArrayList for LinkedList if needed

BENEFITS OF JCF:
- Dynamic sizing (no fixed array size)
- Built-in methods for search, add, remove
- Type safety with generics
- Easy iteration with enhanced for-loop

==================================================
4. CLASS DETAILS
==================================================

CLASS: Course (Abstract)
------------------------
Package: default
Purpose: Base class for all course types
Instance Variables (all private):
  - String courseName
  - String year
  - String dayOfLecture
  - LocalTime timeOfLecture
  - double durationOfLecture

Abstract Methods:
  - getDeliveryMode(): String
  - hasCapacityLimit(): boolean
  - getCapacity(): int

Concrete Methods:
  - getEndTime(): Calculates lecture end time
  - getTimeRange(): Returns formatted time range
  - matchesKeyword(String): Case-insensitive search
  - Standard getters for all fields
  - equals() and hashCode() based on courseName

---

CLASS: FaceToFaceCourse (extends Course)
----------------------------------------
Package: default
Purpose: Represents face-to-face delivered courses with capacity limits
Additional Instance Variables (private):
  - int capacity
  - int currentEnrollment

Methods:
  - getDeliveryMode(): Returns "Face-to-face"
  - hasCapacityLimit(): Returns true
  - getCapacity(): Returns capacity value
  - hasAvailableSpace(): Checks currentEnrollment < capacity
  - enrollStudent(): Increments enrollment if space available
  - withdrawStudent(): Decrements enrollment

---

CLASS: OnlineCourse (extends Course)
------------------------------------
Package: default
Purpose: Represents online courses with no capacity limits
No additional instance variables

Methods:
  - getDeliveryMode(): Returns "Online"
  - hasCapacityLimit(): Returns false
  - getCapacity(): Returns -1 (no limit)
  - hasAvailableSpace(): Always returns true

---

CLASS: CourseCatalog
--------------------
Package: default
Purpose: Manages all available courses, loads from CSV
Instance Variables (private):
  - List<Course> courses
  - static final String CSV_FILE = "courses.csv"

Methods:
  - CourseCatalog(): Constructor, calls loadCoursesFromCSV()
  - loadCoursesFromCSV(): Private, reads file and creates objects
  - searchByKeyword(String): Returns List<Course> matching keyword
  - getAllCourses(): Returns copy of all courses
  - findCourseByName(String): Returns single course or null
  - getCourseCount(): Returns number of courses

---

CLASS: Student
--------------
Package: default
Purpose: Represents a student and manages their enrollments
Instance Variables (private):
  - String studentId
  - String name
  - List<Course> enrolledCourses

Methods:
  - Student(String, String): Constructor
  - getEnrolledCourses(): Returns copy of enrolled courses
  - isEnrolled(Course): Checks enrollment status
  - enroll(Course): Adds course, handles capacity for F2F
  - withdraw(Course): Removes course, updates capacity for F2F
  - hasNoCourses(): Checks if enrolled list is empty

---

CLASS: MyTimetable
------------------
Package: default
Purpose: Main application with console interface
Instance Variables (private):
  - CourseCatalog catalog
  - Student currentStudent
  - Scanner scanner

Methods:
  - main(String[]): Entry point
  - run(): Main program loop
  - displayMainMenu(): Shows menu options
  - searchAndEnroll(): Handles search and enrollment flow
  - showEnrolledCourses(): Displays enrolled courses
  - withdrawFromCourse(): Handles withdrawal flow

==================================================
5. COMPILATION AND EXECUTION INSTRUCTIONS
==================================================

REQUIREMENTS:
- Java SE 8 or later
- All .java files in same directory
- courses.csv in same directory

COMPILATION:
------------
javac Course.java FaceToFaceCourse.java OnlineCourse.java CourseCatalog.java Student.java MyTimetable.java

Or compile all at once:
javac *.java

EXECUTION:
----------
use GitHub Codespaces (free):
1. Click "Code" → "Codespaces" → "Create codespace on main"
2. Wait for the environment to load
3. In the terminal, run:
   bash
   javac src/*.java -d bin
   java -cp bin MyTimetable
   

FILE STRUCTURE:
--------------
workspace/
├── courses.csv
├── Course.java
├── FaceToFaceCourse.java
├── OnlineCourse.java
├── CourseCatalog.java
├── Student.java
└── MyTimetable.java

