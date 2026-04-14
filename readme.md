# 📅 MyTimetable
### Advanced Programming - Assessment 1

A Java-based course enrollment and timetable management system.

## 🚀 Compilation & Execution

### 💻 GitHub / Terminal Compile
Use the following commands to compile and run the application from the project root directory:

```bash
# Compile all source files
javac src/*.java -d bin

# Run the application
java -cp bin MyTimetable
```

### 💻 IntelliJ IDEA Compile
1. **Open Project**: Open IntelliJ IDEA and select **Open**. Navigate to the root directory and click **OK**.
2. **Configure Source**: Right-click the `src` folder -> **Mark Directory as** -> **Sources Root**.
3. **Run Application**: Open `src/MyTimetable.java` and click the green play icon next to the `main` method.

## 🛠️ System Architecture

### Class Hierarchy
- **Course (Abstract)**: Base class for all course types.
  - **FaceToFaceCourse**: Managed capacity with physical constraints.
  - **OnlineCourse**: Flexible delivery with unlimited capacity.
- **CourseCatalog**: Central registry for course management.
- **Student**: Manages identity and individual enrollments.
- **MyTimetable**: Main application controller.

### OO Principles Demonstrated
- **Encapsulation**: Private fields with controlled public access via getters/setters.
- **Inheritance**: Subclasses extend `Course` to reuse shared logic.
- **Polymorphism**: Overridden methods allow for specific behavior across different course types.
- **Abstraction**: Abstract `Course` class defines a shared contract without implementation details.

## 📁 Project Structure
- `src/`: Java source files.
- `courses.csv`: Data source for available courses.
- `readme.md`: Project documentation.
