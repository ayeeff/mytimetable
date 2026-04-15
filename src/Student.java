import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

/**
 * Student class representing a student in the system.
 * Enrolled courses are stored in a LinkedHashSet:
 *   - Prevents duplicate enrolments by definition (no manual contains() check needed)
 *   - Preserves insertion order for consistent display
 */
public class Student {
    private String studentId;
    private String name;
    private Set<Course> enrolledCourses;

    public Student(String studentId, String name) {
        this.studentId = studentId;
        this.name = name;
        this.enrolledCourses = new LinkedHashSet<Course>();
    }

    public String getStudentId() {
        return studentId;
    }

    public String getName() {
        return name;
    }

    public List<Course> getEnrolledCourses() {
        return new ArrayList<Course>(enrolledCourses);
    }

    public boolean isEnrolled(Course course) {
        return enrolledCourses.contains(course);
    }

    /**
     * Enrol in a course.
     * LinkedHashSet.add() returns false if the course is already present,
     * so duplicate prevention requires no extra contains() call.
     */
    public boolean enroll(Course course) {
        if (course instanceof FaceToFaceCourse) {
            FaceToFaceCourse f2f = (FaceToFaceCourse) course;
            if (!f2f.hasAvailableSpace()) {
                return false;
            }
            // Only increment enrollment if the set actually accepted the course
            if (enrolledCourses.add(course)) {
                f2f.enrollStudent();
                return true;
            }
            return false; // already enrolled
        }
        return enrolledCourses.add(course); // false if duplicate
    }

    /**
     * Withdraw from a course.
     */
    public boolean withdraw(Course course) {
        if (enrolledCourses.remove(course)) {
            if (course instanceof FaceToFaceCourse) {
                ((FaceToFaceCourse) course).withdrawStudent();
            }
            return true;
        }
        return false;
    }

    public boolean hasNoCourses() {
        return enrolledCourses.isEmpty();
    }

    public int getEnrollmentCount() {
        return enrolledCourses.size();
    }

    @Override
    public String toString() {
        return "Student ID: " + studentId + ", Name: " + name;
    }
}
