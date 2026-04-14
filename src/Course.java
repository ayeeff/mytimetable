import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * Abstract base class representing a Course.
 * COSC1295 Assignment 1
 *
 * Provides common fields and behaviour for all course types.
 * equals() and hashCode() are based on courseName so that
 * LinkedHashSet<Course> in Student correctly prevents duplicates.
 */

public abstract class Course {
    private String courseName;
    private String year;
    private String dayOfLecture;
    private LocalTime timeOfLecture;
    private double durationOfLecture;

    public Course(String courseName, String year, String dayOfLecture,
                  String timeOfLecture, double durationOfLecture) {
        this.courseName = courseName;
        this.year = year;
        this.dayOfLecture = dayOfLecture;
        this.timeOfLecture = LocalTime.parse(timeOfLecture, DateTimeFormatter.ofPattern("H:mm"));
        this.durationOfLecture = durationOfLecture;
    }

    public String getCourseName() { return courseName; }
    public String getYear() { return year; }
    public String getDayOfLecture() { return dayOfLecture; }
    public LocalTime getTimeOfLecture() { return timeOfLecture; }
    public double getDurationOfLecture() { return durationOfLecture; }

    public abstract String getDeliveryMode();
    public abstract boolean hasCapacityLimit();
    public abstract int getCapacity();

    public String getEndTime() {
        int durationMinutes = (int) (durationOfLecture * 60);
        LocalTime endTime = timeOfLecture.plusMinutes(durationMinutes);
        return endTime.format(DateTimeFormatter.ofPattern("H:mm"));
    }

    public String getTimeRange() {
        return timeOfLecture.format(DateTimeFormatter.ofPattern("H:mm")) + "-" + getEndTime();
    }

    public boolean matchesKeyword(String keyword) {
        return courseName.toLowerCase().contains(keyword.toLowerCase());
    }

    @Override
    public String toString() {
        return courseName + " " + getDeliveryMode() + " " + getDayOfLecture() + " " + getTimeRange();
    }

    /**
     * Equality based on course name.
     * Required for LinkedHashSet duplicate prevention in Student.
     */
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
