import java.util.ArrayList;
import java.util.List;

/**
 * Student class representing a student in the system
 * Manages enrolled courses using JCF ArrayList
 */
public class Student {
    private String studentId;
    private String name;
    private List<Course> enrolledCourses;

    public Student(String studentId, String name) {
        this.studentId = studentId;
        this.name = name;
        this.enrolledCourses = new ArrayList<Course>();
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
     * Enroll in a course with capacity checking
     */
    public boolean enroll(Course course) {
        if (enrolledCourses.contains(course)) {
            return false;
        }

        if (course instanceof FaceToFaceCourse) {
            FaceToFaceCourse f2f = (FaceToFaceCourse) course;
            if (!f2f.hasAvailableSpace()) {
                return false;
            }
            f2f.enrollStudent();
        }

        enrolledCourses.add(course);
        return true;
    }

    /**
     * Withdraw from a course
     */
    public boolean withdraw(Course course) {
        if (enrolledCourses.contains(course)) {
            enrolledCourses.remove(course);

            if (course instanceof FaceToFaceCourse) {
                FaceToFaceCourse f2f = (FaceToFaceCourse) course;
                f2f.withdrawStudent();
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
