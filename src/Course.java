import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * Abstract base class representing a Course.
 * COSC1295 Assignment 1 - Week 6 Final Submission
 * 
 * This class demonstrates abstraction, encapsulation and provides
 * common functionality for all course types.
 */
public abstract class Course {
    // Instance variables - private for encapsulation
    private String courseName;
    private String year;
    private String dayOfLecture;
    private LocalTime timeOfLecture;
    private double durationOfLecture;

    /**
     * Constructor for Course
     */
    public Course(String courseName, String year, String dayOfLecture, 
                  String timeOfLecture, double durationOfLecture) {
        this.courseName = courseName;
        this.year = year;
        this.dayOfLecture = dayOfLecture;
        this.timeOfLecture = LocalTime.parse(timeOfLecture, DateTimeFormatter.ofPattern("H:mm"));
        this.durationOfLecture = durationOfLecture;
    }

    // Getter methods
    public String getCourseName() {
        return courseName;
    }

    public String getYear() {
        return year;
    }

    public String getDayOfLecture() {
        return dayOfLecture;
    }

    public LocalTime getTimeOfLecture() {
        return timeOfLecture;
    }

    public double getDurationOfLecture() {
        return durationOfLecture;
    }

    // Abstract methods to be implemented by subclasses
    public abstract String getDeliveryMode();
    public abstract boolean hasCapacityLimit();
    public abstract int getCapacity();

    /**
     * Calculate lecture end time
     */
    public String getEndTime() {
        int durationMinutes = (int) (durationOfLecture * 60);
        LocalTime endTime = timeOfLecture.plusMinutes(durationMinutes);
        return endTime.format(DateTimeFormatter.ofPattern("H:mm"));
    }

    /**
     * Get formatted time range
     */
    public String getTimeRange() {
        return timeOfLecture.format(DateTimeFormatter.ofPattern("H:mm")) + "-" + getEndTime();
    }

    /**
     * Check if course name contains keyword (case-insensitive)
     */
    public boolean matchesKeyword(String keyword) {
        return courseName.toLowerCase().contains(keyword.toLowerCase());
    }

    @Override
    public String toString() {
        return courseName + " " + getDeliveryMode() + " " + dayOfLecture + " " + getTimeRange();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Course course = (Course) obj;
        return courseName.equals(course.courseName);
    }

    @Override
    public int hashCode() {
        return courseName.hashCode();
    }
}
