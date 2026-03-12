import java.util.ArrayList;
import java.util.List;

/**
 * Student class representing a student in the system.
 * Manages student's enrolled courses using JCF (ArrayList).
 * Demonstrates encapsulation and composition.
 */
public class Student {
    // Private instance variables - encapsulation
    private String studentId;
    private String name;
    private List<Course> enrolledCourses;

    /**
     * Constructor for Student
     * @param studentId Unique student identifier
     * @param name Student name
     */
    public Student(String studentId, String name) {
        this.studentId = studentId;
        this.name = name;
        this.enrolledCourses = new ArrayList<>();
    }

    // Getter methods
    public String getStudentId() {
        return studentId;
    }

    public String getName() {
        return name;
    }

    /**
     * Get list of enrolled courses
     * @return List of courses student is enrolled in
     */
    public List<Course> getEnrolledCourses() {
        return new ArrayList<>(enrolledCourses);
    }

    /**
     * Check if student is enrolled in a specific course
     * @param course Course to check
     * @return true if enrolled
     */
    public boolean isEnrolled(Course course) {
        return enrolledCourses.contains(course);
    }

    /**
     * Enroll in a course
     * @param course Course to enroll in
     * @return true if successfully enrolled, false if already enrolled or full
     */
    public boolean enroll(Course course) {
        // Check if already enrolled
        if (enrolledCourses.contains(course)) {
            return false;
        }

        // Check capacity for face-to-face courses
        if (course instanceof FaceToFaceCourse) {
            FaceToFaceCourse f2fCourse = (FaceToFaceCourse) course;
            if (!f2fCourse.hasAvailableSpace()) {
                return false;
            }
            f2fCourse.enrollStudent();
        }

        enrolledCourses.add(course);
        return true;
    }

    /**
     * Withdraw from a course
     * @param course Course to withdraw from
     * @return true if successfully withdrawn, false if not enrolled
     */
    public boolean withdraw(Course course) {
        if (enrolledCourses.contains(course)) {
            enrolledCourses.remove(course);

            // Update capacity for face-to-face courses
            if (course instanceof FaceToFaceCourse) {
                FaceToFaceCourse f2fCourse = (FaceToFaceCourse) course;
                f2fCourse.withdrawStudent();
            }
            return true;
        }
        return false;
    }

    /**
     * Get number of enrolled courses
     * @return count of enrolled courses
     */
    public int getEnrollmentCount() {
        return enrolledCourses.size();
    }

    /**
     * Check if student has any enrolled courses
     * @return true if no courses enrolled
     */
    public boolean hasNoCourses() {
        return enrolledCourses.isEmpty();
    }

    @Override
    public String toString() {
        return "Student ID: " + studentId + ", Name: " + name;
    }
}
