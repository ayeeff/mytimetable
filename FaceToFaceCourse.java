/**
 * Concrete subclass representing a face-to-face delivered course.
 * Extends Course and implements capacity constraints.
 * Demonstrates inheritance and polymorphism.
 */
public class FaceToFaceCourse extends Course {
    // Private instance variable specific to face-to-face courses
    private int capacity;
    private int currentEnrollment;

    /**
     * Constructor for FaceToFaceCourse
     * @param courseName Name of the course
     * @param capacity Maximum number of students
     * @param year Year level
     * @param dayOfLecture Day of lecture
     * @param timeOfLecture Time of lecture
     * @param durationOfLecture Duration in hours
     */
    public FaceToFaceCourse(String courseName, int capacity, String year, 
                            String dayOfLecture, String timeOfLecture, double durationOfLecture) {
        // Call superclass constructor
        super(courseName, year, dayOfLecture, timeOfLecture, durationOfLecture);
        this.capacity = capacity;
        this.currentEnrollment = 0;
    }

    @Override
    public String getDeliveryMode() {
        return "Face-to-face";
    }

    @Override
    public boolean hasCapacityLimit() {
        return true;
    }

    @Override
    public int getCapacity() {
        return capacity;
    }

    /**
     * Get current enrollment count
     * @return number of currently enrolled students
     */
    public int getCurrentEnrollment() {
        return currentEnrollment;
    }

    /**
     * Check if course has available space
     * @return true if enrollment is below capacity
     */
    public boolean hasAvailableSpace() {
        return currentEnrollment < capacity;
    }

    /**
     * Increment enrollment count
     * @return true if successfully enrolled, false if full
     */
    public boolean enrollStudent() {
        if (hasAvailableSpace()) {
            currentEnrollment++;
            return true;
        }
        return false;
    }

    /**
     * Decrement enrollment count
     */
    public void withdrawStudent() {
        if (currentEnrollment > 0) {
            currentEnrollment--;
        }
    }

    @Override
    public String toString() {
        return getCourseName() + " " + getDeliveryMode() + " " + getDayOfLecture() + " " + getTimeRange();
    }
}
