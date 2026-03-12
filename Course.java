import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * Abstract base class representing a generic Course.
 * Demonstrates abstraction, encapsulation, and inheritance principles.
 * All instance variables are private - accessed only via getters/setters.
 */
public abstract class Course {
    // Private instance variables - encapsulation
    private String courseName;
    private String year;
    private String dayOfLecture;
    private LocalTime timeOfLecture;
    private double durationOfLecture;

    /**
     * Constructor for Course
     * @param courseName Name of the course
     * @param year Year level (Year 1, Year 2, Year 3)
     * @param dayOfLecture Day when lecture is held
     * @param timeOfLecture Time when lecture starts
     * @param durationOfLecture Duration in hours
     */
    public Course(String courseName, String year, String dayOfLecture, 
                  String timeOfLecture, double durationOfLecture) {
        this.courseName = courseName;
        this.year = year;
        this.dayOfLecture = dayOfLecture;
        this.timeOfLecture = LocalTime.parse(timeOfLecture, DateTimeFormatter.ofPattern("H:mm"));
        this.durationOfLecture = durationOfLecture;
    }

    // Getter methods - controlled access to private data
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

    /**
     * Abstract method to get delivery mode - must be implemented by subclasses
     * Demonstrates polymorphism
     * @return String representing delivery mode
     */
    public abstract String getDeliveryMode();

    /**
     * Abstract method to check if course has capacity limit
     * @return true if capacity constrained, false otherwise
     */
    public abstract boolean hasCapacityLimit();

    /**
     * Abstract method to get capacity
     * @return capacity number, or -1 if no limit
     */
    public abstract int getCapacity();

    /**
     * Calculate end time of lecture based on start time and duration
     * @return formatted end time string
     */
    public String getEndTime() {
        int durationMinutes = (int) (durationOfLecture * 60);
        LocalTime endTime = timeOfLecture.plusMinutes(durationMinutes);
        return endTime.format(DateTimeFormatter.ofPattern("H:mm"));
    }

    /**
     * Format time range for display
     * @return String in format "HH:mm-HH:mm"
     */
    public String getTimeRange() {
        return timeOfLecture.format(DateTimeFormatter.ofPattern("H:mm")) + "-" + getEndTime();
    }

    /**
     * Check if course name contains keyword (case-insensitive)
     * @param keyword search term
     * @return true if keyword found in course name
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
